package com.library.billing;

import java.time.LocalDate;

public class Invoice {
    private final int id;
    private final int memberId;
    private final double amount;
    private final LocalDate date;
    private final String description;

    private boolean refunded;

    public Invoice(int id, int memberId, double amount, String description){
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
        this.refunded = false;
    }

    public int getId(){ return id; }
    public int getMemberId(){ return memberId; }
    public double getAmount(){ return amount; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }

    public boolean isRefunded(){ return refunded; }
    public void refund(){
        this.refunded = true;
    }

    @Override
    public String toString(){
        return String.format("Invoice{id=%d, member=%d, amount=%.2f, desc='%s', refunded=%b}",
                id, memberId, amount, description, refunded);
    }
}