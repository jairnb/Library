package com.example.library.model.domain;

public class Member{
    private int id;
    private String name;
    private String lastName;
    protected String password;
    private String phoneNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
