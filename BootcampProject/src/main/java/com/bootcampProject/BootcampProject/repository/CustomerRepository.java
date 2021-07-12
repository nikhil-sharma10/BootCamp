package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    Page<Customer> findAll(Pageable pageable);
}
