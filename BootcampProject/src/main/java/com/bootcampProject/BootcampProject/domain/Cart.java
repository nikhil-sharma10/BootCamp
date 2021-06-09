package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart extends BaseDomain{
    @OneToOne
    @JoinColumn(name = "customer_user_id")
    private Customer customer;
    @NotNull
    @Column(nullable = false)
    private int quantity;
//    private boolean is__WishList_Item;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_variation_id")
    private List<ProductVariation> productVariation;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public boolean isIs__WishList_Item() {
//        return is__WishList_Item;
//    }
//
//    public void setIs__WishList_Item(boolean is__WishList_Item) {
//        this.is__WishList_Item = is__WishList_Item;
//    }


    public List<ProductVariation> getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(List<ProductVariation> productVariation) {
        this.productVariation = productVariation;
    }
}
