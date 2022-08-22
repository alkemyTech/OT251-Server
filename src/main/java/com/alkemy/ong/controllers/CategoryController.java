package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.services.ICategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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


    @ApiOperation(value = "Method to get all categories", notes = "Returns a page with the first 10 categories and the links of previous and next page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Returns categories page"),
            @ApiResponse(code = 400, message = "NOT FOUND - No categories found")})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PageResultResponse<CategoryResponse>> getCategoryList(@RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories(page));
    }

    @ApiOperation(value = "Method to get a category", notes = "Returns a specific category based on its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Returns category details"),
            @ApiResponse(code = 400, message = "NOT FOUND - No category found with that id")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsResponse> getCategory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.categoryToCategoryDetailsResponse(categoryService.findById(id)));
    }

    @ApiOperation(value = "Method to create a category", notes = "Saves a new category in the database and returns its details")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Category created successfully"),
            @ApiResponse(code = 400, message = "BAD REQUEST - Invalid params")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDetailsResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        CategoryDetailsResponse categoryDetailsResponse = categoryMapper.categoryToCategoryDetailsResponse(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDetailsResponse);
    }

    @ApiOperation(value = "Method to update a category", notes = "Updates a category if it exists, and returns its details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Category updated successfully"),
            @ApiResponse(code = 400, message = "BAD REQUEST - Invalid params"),
            @ApiResponse(code = 400, message = "NOT FOUND - No category found with that id")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDetailsResponse> updateCategory(@PathVariable UUID id, @RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id,categoryRequest));
    }

    @ApiOperation(value = "Method to delete a category", notes = "Deletes a category based on its ID if it exists")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT - Category deleted successfully"),
            @ApiResponse(code = 400, message = "NOT FOUND - No category found with that id")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
