package com.bootcampProject.BootcampProject.dto;

import com.bootcampProject.BootcampProject.domain.Category;

import java.util.UUID;

public class ProductViewDTO extends BaseDTO {

    private String productName;
    private String description;
    private String brand;
    private CategoryDTO category;
    private boolean isReturnable;
    private boolean isActive;

    public ProductViewDTO() {
    }

    public ProductViewDTO(UUID id,String productName, String description, String brand, CategoryDTO category, boolean isReturnable, boolean isActive) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.isActive = isActive;
        this.isReturnable = isReturnable;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
