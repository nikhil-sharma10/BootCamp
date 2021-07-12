package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRoleRepository extends CrudRepository<UserRole, UUID> {

}
