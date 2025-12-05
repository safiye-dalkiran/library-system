package com.library.person;

import java.time.LocalDate;

public class MemberRecord {
    private final int memberId;
    private final LocalDate dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String address;

    public MemberRecord(int memberId, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String address){
        this.memberId = memberId;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.address = address;
    }

    public void incBookIssued(){
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        } else {
            System.out.println("Hata: Kayıt limitine ulaşıldı.");
        }
    }

    public void decBookIssued(){
        if(noBooksIssued > 0) noBooksIssued--;
    }

    public int getNoBooksIssued(){ return noBooksIssued; }

    public int getMaxBookLimit(){ return maxBookLimit; }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString(){
        return "Record{id=" + memberId + ", joined=" + dateOfMembership + ", issued=" + noBooksIssued + "/" + maxBookLimit + "}";
    }
}