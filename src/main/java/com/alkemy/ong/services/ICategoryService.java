package com.alkemy.ong.services;

import com.alkemy.ong.models.Category;

import java.util.UUID;

public interface ICategoryService {

    public Category findById(UUID id);
}
