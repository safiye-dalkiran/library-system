package com.library;

import com.library.billing.Invoice;
import com.library.billing.InvoiceManager;
import com.library.book.Book;
import com.library.catalogue.Catalogue;
import com.library.loan.Loan;
import com.library.loan.LoanManager;
import com.library.person.Librarian;
import com.library.person.Member;

import java.util.*;

public class Library {
    private final Catalogue catalogue = new Catalogue();
    private final LoanManager loanManager = new LoanManager();
    private final InvoiceManager invoiceManager = new InvoiceManager();

    private final Map<Integer, Member> members = new HashMap<>();
    private final Map<Integer, Librarian> librarians = new HashMap<>();


    public Catalogue getCatalogue(){ return catalogue; }
    public LoanManager getLoanManager(){ return loanManager; }
    public InvoiceManager getInvoiceManager(){ return invoiceManager; }

    public void addMember(Member m) { members.put(m.getId(), m); }
    public Member getMember(int id){ return members.get(id); }

    public void addLibrarian(Librarian l) { librarians.put(l.getId(), l); }
    public boolean isLibrarian(int id) { return librarians.containsKey(id); }


    public String borrowBook(int memberId, int bookId) {
        Member m = members.get(memberId);
        if(m == null) return "Üye bulunamadı.";

        Book b = catalogue.getById(bookId);
        if(b == null) return "Kitap bulunamadı.";


        if (m.getBorrowedBookIds().contains(bookId)) {
            return "Bu kitabı zaten ödünç almışsınız.";
        }

        if(!m.canBorrowMore()) return "Üye kitap limitine ulaştı.";

        if(b.getAvailableCopies() <= 0) return "Mevcut kopya yok.";

        b.borrowCopy();
        m.borrowBook(bookId);

        Loan loan = loanManager.createLoan(bookId, memberId, b.getLendingPeriodDays());

        Invoice inv = invoiceManager.createInvoice(m, 10.0, "Kitap Kiralama");

        return "Başarıyla ödünç alındı. LoanId: " + loan.getId();
    }

    public String returnBook(int memberId, int bookId) {
        Member m = members.get(memberId);
        if(m == null) return "Üye bulunamadı.";

        Book b = catalogue.getById(bookId);
        if(b == null) return "Kitap bulunamadı.";

        Optional<Loan> loanOpt = loanManager.findActiveLoanByMemberAndBook(memberId, bookId);
        if(loanOpt.isEmpty()) return "Bu üye için bu kitaba ait aktif ödünç bulunamadı.";

        Loan loan = loanOpt.get();
        loanManager.closeLoan(loan.getId());

        b.returnCopy();
        m.getBorrowedBookIds().remove((Integer)bookId);
        m.getRecord().decBookIssued();

        return "İade başarılı.";
    }
}
