package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.response.category.CategoryDTO;
import com.alkemy.ong.services.ICategoryService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getListCategory() {
        List<CategoryDTO> lista = categoryService.categoryList();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (CategoryDTO n : lista) {
            JSONObject catDTO = new JSONObject();
            catDTO.put("id", n.getId()).toString();
            catDTO.put("name", n.getName()).toString();
            entities.add(catDTO);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

}