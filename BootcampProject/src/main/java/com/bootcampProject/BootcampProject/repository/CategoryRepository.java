package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    public Category findByName(String name);
    Page findAll(Pageable pageable);
}
