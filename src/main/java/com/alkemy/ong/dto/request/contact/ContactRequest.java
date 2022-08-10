package com.alkemy.ong.dto.request.contact;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ContactRequest {

    @NotBlank(message = "The 'name' field cannot be empty.")
    private String name;

    @Size(min = 7, max = 20, message = "The phone's length must be between 7 and 20 characters.")
    private String phone;

    @NotBlank(message = "The 'email' field cannot be empty.")
    private String email;

    private String message;
}
