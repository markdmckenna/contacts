package com.ltp.contacts.pojo;

import java.util.UUID;

import lombok.Data;

@Data
public class Contact {

    private String id;
    private String name;
    private String phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID().toString();
    }

}
