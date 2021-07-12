package com.bootcampProject.BootcampProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties(value = {"productReview","orders","password","addresses","createdAt","updatedAt","createdBy","updatedBy","active","deleted","userRole"})
public class Customer extends Users {
    @Column(nullable = false)
    @NotNull
    private String contact;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ProductReview productReview;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ProductReview getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReview productReview) {
        this.productReview = productReview;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
