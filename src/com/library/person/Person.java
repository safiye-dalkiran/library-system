package com.library.person;

import java.util.Objects;

public abstract class Person {
    private final int id;
    private String name;
    private String email;

    public Person(int id, String name, String email){
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public String getEmail(){ return email; }
    public void setEmail(String email){
        this.email = email;
    }

    public abstract String whoYouAre();

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}