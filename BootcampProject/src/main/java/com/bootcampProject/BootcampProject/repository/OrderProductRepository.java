package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.OrderProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface OrderProductRepository extends PagingAndSortingRepository<OrderProduct, UUID> {

}
