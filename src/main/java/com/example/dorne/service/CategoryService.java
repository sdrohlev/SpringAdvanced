package com.example.dorne.service;

import com.example.dorne.model.entity.Category;

public interface CategoryService {

    void initCategories();

    Category findByName(String categoryName);
}
