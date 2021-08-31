package com.bootcampProject.BootcampProject.dto;

import com.bootcampProject.BootcampProject.domain.BaseDomain;

import java.util.UUID;

public class CartItemDTO extends BaseDTO {

    private Integer quantity;
    private ProductVariationDTO productVariation;

    public CartItemDTO(UUID id, Integer quantity, ProductVariationDTO productVariation) {
        this.id = id;
        this.quantity = quantity;
        this.productVariation = productVariation;
    }


    public CartItemDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductVariationDTO getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariationDTO productVariation) {
        this.productVariation = productVariation;
    }
}
