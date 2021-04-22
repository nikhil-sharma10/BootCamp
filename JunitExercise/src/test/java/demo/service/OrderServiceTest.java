package demo.service;

import demo.domain.Order;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @org.junit.jupiter.api.Test
    void placeOrder() {
        Order order1 = new Order(10,"Item1",34.8);
        String cc = "nikhil";
        assertTrue(new OrderService().placeOrder(order1,cc));
    }
}