package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.ImageTable;
import com.bootcampProject.BootcampProject.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ImageTableRepository extends CrudRepository<ImageTable, UUID> {

    public ImageTable findByUserId(Users user);
}
