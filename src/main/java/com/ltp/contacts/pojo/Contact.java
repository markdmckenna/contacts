package com.ltp.contacts.pojo;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Contact {

    private String id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Number cannot be blank")
    private String phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID().toString();
    }

}
