package com.example.library.model.domain;

public class Member extends Person{
    private String password;
    private Role role;
    private Boolean chkSelect;

    public Member(int id, String firstName, String lastName, String phoneNumber,
                  Address address, String password, Role role) {
        super( firstName, lastName, phoneNumber, address);
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public Member(int id, String firstName, String lastName, String phoneNumber,
                  Address address, String password, Role role,Boolean chkSelect) {
        super( firstName, lastName, phoneNumber, address);
        this.password = password;
        this.role = role;
        this.id = id;
        this.chkSelect = chkSelect;
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
