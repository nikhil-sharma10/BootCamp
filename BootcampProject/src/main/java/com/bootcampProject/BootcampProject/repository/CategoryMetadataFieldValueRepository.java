package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Category;
import com.bootcampProject.BootcampProject.domain.CategoryMetadataFieldValue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryMetadataFieldValueRepository extends CrudRepository<CategoryMetadataFieldValue, UUID> {

    List<CategoryMetadataFieldValue> findByCategory(Category category);
}
