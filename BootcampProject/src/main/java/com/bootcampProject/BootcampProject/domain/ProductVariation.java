package com.bootcampProject.BootcampProject.domain;

import com.bootcampProject.BootcampProject.convertor.JSONObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductVariation extends BaseDomain {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantityAvailable;
    private double price;
    @Column(columnDefinition = "Text")
    private String metadata;
    private String primaryImageName;
    private boolean isActive;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        try{
            this.metadata = new ObjectMapper().writeValueAsString(metadata);
        }
        catch (JsonProcessingException ex){
            ex.getMessage();
        }
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @PrePersist
    public void prePersist(){
        try {
            this.setMetadata(new ObjectMapper().writeValueAsString(this));
        }
        catch (JsonProcessingException ex){
            ex.getMessage();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductVariation)) return false;
        ProductVariation that = (ProductVariation) o;
        return getQuantityAvailable() == that.getQuantityAvailable() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                isActive() == that.isActive() &&
                getProduct().equals(that.getProduct()) &&
                getMetadata().equals(that.getMetadata()) &&
                getPrimaryImageName().equals(that.getPrimaryImageName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantityAvailable(), getPrice(), getMetadata(), getPrimaryImageName(), isActive());
    }
}
