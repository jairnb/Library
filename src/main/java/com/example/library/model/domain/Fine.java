package com.example.library.model.domain;

import java.time.LocalDate;

public class Fine {
    private int id;
    private LocalDate paymentDate;
    private Checkout checkOut;

    public Fine(LocalDate paymentDate, Checkout checkOut) {
        this.paymentDate = paymentDate;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Checkout getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Checkout checkOut) {
        this.checkOut = checkOut;
    }
}
