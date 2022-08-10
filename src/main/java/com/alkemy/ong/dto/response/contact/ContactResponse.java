package com.alkemy.ong.dto.response.contact;

import lombok.Data;

import java.util.UUID;

@Data
public class ContactResponse {

    private UUID id;
    private String name;
    private String phone;
    private String email;
    private String message;
}
