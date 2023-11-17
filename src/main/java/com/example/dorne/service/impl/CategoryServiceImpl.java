package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Category;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.repository.CategoryRepository;
import com.example.dorne.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {
                        this.categoryRepository
                                .save(new Category(categoryNameEnum));
                    });
        }

    }

    @Override
    public Category findByName(String categoryName) {
        return this.categoryRepository.findByName(CategoryNameEnum.valueOf(categoryName)).orElse(null);
    }
}
