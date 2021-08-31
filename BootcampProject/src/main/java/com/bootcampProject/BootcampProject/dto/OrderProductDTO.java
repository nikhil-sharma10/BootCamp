package com.bootcampProject.BootcampProject.dto;


import com.bootcampProject.BootcampProject.constants.OrdersStatus;

import java.util.List;
import java.util.UUID;

public class OrderProductDTO extends BaseDTO {
    private UUID orderId;
    private Integer quantity;
    private Double price;
    private List<ProductVariationDTO> products;
    private OrdersStatus orderStatus;

    public OrderProductDTO() {
    }

    public OrderProductDTO(UUID orderId, Integer quantity, Double price, List<ProductVariationDTO> products, OrdersStatus orderStatus) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.products = products;
        this.orderStatus = orderStatus;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ProductVariationDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductVariationDTO> products) {
        this.products = products;
    }

    public OrdersStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrdersStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
