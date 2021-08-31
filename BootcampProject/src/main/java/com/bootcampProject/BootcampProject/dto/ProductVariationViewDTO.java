package com.bootcampProject.BootcampProject.dto;



import org.json.simple.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class ProductVariationViewDTO extends BaseDTO{

    private ProductViewDTO product;
    private int quantityAvailable;
    private double price;
    private URI primaryImage;
    private boolean isActive;
    private String metaData;

    public ProductVariationViewDTO() {
    }

    public ProductVariationViewDTO(UUID id, ProductViewDTO product, int quantityAvailable, double price, URI primaryImage, boolean isActive, String metaData) {
        this.id = id;
        this.product = product;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.primaryImage = primaryImage;
        this.isActive = isActive;
        this.metaData = metaData;
    }

    public ProductViewDTO getProduct() {
        return product;
    }

    public void setProduct(ProductViewDTO product) {
        this.product = product;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public URI getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(URI primaryImage) {
        this.primaryImage = primaryImage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
