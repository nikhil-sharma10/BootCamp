package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {
    @Column(nullable = false)
    @NotNull
    private String contact;
//    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//    private ProductReview productReview;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

//    public ProductReview getProductReview() {
//        return productReview;
//    }
//
//    public void setProductReview(ProductReview productReview) {
//        this.productReview = productReview;
//    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
