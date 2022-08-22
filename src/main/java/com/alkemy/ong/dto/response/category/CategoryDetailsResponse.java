package com.alkemy.ong.dto.response.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.UUID;

@Data
public class CategoryDetailsResponse {

    @ApiModelProperty(name = "id", position = 0, value="Category ID", dataType="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf")
    private UUID id;
    @ApiModelProperty(name = "name", position = 1, value="Category name", dataType="String",example="Others")
    private String name;
    @ApiModelProperty(name = "description", position = 2, value="Category description", dataType="String",example="In this category you'll find...")
    private String description;
    @ApiModelProperty(name = "image", position = 3, value="Category image", dataType="String",example="categoryImage.jpg")
    private String image;
}