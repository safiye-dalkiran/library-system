package com.library.catalogue;

import com.library.book.Book;
import java.util.*;

public class Catalogue {
    private final Map<Integer, Book> booksById = new HashMap<>();


    private final Map<String, Set<Integer>> booksByAuthor = new HashMap<>();
    private final Map<String, Set<Integer>> booksByCategory = new HashMap<>();

    private int nextId = 1;

    //Person person = new Member()
    public void addBook(Book book) {
        for (Book existingBook : booksById.values()) {
            if (existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                    existingBook.getAuthor().equals(book.getAuthor())) {

                existingBook.addCopies(book.getTotalCopies());
                System.out.println("Kitap zaten var, stok artırıldı: " + existingBook.getTitle());
                return;
            }
        }


        booksById.put(book.getId(), book);

        String authorKey = book.getAuthor().getName().toLowerCase(Locale.ROOT);
        String catKey = book.getCategory().getName().toLowerCase(Locale.ROOT);

        booksByAuthor.computeIfAbsent(authorKey, k -> new HashSet<>()).add(book.getId());
        booksByCategory.computeIfAbsent(catKey, k -> new HashSet<>()).add(book.getId());

        book.getCategory().addBookId(book.getId());
        book.getAuthor().addBookId(book.getId());
    }

    public int generateId() {
        return nextId++;
    }

    public Book getById(int id){ return booksById.get(id); }

    public Set<Book> getByAuthor(String authorName){
        String key = authorName.toLowerCase(Locale.ROOT);
        Set<Integer> ids = booksByAuthor.getOrDefault(key, Collections.emptySet());

        Set<Book> res = new HashSet<>();
        for(int id: ids) {
            Book b = booksById.get(id);
            if(b != null) res.add(b);
        }
        return res;
    }

    public Set<Book> getByCategory(String categoryName){
        String key = categoryName.toLowerCase(Locale.ROOT);
        Set<Integer> ids = booksByCategory.getOrDefault(key, Collections.emptySet());

        Set<Book> res = new HashSet<>();
        for(int id: ids) {
            Book b = booksById.get(id);
            if(b != null) res.add(b);
        }
        return res;
    }

    public List<Book> searchByTitle(String partialTitle) {
        List<Book> results = new ArrayList<>();
        String searchKey = partialTitle.toLowerCase(Locale.ROOT);

        for (Book b : booksById.values()) {
            if (b.getTitle().toLowerCase(Locale.ROOT).contains(searchKey)) {
                results.add(b);
            }
        }
        return results;
    }

    public Collection<Book> getAllBooks(){ return booksById.values(); }

    public boolean removeBook(int id){
        Book b = booksById.remove(id);
        if(b == null) return false;

        String authorKey = b.getAuthor().getName().toLowerCase(Locale.ROOT);
        String catKey = b.getCategory().getName().toLowerCase(Locale.ROOT);

        Set<Integer> authorBooks = booksByAuthor.get(authorKey);
        if(authorBooks != null) authorBooks.remove(id);

        Set<Integer> catBooks = booksByCategory.get(catKey);
        if(catBooks != null) catBooks.remove(id);

        return true;
    }
}