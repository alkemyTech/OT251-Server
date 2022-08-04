package com.alkemy.ong.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.ong.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.category.CategoryDTO;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.repositories.CategoryRepository;
import com.alkemy.ong.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired   
    private CategoryRepository categoryRepo;
    private CategoryDTO dto = new CategoryDTO();        
    private CategoryMapper map = new CategoryMapper();
    private List<CategoryDTO> listaDto = new ArrayList<CategoryDTO>();

    @Override
    public List<CategoryDTO> categoryList() {        
        List<Category> lista = categoryRepo.findAll();
        for (int i=0;i<lista.size();i++) {
            dto = map.crearDTO(lista.get(i));
            listaDto.add(dto);
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

}
