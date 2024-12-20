package com.example.pojetopdm.classes;

import java.io.Serializable;

public class Client implements Serializable {
    private String name, email, phoneNumber, address, aditionalNotes;




    public Client(String name, String email, String phoneNumber, String address, String aditionalNotes) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.aditionalNotes = aditionalNotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAditionalNotes() {
        return aditionalNotes;
    }

    public void setAditionalNotes(String aditionalNotes) {
        this.aditionalNotes = aditionalNotes;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
