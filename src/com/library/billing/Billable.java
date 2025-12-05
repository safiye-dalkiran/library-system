package com.library.billing;

import com.library.person.Member;

public interface Billable {
    // Fatura keserken açıklama eklemek (örn: "Gecikme Bedeli") takip açısından önemlidi
    Invoice createInvoice(Member member, double amount, String description);

    boolean refundInvoice(int invoiceId);
}