package com.alkemy.ong.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.category.CategorySlimResponse;
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

    private List<CategorySlimResponse> listaDto = new ArrayList<CategorySlimResponse>();

    @Override
    public List<CategorySlimResponse> categoryList() {
        List<Category> lista = categoryRepo.findAll();
        CategorySlimResponse categorySlimResponse = new CategorySlimResponse();
        for (int i=0;i<lista.size();i++) {
            categorySlimResponse = categoryMapper.categoryToCategorySlimResponse(lista.get(i));
            listaDto.add(categorySlimResponse);
        }    
        return listaDto;
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
    public CategoryResponse update(UUID id, CategoryRequest categoryRequest) {
        categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        category.setId(id);
        return categoryMapper.categoryToCategoryResponse(categoryRepo.save(category));
    }

    @Override
    public void delete(UUID id){
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
    }

}
