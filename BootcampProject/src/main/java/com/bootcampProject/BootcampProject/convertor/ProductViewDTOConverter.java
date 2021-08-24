package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Product;
import com.bootcampProject.BootcampProject.dto.ProductViewDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductViewDTOConverter implements DTOTransform<Product, ProductViewDTO> {

    @Autowired
    private CategoryCoverter categoryCoverter;
    @Override
    public ProductViewDTO toDTO(Product domainBase) {
        return new ProductViewDTO(domainBase.getId(),domainBase.getName(),domainBase.getDescription(),domainBase.getBrand(),categoryCoverter.toDTO(domainBase.getCategory()),domainBase.isReturnable(),domainBase.isActive());
    }

    @Override
    public Product fromDTO(ProductViewDTO baseDTO) {
        Product product = new Product();
        product.setName(baseDTO.getProductName());
        product.setBrand(baseDTO.getBrand());
        product.setCategory(categoryCoverter.fromDTO(baseDTO.getCategory()));
        product.setReturnable(baseDTO.isReturnable());
        product.setActive(baseDTO.isActive());
        return product;
    }
}
