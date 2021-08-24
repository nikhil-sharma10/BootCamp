package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.BlockedToken;
import com.bootcampProject.BootcampProject.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BlockedTokenRepository extends CrudRepository<BlockedToken, UUID> {

    BlockedToken findByToken(String token);
    BlockedToken findByUser(Users user);
}
