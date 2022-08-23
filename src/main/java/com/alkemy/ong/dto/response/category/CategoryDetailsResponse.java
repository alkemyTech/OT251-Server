package com.alkemy.ong.dto.response.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.UUID;

@Data
public class CategoryDetailsResponse {

    @Schema(name = "id", description="Category ID", type="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf")
    private UUID id;
    @Schema(name = "name", description="Category name", type="String",example="Others")
    private String name;
    @Schema(name = "description", description="Category description", type="String",example="In this category you'll find...")
    private String description;
    @Schema(name = "image", description="Category image", type="String",example="categoryImage.jpg")
    private String image;
}