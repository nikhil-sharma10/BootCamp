package com.bootcampProject.BootcampProject.domain;

import com.bootcampProject.BootcampProject.constants.OrdersStatus;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class OrderStatus extends BaseDomain{

    @OneToOne
    @JoinColumn(name = "order_product_id")
    private OrderProduct orderProduct;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private OrdersStatus fromStatus;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private OrdersStatus toStatus;
    private String transactionNotesComments;

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public OrdersStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(OrdersStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public OrdersStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(OrdersStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransactionNotesComments() {
        return transactionNotesComments;
    }

    public void setTransactionNotesComments(String transactionNotesComments) {
        this.transactionNotesComments = transactionNotesComments;
    }
}
