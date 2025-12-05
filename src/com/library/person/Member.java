package com.library.person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member extends Person {
    private final List<Integer> borrowedBookIds = new ArrayList<>();
    private MemberRecord record;

    public Member(int id, String name, String email){
        super(id, name, email);
        this.record = new MemberRecord(id, LocalDate.now(), 0, 5, "Unknown");
    }

    public Member(int id, String name, String email, MemberRecord record){
        super(id, name, email);
        this.record = record;
    }

    @Override
    public String whoYouAre(){ return "Member"; }

    public List<Integer> getBorrowedBookIds(){
        return Collections.unmodifiableList(borrowedBookIds);
    }

    public void borrowBook(int bookId) {
        if (!borrowedBookIds.contains(bookId)) {
            borrowedBookIds.add(bookId);

            if (record != null) {
                record.incBookIssued();
            }
        }
    }

    public void returnBook(int bookId) {
        boolean removed = borrowedBookIds.remove((Integer) bookId);

        if (removed && record != null) {
            record.decBookIssued();
        }
    }

    public boolean canBorrowMore(){
        int limit = (record != null) ? record.getMaxBookLimit() : 5;
        return borrowedBookIds.size() < limit;
    }

    public void setRecord(MemberRecord r){
        if(r != null) this.record = r;
    }

    public MemberRecord getRecord(){ return record; }
}