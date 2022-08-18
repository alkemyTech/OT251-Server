package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.services.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getCategoryList(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsResponse> getCategory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.categoryToCategoryDetailsResponse(categoryService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDetailsResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        CategoryDetailsResponse categoryDetailsResponse = categoryMapper.categoryToCategoryDetailsResponse(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.OK).body(categoryDetailsResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDetailsResponse> updateCategory(@PathVariable UUID id, @RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id,categoryRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
