package com.library.book;

import com.library.catalogue.Author;
import com.library.catalogue.Category;

public abstract class Book {
    private final int id;
    private String title;
    private Author author;
    private Category category;

    private int totalCopies;
    private int availableCopies;

    public Book(int id, String title, Author author, Category category, int copies){
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }

    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public Author getAuthor(){ return author; }
    public Category getCategory(){ return category; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies(){ return availableCopies; }


    public void addCopies(int amount) {
        if (amount > 0) {
            this.totalCopies += amount;
            this.availableCopies += amount;
        }
    }

    public synchronized void borrowCopy(){
        if(availableCopies <= 0) {
            throw new IllegalStateException("Stokta kitap kalmadı: " + title);
        }
        availableCopies--;
    }

    public synchronized void returnCopy(){
        if(availableCopies < totalCopies) {
            availableCopies++;
        } else {
            System.out.println("Uyarı: " + title + " kitabı için stok fazlası iade denemesi.");
        }
    }

    @Override
    public String toString(){
        return String.format("Book{id=%d, title='%s', author=%s, category=%s, Stock=%d/%d}",
                id, title, author.getName(), category.getName(), availableCopies, totalCopies);
    }

    public abstract int getLendingPeriodDays();
}