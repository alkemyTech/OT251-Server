package com.alkemy.ong.dto.request.activity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ActivityRequest {

    @NotBlank(message = "The 'name' field cannot be empty.")
    private String name;

    @NotBlank(message = "The 'content' field cannot be empty.")
    private String content;

    @NotBlank(message = "The 'image' field cannot be empty.")
    private String image;
}
