package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.CategoryMetadataField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryMetadataFieldRepository extends CrudRepository<CategoryMetadataField, UUID> {
    CategoryMetadataField findByName(String name);
    Page<CategoryMetadataField> findAll(Pageable pageable);
}
