package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Category;
import com.bootcampProject.BootcampProject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    public Category findByName(String name);
    Page findAll(Pageable pageable);
}
