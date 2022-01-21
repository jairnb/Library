package com.example.library.model.domain;

import java.util.List;

public class BookCopy {
    private int id;
    private List<Book> bookList;

    public BookCopy(int id, List<Book> bookList) {
        this.id = id;
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
