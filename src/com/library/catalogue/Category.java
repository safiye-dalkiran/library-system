package com.library.catalogue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Category {
    private final String name;
    private final Set<Integer> bookIds = new HashSet<>();

    public Category(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        this.name = name;
    }

    public String getName(){ return name; }

    public Set<Integer> getBookIds(){
        return Collections.unmodifiableSet(bookIds);
    }

    public void addBookId(int id) {
        bookIds.add(id);
    }

    public void removeBookId(int id) {
        bookIds.remove(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString(){
        return "Category{name='" + name + "', count=" + bookIds.size() + "}";
    }
}