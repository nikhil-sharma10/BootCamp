package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Product;
import com.bootcampProject.BootcampProject.domain.ProductVariation;
import com.bootcampProject.BootcampProject.dto.ProductVariationDTO;
import com.bootcampProject.BootcampProject.repository.ProductRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductVariationConverter implements DTOTransform<ProductVariation, ProductVariationDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductVariationDTO toDTO(ProductVariation domainBase) {
        return new ProductVariationDTO(domainBase.getId(),domainBase.getProduct().getId(),domainBase.getMetadata().toString(),domainBase.getQuantityAvailable(),domainBase.getPrice());
    }

    @Override
    public ProductVariation fromDTO(ProductVariationDTO baseDTO){
        ProductVariation productVariation = new ProductVariation();
        Product product = productRepository.findById(baseDTO.getProductId()).get();
        productVariation.setProduct(product);
        productVariation.setMetadata(baseDTO.getMetaData());
        productVariation.setPrice(baseDTO.getPrice());
        productVariation.setQuantityAvailable(baseDTO.getQuantityAvailable());
        return productVariation;
    }
}
