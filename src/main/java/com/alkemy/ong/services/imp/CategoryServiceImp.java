package com.alkemy.ong.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.category.CategoryDTO;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.repositories.CategoryRepository;
import com.alkemy.ong.services.ICategoryService;

@Service
public class CategoryServiceImp implements ICategoryService{

    @Autowired   
    private CategoryRepository catRep;   
    private CategoryDTO dto = new CategoryDTO();        
    private CategoryMapper map = new CategoryMapper();
    private List<CategoryDTO> listaDto = new ArrayList<CategoryDTO>();

    @Override
    public List<CategoryDTO> listaCategorias() {        
        List<Category> lista = catRep.findAll();

        for (int i=0;i<lista.size();i++) {
            dto = map.crearDTO(lista.get(i));
            listaDto.add(dto);
        }    
        return listaDto;
    }

    
}
