package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Category;
import com.bootcampProject.BootcampProject.domain.Product;
import com.bootcampProject.BootcampProject.domain.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    List<Product> findAllBySeller(Seller seller, Pageable pageable);
    Page findAllByCategory(Category category, Pageable pageable);
}
