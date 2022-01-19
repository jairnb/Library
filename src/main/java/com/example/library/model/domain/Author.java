package com.example.library.model.domain;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String credentials;
    private String phoneNumber;
    private String shortBio;
    private int address_id;

    public Author(int id, String firstName, String lastName, String credentials, String phoneNumber, String shortBio, int address_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
        this.phoneNumber = phoneNumber;
        this.shortBio = shortBio;
        this.address_id = address_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
