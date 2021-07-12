package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<Users, UUID> {
    Users findByEmail(String email);

}
