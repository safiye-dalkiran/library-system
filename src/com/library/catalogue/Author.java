package com.library.catalogue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author {
    private final String name;

    private final Set<Integer> bookIds = new HashSet<>();

    public Author(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString(){
        return "Author{name='" + name + "', bookCount=" + bookIds.size() + "}";
    }
}