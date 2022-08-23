package com.alkemy.ong.dto.response.category;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryResponse {

    @Schema(name = "id", description="Category ID", type="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf")
    private UUID id;
    @Schema(name = "name", description="Category name", type="String",example="Others")
    private String name;
}