package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Seller;
import com.bootcampProject.BootcampProject.dto.SellerDTO;

public class SellerTransformer implements DTOTransform<Seller, SellerDTO> {


    @Override
    public SellerDTO toDTO(Seller domainBase) {
        return new SellerDTO(domainBase.getEmail(),domainBase.getCompanyName(),domainBase.getCompanyContact(),domainBase.getFirstName(),domainBase.getLastName());
    }

    @Override
    public Seller fromDTO(SellerDTO baseDTO) {

        Seller seller = new Seller();
        seller.setFirstName(baseDTO.getFirstName());
        seller.setLastName(baseDTO.getLastName());
        seller.setEmail(baseDTO.getEmail());
        seller.setPassword(baseDTO.getPassword());
        seller.setCompanyContact(baseDTO.getCompanyContact());
        seller.setCompanyName(baseDTO.getCompanyName());
        seller.setGst(baseDTO.getGst());
        return seller;
    }
}
