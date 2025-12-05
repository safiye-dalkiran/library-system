package com.library.loan;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LoanManager {

    private final Map<Integer, Loan> loans = new HashMap<>();
    private int nextLoanId = 1;

    public Loan createLoan(int bookId, int memberId, int days){
        Loan loan = new Loan(nextLoanId++, bookId, memberId, LocalDate.now(), LocalDate.now().plusDays(days));
        loans.put(loan.getId(), loan);
        return loan;
    }

    public void closeLoan(int loanId){
        Loan loan = loans.get(loanId);
        if(loan == null || !loan.isActive()) return;
        loan.setReturnedDate(LocalDate.now());

    }

    public boolean isBookOnLoan(int bookId){
        return loans.values().stream()
                .anyMatch(l -> l.getBookId() == bookId && l.isActive());
    }

    public Optional<Loan> findActiveLoanByMemberAndBook(int memberId, int bookId){
        return loans.values().stream()
                .filter(l -> l.getBookId() == bookId && l.getMemberId() == memberId && l.isActive())
                .findFirst();
    }

    public List<Loan> getActiveLoans(){
        return loans.values().stream()
                .filter(Loan::isActive)
                .collect(Collectors.toList());
    }

    public Collection<Loan> getAllLoansHistory() {
        return Collections.unmodifiableCollection(loans.values());
    }

    public List<Loan> getOverdueLoans() {
        return loans.values().stream()
                .filter(Loan::isOverdue)
                .collect(Collectors.toList());
    }
}