package com.example.library.model.domain;

public class Role {
    private int id;
    private String type;

    public Role(int id, String type){
        this.id = id;
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
