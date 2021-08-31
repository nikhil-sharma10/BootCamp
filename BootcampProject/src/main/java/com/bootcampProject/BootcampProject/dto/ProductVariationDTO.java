package com.bootcampProject.BootcampProject.dto;

import org.json.JSONObject;

import java.net.URI;
import java.util.UUID;

public class ProductVariationDTO extends BaseDTO {

   private UUID productId;
   private String metaData;
   private URI primaryImageName;
   private Integer quantityAvailable;
   private Double price;

    public ProductVariationDTO() {
    }

    public ProductVariationDTO(UUID id, UUID productId, String metaData, Integer quantityAvailable, Double price) {
        this.id = id;
        this.productId = productId;
        this.metaData = metaData;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public URI getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(URI primaryImageName) {
        this.primaryImageName = primaryImageName;
    }
}
