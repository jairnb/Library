package com.example.library.model.domain;

public class Member extends Person{
    private String password;
    private Role role;



    public Member(int id, String firstName, String lastName, String phoneNumber,
                  Address address, String password, Role role) {
        super( firstName, lastName, phoneNumber, address);
//        super(id, firstName, lastName, phoneNumber, address);
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
