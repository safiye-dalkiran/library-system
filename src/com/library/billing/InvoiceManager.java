package com.library.billing;

import com.library.person.Member;
import java.util.*;

public class InvoiceManager implements Billable {
    private final Map<Integer, Invoice> invoices = new HashMap<>();
    private int nextId = 1;

    @Override
    public Invoice createInvoice(Member member, double amount, String description){
        Invoice inv = new Invoice(nextId++, member.getId(), amount, description);

        invoices.put(inv.getId(), inv);
        System.out.println("Fatura oluşturuldu: " + inv);
        return inv;
    }

    @Override
    public boolean refundInvoice(int invoiceId){
        Invoice inv = invoices.get(invoiceId);

        if(inv == null || inv.isRefunded()) return false;

        inv.refund();

        System.out.println("Fatura iade edildi: " + inv.getId());
        return true;
    }

    public Collection<Invoice> getAllInvoices(){
        return invoices.values();
    }
}