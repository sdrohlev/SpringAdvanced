package com.example.dorne.repository;

import com.example.dorne.model.entity.Category;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(CategoryNameEnum categoryNameEnum);
}
