package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderStatus extends BaseDomain{

    @OneToOne
    @JoinColumn(name = "order_product_id")
    private OrderProduct orderProduct;
    @NotNull
    @Column(nullable = false)
    private OrderStatus fromStatus;
    @NotNull
    @Column(nullable = false)
    private OrderStatus toStatus;
    private String transactionNotesComments;

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public OrderStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(OrderStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public OrderStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(OrderStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransactionNotesComments() {
        return transactionNotesComments;
    }

    public void setTransactionNotesComments(String transactionNotesComments) {
        this.transactionNotesComments = transactionNotesComments;
    }
}
