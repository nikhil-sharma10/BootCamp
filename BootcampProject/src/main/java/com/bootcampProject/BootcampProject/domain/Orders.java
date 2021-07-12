package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
//Spring boot AuditorAware
//Created at, Created By, ModifiedAt, ModifiedBy

@Entity
public class Orders extends BaseDomain{

    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private Customer customer;
    @NotNull
    @Column(nullable = false)
    private double amountPaid;
    @NotNull
    @Column(nullable = false)
    private Date dateCreated;
    @NotNull
    @Column(nullable = false)
    private String paymentMethod;
    @Embedded
    private CommonAddress address;
    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private OrderProduct orderProduct;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CommonAddress getAddress() {
        return address;
    }

    public void setAddress(CommonAddress address) {
        this.address = address;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }
}
