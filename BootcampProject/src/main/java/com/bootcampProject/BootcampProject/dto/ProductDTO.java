package com.bootcampProject.BootcampProject.dto;

import java.util.UUID;

public class ProductDTO extends BaseDTO {

    private String productName;
    private String brand;
    private UUID categoryId;
    private String description;
    private boolean isCancellable;
    private boolean isReturnable;

    public ProductDTO(String productName, String brand, UUID categoryId, String description, boolean isCancellable, boolean isReturnable) {
        this.productName = productName;
        this.brand = brand;
        this.categoryId = categoryId;
        this.description = description;
        this.isCancellable = isCancellable;
        this.isReturnable = isReturnable;
    }

    public ProductDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }
}
