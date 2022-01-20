package com.example.library.model.domain;

import java.util.List;

public class Member extends Person{
    private String password;
    private List<Role> roleList;

    public Member(String firstName, String lastName, String phoneNumber, Address address, String password, List<Role> roleList) {
        super(firstName, lastName, phoneNumber, address);
        this.password = password;
        this.roleList = roleList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
