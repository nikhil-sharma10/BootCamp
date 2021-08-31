package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Product;
import com.bootcampProject.BootcampProject.domain.ProductVariation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductVariationRepository extends CrudRepository<ProductVariation, UUID> {

    List<ProductVariation> findAllByProduct(Product product, Pageable pageable);
}
