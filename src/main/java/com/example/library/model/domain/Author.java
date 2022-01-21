package com.example.library.model.domain;

public class Author extends Person{
    private String credentials;
    private String shortBio;

    public Author(){
        super();
    }

    public Author(String firstName, String lastName, String phoneNumber, Address address, String credentials, String shortBio) {
        super(firstName, lastName, phoneNumber, address);
        this.credentials = credentials;
        this.shortBio = shortBio;
    }


    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

}
