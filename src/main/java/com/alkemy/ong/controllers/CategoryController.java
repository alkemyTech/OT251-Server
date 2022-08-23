package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.response.category.CategoryDetailsResponse;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.mappers.CategoryMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.services.ICategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Categories controller")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;


    @Operation(summary = "Method to get all categories", description = "Returns a page with the first 10 categories and the links of previous and next page")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Returns categories page"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - No categories found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PageResultResponse<CategoryResponse>> getCategoryList(@RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories(page));
    }

    @Operation(summary = "Method to get a category", description = "Returns a specific category based on its ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Returns category details"),
            @ApiResponse(responseCode = "400", description = "NOT FOUND - No category found with that id")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsResponse> getCategory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.categoryToCategoryDetailsResponse(categoryService.findById(id)));
    }

    @Operation(summary = "Method to create a category", description = "Saves a new category in the database and returns its details")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Category created successfully"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid params")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDetailsResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        CategoryDetailsResponse categoryDetailsResponse = categoryMapper.categoryToCategoryDetailsResponse(categoryService.save(category));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDetailsResponse);
    }

    @Operation(summary = "Method to update a category", description = "Updates a category if it exists, and returns its details")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid params"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - No category found with that id")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDetailsResponse> updateCategory(@PathVariable UUID id, @RequestBody @Valid CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id,categoryRequest));
    }

    @Operation(summary = "Method to delete a category", description = "Deletes a category based on its ID if it exists")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT - Category deleted successfully"),
            @ApiResponse(responseCode = "400", description = "NOT FOUND - No category found with that id")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
