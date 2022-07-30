package com.alkemy.ong.controllers;

import com.alkemy.ong.models.Category;
import com.alkemy.ong.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

}
