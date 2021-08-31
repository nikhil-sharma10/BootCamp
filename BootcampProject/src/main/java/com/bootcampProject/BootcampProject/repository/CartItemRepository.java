package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartItemRepository extends CrudRepository<CartItem, UUID> {

}
