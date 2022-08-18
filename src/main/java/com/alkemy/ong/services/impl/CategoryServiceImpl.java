package com.alkemy.ong.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.repositories.CategoryRepository;
import com.alkemy.ong.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired   
    private CategoryRepository categoryRepo;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryResponse> getCategories(Pageable pageable) {
        List<Category> categories = categoryRepo.findAll(Sort.by("name"));
        List<CategoryResponse> responses = new ArrayList<>();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("List Category");
        }
        for (Category category:categories) {
            responses.add(categoryMapper.categoryToCategorySlimResponse(category));
        }
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), responses.size());
        return new PageImpl<>(responses.subList(start, end), pageable, responses.size());
    }

    @Override
    public Category findById(UUID id){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return category;
    }

    @Override
    public Category save(Category category){
        return categoryRepo.save(category);
    }

    @Override
    public CategoryDetailsResponse update(UUID id, CategoryRequest categoryRequest) {
        categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        category.setId(id);
        return categoryMapper.categoryToCategoryDetailsResponse(categoryRepo.save(category));
    }

    @Override
    public void delete(UUID id){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
    }

}
