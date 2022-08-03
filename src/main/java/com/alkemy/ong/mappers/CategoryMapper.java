package com.alkemy.ong.mappers;

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

}
