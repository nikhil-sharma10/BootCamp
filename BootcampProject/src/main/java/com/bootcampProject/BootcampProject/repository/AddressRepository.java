package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Address;
import com.bootcampProject.BootcampProject.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends CrudRepository<Address, UUID> {

}
