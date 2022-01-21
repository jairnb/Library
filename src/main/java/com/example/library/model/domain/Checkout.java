package com.example.library.model.domain;

import java.time.LocalDate;

public class Checkout {
    private int id;
    private LocalDate dueDate;
    private LocalDate checkoutDate;
    private Book book;
    private Member member;

    public Checkout(LocalDate dueDate, LocalDate checkoutDate, Book book, Member member) {
        this.dueDate = dueDate;
        this.checkoutDate = checkoutDate;
        this.book = book;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
