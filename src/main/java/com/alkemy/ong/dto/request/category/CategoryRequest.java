package com.alkemy.ong.dto.request.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    @NotBlank(message = "The Name field cannot be empty.")
    private String name;
    private String description;
    private String image;

}
