package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.category.CategorySlimResponse;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.services.ICategoryService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getListCategory() {
        List<CategorySlimResponse> lista = categoryService.categoryList();

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (CategorySlimResponse n : lista) {
            JSONObject catDTO = new JSONObject();
            catDTO.put("id", n.getId()).toString();
            catDTO.put("name", n.getName()).toString();
            entities.add(catDTO);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.categoryToCategoryResponse(categoryService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        CategoryResponse categoryResponse = categoryMapper.categoryToCategoryResponse(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id,categoryRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
