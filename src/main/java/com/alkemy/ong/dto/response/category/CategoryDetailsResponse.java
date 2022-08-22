package com.alkemy.ong.dto.response.category;

import lombok.Data;
import java.util.UUID;

@Data
public class CategoryDetailsResponse {

    private UUID id;
    private String name;
    private String description;
    private String image;
}