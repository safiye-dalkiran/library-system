package com.library.book;

import com.library.catalogue.Author;
import com.library.catalogue.Category;

public class Journal extends Book {
    public Journal(int id, String title, Author a, Category c, int copies){
        super(id, title, a, c, copies);
    }

    @Override
    public int getLendingPeriodDays() {
        return 30;
    }
}
