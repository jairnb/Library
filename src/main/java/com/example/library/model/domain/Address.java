package com.example.library.model.domain;

public class Address {
    private int id;
    private String city;
    private String state;
    private String postalCode;
    private String street;

    public Address(int id,String street,String city,String state,String postalCode){
        this.setStreet(street);
        this.setCity(city);
        this.setState(state);
        this.setPostalCode(postalCode);
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
