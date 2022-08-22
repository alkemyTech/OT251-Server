package com.alkemy.ong.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.PageResultMapper;
import com.alkemy.ong.models.Testimonial;
import com.alkemy.ong.utils.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.repositories.CategoryRepository;
import com.alkemy.ong.services.ICategoryService;

@Service
public class CategoryServiceImpl extends ClassUtils<Category, UUID> implements ICategoryService{

    @Autowired   
    private CategoryRepository categoryRepo;

    @Autowired
    private CategoryMapper categoryMapper;

    private final String PATH_CATEGORY = "http://localhost:8080/categories?page=%d";

    @Override
    public PageResultResponse<CategoryResponse> getCategories(Integer pageNumber) {
        Page<Category> page = categoryRepo.findAll(PageRequest.of(pageNumber-1, 10));
        if(!page.hasContent()){
            throw new ResourceNotFoundException();
        }
        List<CategoryResponse> categoryResponses = categoryMapper.entities2ListResponse(page.getContent());
        String previous = getPrevious(PATH_CATEGORY, pageNumber);
        String next = getNext(page, PATH_CATEGORY, pageNumber);

        PageResultMapper<CategoryResponse> response = new PageResultMapper<>();
        return response.mapPage(categoryResponses, previous, next);
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
