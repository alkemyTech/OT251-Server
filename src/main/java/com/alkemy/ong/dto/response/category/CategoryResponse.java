package com.alkemy.ong.dto.response.category;

import java.util.UUID;
import lombok.Data;

@Data
public class CategoryResponse {

    private UUID id;

    private String name;
}