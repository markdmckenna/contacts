package com.ltp.contacts.pojo;

import java.util.UUID;

import lombok.Data;

@Data
public class Contact {

    private String id;
    private String name;
    private String phoneNumber;


    public Contact(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact() {
        this.id = UUID.randomUUID().toString();
    }
   

}
