package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.ProductVariation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductVariationRepository extends CrudRepository<ProductVariation, UUID> {

}
