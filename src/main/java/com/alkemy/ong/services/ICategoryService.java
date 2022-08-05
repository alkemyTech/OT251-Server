package com.alkemy.ong.services;

import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.category.CategorySlimResponse;
import com.alkemy.ong.models.Category;

public interface ICategoryService {
    public List<CategorySlimResponse> categoryList();

    public Category findById(UUID id);

    public Category save(Category category);

    public CategoryResponse update(UUID id, CategoryRequest categoryRequest);

    public void delete(UUID id);

}
