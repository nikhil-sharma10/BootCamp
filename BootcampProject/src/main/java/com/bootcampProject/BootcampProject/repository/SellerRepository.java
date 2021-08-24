package com.bootcampProject.BootcampProject.repository;

import com.bootcampProject.BootcampProject.domain.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SellerRepository extends CrudRepository<Seller, UUID> {

    Seller findByGst(String gst);

    Seller findByCompanyName(String companyName);

    Page<Seller> findAll(Pageable pageable);

    Seller findByEmail(String email);
}
