package com.alkemy.ong.services.impl;

import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.repositories.CategoryRepository;
import com.alkemy.ong.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(UUID id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return category;
    }
}
