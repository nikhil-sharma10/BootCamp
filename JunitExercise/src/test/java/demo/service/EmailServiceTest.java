package demo.service;

import demo.domain.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    @Test
    void sendEmailThatShouldProvidesException() {
        Order order1 = new Order(24,"Item2",34.5);
        assertThrows(RuntimeException.class, () -> new EmailService().sendEmail(order1));
    }

    @Test
    void sendEmailThatShouldReturnTrue() {
        Order order1 = new Order(24,"Item2",34.5);
        assertTrue(new EmailService().sendEmail(order1,"nikhil.sharma@tothenew.com"));
    }
}