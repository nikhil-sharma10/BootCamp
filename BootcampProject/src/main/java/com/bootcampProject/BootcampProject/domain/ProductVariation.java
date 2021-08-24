package com.bootcampProject.BootcampProject.domain;

import com.bootcampProject.BootcampProject.convertor.JSONObjectConverter;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
public class ProductVariation extends BaseDomain {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantityAvailable;
    private double price;
    @Column(columnDefinition = "TEXT")
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject metadata;
    private String primaryImageName;
    private boolean isActive;
    @OneToOne(mappedBy = "productVariation", cascade = CascadeType.ALL)
    private OrderProduct orderProduct;


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

    public JSONObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
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

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }
}
