package com.example.library.model.domain;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private String availability;
    private String maxDate;
    private int author_id;

    public Book(String title, String isbn, String availability, String maxDate, int author_id) {
        this.title = title;
        this.isbn = isbn;
        this.availability = availability;
        this.maxDate = maxDate;
        this.author_id = author_id;
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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
