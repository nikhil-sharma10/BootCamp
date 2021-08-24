package com.bootcampProject.BootcampProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
@JsonIgnoreProperties(value = {"createdAt","createdBy","updatedAt","updatedBy"})
public class CategoryDTO extends BaseDTO{

    private String name;
    private CategoryDTO parentCategory;

    public CategoryDTO(UUID id, String name, CategoryDTO parentCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryDTO parentCategory) {
        this.parentCategory = parentCategory;
    }
}
