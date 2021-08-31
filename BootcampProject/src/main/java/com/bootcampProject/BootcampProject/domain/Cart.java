package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Table(name = "cart")
@Entity
public class Cart extends BaseDomain{
    @OneToOne
    @JoinColumn(name = "customer_user_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
