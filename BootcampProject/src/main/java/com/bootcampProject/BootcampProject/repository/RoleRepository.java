package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

}
