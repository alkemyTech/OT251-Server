package com.alkemy.ong.services;

import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.response.category.CategoryDTO;
import com.alkemy.ong.models.Category;

public interface ICategoryService {
    public List<CategoryDTO> categoryList();

    public Category findById(UUID id);

}
