package com.example.dorne.model.entity;


import com.example.dorne.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryNameEnum name;

    public Category() {
    }

    public Category(CategoryNameEnum name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }
}
