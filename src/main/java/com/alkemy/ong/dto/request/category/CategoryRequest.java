package com.alkemy.ong.dto.request.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    @ApiModelProperty(name = "name", position = 0, value="Category name", dataType="String",example="Others", required = true)
    @NotBlank(message = "The Name field cannot be empty.")
    private String name;
    @ApiModelProperty(name = "description", position = 1, value="Category description", dataType="String",example="In this category you'll find...")
    private String description;
    @ApiModelProperty(name = "image", position = 2, value="Category image", dataType="String",example="categoryImage.jpg")
    private String image;

}
