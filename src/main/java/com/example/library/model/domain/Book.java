package com.example.library.model.domain;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private String availability;
    List<Author> authors;

    public Book(int id, String title, String isbn, String availability, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.availability = availability;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
