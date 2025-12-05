package com.library.loan;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final int id;
    private final int bookId;
    private final int memberId;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;


    private LocalDate returnedDate;

    public Loan(int id, int bookId, int memberId, LocalDate borrowDate, LocalDate dueDate){
        if (dueDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Due date cannot be before borrow date.");
        }
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public int getId(){ return id; }
    public int getBookId(){ return bookId; }
    public int getMemberId(){ return memberId; }
    public LocalDate getBorrowDate(){ return borrowDate; }
    public LocalDate getDueDate(){ return dueDate; }
    public LocalDate getReturnedDate(){ return returnedDate; }


    public void setReturnedDate(LocalDate date){
        if (date != null && date.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date cannot be before borrow date.");
        }
        this.returnedDate = date;
    }


    public void returnNow() {
        this.returnedDate = LocalDate.now();
    }

    public boolean isActive(){
        return returnedDate == null;
    }

    //kitabın gecikip gecikmediği
    public boolean isOverdue() {
        if (!isActive()) return false;
        return LocalDate.now().isAfter(dueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return String.format("Loan{id=%d, book=%d, member=%d, due=%s, active=%b}",
                id, bookId, memberId, dueDate, isActive());
    }
}