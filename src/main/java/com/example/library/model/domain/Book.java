package com.example.library.model.domain;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private String availability;
    private String maxDate;
    private Author author;

    public Book(String title, String isbn, String availability, String maxDate, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.availability = availability;
        this.maxDate = maxDate;
        this.author = author;
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

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
