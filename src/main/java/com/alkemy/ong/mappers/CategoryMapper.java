package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.models.Category;

@Component
public class CategoryMapper {

    public CategoryResponse categoryToCategorySlimResponse(Category category){
        if (category==null)
            return null;
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
    public CategoryDetailsResponse categoryToCategoryDetailsResponse(Category category){
        if (category==null)
            return null;
        CategoryDetailsResponse categoryDetailsResponse = new CategoryDetailsResponse();
        categoryDetailsResponse.setId(category.getId());
        categoryDetailsResponse.setName(category.getName());
        categoryDetailsResponse.setDescription(category.getDescription());
        categoryDetailsResponse.setImage(category.getImage());
        return categoryDetailsResponse;
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
