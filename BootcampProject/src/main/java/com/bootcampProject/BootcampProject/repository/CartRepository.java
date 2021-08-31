package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Cart;
import com.bootcampProject.BootcampProject.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart, UUID> {

    Cart findByCustomer(Customer customer);
}
