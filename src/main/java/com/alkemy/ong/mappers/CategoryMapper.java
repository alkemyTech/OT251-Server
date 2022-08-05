package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.response.category.CategoryDTO;
import com.alkemy.ong.models.Category;

@Component
public class CategoryMapper {

    private CategoryDTO result = new CategoryDTO();
    
    public CategoryDTO crearDTO(Category cat){
        if (cat==null)
            return null;        
        result.setId(cat.getId());
        result.setName(cat.getName());
        return result;
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
