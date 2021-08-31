package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.ProductVariation;
import com.bootcampProject.BootcampProject.dto.ProductVariationViewDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductVariationViewConverter implements DTOTransform<ProductVariation, ProductVariationViewDTO> {

    @Autowired
    private ProductViewDTOConverter productViewDTOConverter;

    @Autowired
    private ImageService imageService;

    @Override
    public ProductVariationViewDTO toDTO(ProductVariation domainBase) {
        try{
            return new ProductVariationViewDTO(domainBase.getId(),productViewDTOConverter.toDTO(domainBase.getProduct()),domainBase.getQuantityAvailable(),domainBase.getPrice(),imageService.loadImageAsResource(domainBase.getPrimaryImageName()),domainBase.isActive(),domainBase.getMetadata());
        }
        catch (Exception ex){
            throw new BadRequestException(ex.getMessage());
        }

    }

    @Override
    public ProductVariation fromDTO(ProductVariationViewDTO baseDTO) {
        ProductVariation productVariation = new ProductVariation();
        productVariation.setProduct(productViewDTOConverter.fromDTO(baseDTO.getProduct()));
        productVariation.setActive(baseDTO.isActive());
        productVariation.setQuantityAvailable(baseDTO.getQuantityAvailable());
        productVariation.setPrice(baseDTO.getPrice());
        productVariation.setMetadata(baseDTO.getMetaData());
        return productVariation;
    }
}
