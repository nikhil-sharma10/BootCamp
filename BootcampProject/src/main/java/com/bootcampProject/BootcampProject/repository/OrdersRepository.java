package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrdersRepository extends CrudRepository<Orders, UUID> {

    Page findAllByCustomer(Customer customer, Pageable pageable);
}
