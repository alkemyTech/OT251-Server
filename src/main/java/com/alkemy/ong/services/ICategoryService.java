package com.alkemy.ong.services;

import java.util.UUID;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    public Page<CategoryResponse> getCategories(Pageable pageable);

    public Category findById(UUID id);

    public Category save(Category category);

    public CategoryDetailsResponse update(UUID id, CategoryRequest categoryRequest);

    public void delete(UUID id);

}
