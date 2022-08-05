package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.response.category.CategorySlimResponse;
import com.alkemy.ong.models.Category;

@Component
public class CategoryMapper {

    public CategorySlimResponse categoryToCategorySlimResponse(Category category){
        if (category==null)
            return null;
        CategorySlimResponse categorySlimResponse = new CategorySlimResponse();
        categorySlimResponse.setId(category.getId());
        categorySlimResponse.setName(category.getName());
        return categorySlimResponse;
    }
    public CategoryResponse categoryToCategoryResponse(Category category){
        if (category==null)
            return null;
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setImage(category.getImage());
        return categoryResponse;
    }
    public Category categoryRequestToCategory(CategoryRequest categoryRequest){
        if(categoryRequest==null)
            return null;
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setImage(categoryRequest.getImage());
        return category;
    }

}
