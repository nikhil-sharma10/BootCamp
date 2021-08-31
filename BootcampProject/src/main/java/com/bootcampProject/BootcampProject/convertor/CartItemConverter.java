package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.CartItem;
import com.bootcampProject.BootcampProject.dto.CartItemDTO;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter implements DTOTransform<CartItem, CartItemDTO> {

    @Autowired
    private ProductVariationConverter productVariationConverter;

    @Override
    public CartItemDTO toDTO(CartItem domainBase) {
        return new CartItemDTO(domainBase.getId(),domainBase.getQuantity(),productVariationConverter.toDTO(domainBase.getProductVariation()));
    }

    @Override
    public CartItem fromDTO(CartItemDTO baseDTO) throws ParseException {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(baseDTO.getQuantity());
        cartItem.setProductVariation(productVariationConverter.fromDTO(baseDTO.getProductVariation()));
        return cartItem;
    }
}
