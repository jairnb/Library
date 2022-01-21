package com.example.library.model.domain;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private String availability;
    private String maxDate;
    private List<Author> authors;
    private int numberOfCopy;
    private int numberAvailable;

    public Book(String title, String isbn, String availability, String maxDate, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.availability = availability;
        this.maxDate = maxDate;
        this.authors = authors;
    }


    public Book() {
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

    public List<Author> getAuthor() {
        return authors;
    }

    public void setAuthor(List<Author> authors) {
        this.authors = authors;
    }

    public int getNumberOfCopy() {
        return numberOfCopy;
    }

    public void setNumberOfCopy(int numberOfCopy) {
        this.numberOfCopy = numberOfCopy;
    }

    public int getNumberAvailable() {
        return numberAvailable;
    }

    public void setNumberAvailable(int numberAvailable) {
        this.numberAvailable = numberAvailable;
    }
}
