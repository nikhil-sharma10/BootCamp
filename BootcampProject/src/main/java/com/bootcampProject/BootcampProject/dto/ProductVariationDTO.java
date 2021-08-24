package com.bootcampProject.BootcampProject.dto;

import org.json.JSONObject;

import java.util.UUID;

public class ProductVariationDTO extends BaseDTO {

   private UUID productId;
   private JSONObject metaData;
   private String primaryImageName;
   private int quantityAvailable;
   private double price;

    public ProductVariationDTO() {
    }

    public ProductVariationDTO(UUID productId, JSONObject metaData, String primaryImageName, int quantityAvailable, double price) {
        this.productId = productId;
        this.metaData = metaData;
        this.primaryImageName = primaryImageName;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public JSONObject getMetaData() {
        return metaData;
    }

    public void setMetaData(JSONObject metaData) {
        this.metaData = metaData;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
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
}
