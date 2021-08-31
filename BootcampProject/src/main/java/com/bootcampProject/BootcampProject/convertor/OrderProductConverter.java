package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.OrderProduct;
import com.bootcampProject.BootcampProject.domain.ProductVariation;
import com.bootcampProject.BootcampProject.dto.OrderProductDTO;
import com.bootcampProject.BootcampProject.dto.ProductVariationDTO;
import com.bootcampProject.BootcampProject.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderProductConverter implements DTOTransform<OrderProduct, OrderProductDTO> {

    @Autowired
    private ProductVariationConverter productVariationConverter;

    @Autowired
    private OrdersRepository repository;

    @Override
    public OrderProductDTO toDTO(OrderProduct domainBase) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setOrderId(domainBase.getOrders().getId());
        dto.setPrice(domainBase.getPrice());
        dto.setQuantity(domainBase.getQuantity());
        dto.setOrderStatus(domainBase.getOrderStatus().getToStatus());
        List<ProductVariationDTO> productVariationDTOS = new ArrayList<>();
        List<ProductVariation> productVariations = domainBase.getProductVariation();
        for(ProductVariation productVariation : productVariations){
            productVariationDTOS.add(productVariationConverter.toDTO(productVariation));
        }
        dto.setProducts(productVariationDTOS);
        return dto;
    }

    @Override
    public OrderProduct fromDTO(OrderProductDTO baseDTO) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setQuantity(baseDTO.getQuantity());
        orderProduct.setPrice(baseDTO.getPrice());
        orderProduct.setOrders(repository.findById(baseDTO.getOrderId()).get());
        return orderProduct;
    }
}
