package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Category;
import com.bootcampProject.BootcampProject.domain.Product;
import com.bootcampProject.BootcampProject.dto.ProductDTO;
import com.bootcampProject.BootcampProject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductConverter implements DTOTransform<Product, ProductDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDTO toDTO(Product domainBase) {
        return new ProductDTO(domainBase.getName(),domainBase.getBrand(),domainBase.getCategory().getId(),domainBase.getDescription(),domainBase.isCancellable(),domainBase.isReturnable());
    }

    @Override
    public Product fromDTO(ProductDTO baseDTO) {
        Product product = new Product();
        product.setName(baseDTO.getProductName());
        product.setBrand(baseDTO.getBrand());
        product.setDescription(baseDTO.getDescription());
        Category category = categoryRepository.findById(baseDTO.getCategoryId()).get();
        product.setCategory(category);
        product.setCancellable(baseDTO.isCancellable());
        product.setReturnable(baseDTO.isReturnable());
        return product;
    }
}
