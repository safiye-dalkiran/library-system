package com.library.person;

public class Librarian extends Person {
    private String password;

    public Librarian(int id, String name, String email, String password){
        super(id, name, email);
        this.password = password;
    }

    @Override
    public String whoYouAre(){ return "Librarian"; }

    public boolean verifyPassword(String inputPassword) {

        return this.password != null && this.password.equals(inputPassword);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}