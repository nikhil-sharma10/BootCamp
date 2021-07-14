package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.BlockedToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BlockedTokenRepository extends CrudRepository<BlockedToken, UUID> {

    BlockedToken findByToken(String token);
}
