package com.alkemy.ong.dto.request.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    @Schema(name = "name", description="Category name", type="String", example="Others", required = true)
    @NotBlank(message = "The Name field cannot be empty.")
    private String name;
    @Schema(name = "description", description="Category description", type="String", example="In this category you'll find...")
    private String description;
    @Schema(name = "image", description="Category image", type="String",example="categoryImage.jpg")
    private String image;

}
