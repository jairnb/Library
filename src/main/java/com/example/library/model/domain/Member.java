package com.example.library.model.domain;

public class Member extends Person{
    private String password;
    private String role;
    private String userId;

    public Member(int id, String firstName, String lastName, String phoneNumber,
                  Address address, String password, String role,String user_id) {

        super( firstName, lastName, phoneNumber, address);
        this.password = password;
        this.role = role;
        this.id = id;
        this.userId = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
