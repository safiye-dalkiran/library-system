package com.library.book;

import com.library.catalogue.Author;
import com.library.catalogue.Category;

public class Novel extends Book {

    public Novel(int id, String title, Author author, Category category, int copies) {
        super(id, title, author, category, copies);
    }

    @Override
    public int getLendingPeriodDays() {
        return 15;
    }
}