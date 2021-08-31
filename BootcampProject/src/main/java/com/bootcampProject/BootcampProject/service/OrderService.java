package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.constants.OrdersStatus;
import com.bootcampProject.BootcampProject.convertor.OrderProductConverter;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.OrderProductDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.*;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
@Service
public class OrderService extends BaseService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private HeadersUtil headersUtil;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductVariationRepository productVariationRepository;

    @Autowired
    private OrderProductConverter orderProductConverter;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public UUID orderCartProducts(HttpServletRequest httpServletRequest, boolean isPartial, List<UUID> productVariationIds, boolean isDirect, UUID productVariationId, Integer givenQuantity){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        Cart cart = cartRepository.findByCustomer(customer);

        if(cart != null && cart.getCartItems() != null){
            List<CartItem> cartItems = cart.getCartItems();
            List<ProductVariation> orderProducts = new ArrayList<>();
            for(CartItem cartItem : cartItems){
                if(cartItem.getProductVariation().isActive() && ! cartItem.getProductVariation().getProduct().isDeleted()){
                    if(cartItem.getProductVariation().getQuantityAvailable() > 0){
                        if(isPartial){
                            if(productVariationIds.contains(cartItem.getProductVariation().getId())){
                             orderProducts.add(cartItem.getProductVariation());
                            }
                        }
                        else if(!isDirect){
                            orderProducts.add(cartItem.getProductVariation());
                        }

                    }
                    else{
                        throw new BadRequestException("Product is not available");
                    }
                }
                else{
                    throw new BadRequestException("Product is not valid!!");
                }
            }
            Orders order = new Orders();
            order.setCustomer(customer);
            ProductVariation productVariation = null;
            if(isDirect && !isPartial){
                productVariation = productVariationRepository.findById(productVariationId).get();
                if(productVariation == null && !productVariation.isActive() && !productVariation.getProduct().isDeleted()){
                    throw new BadRequestException("Product Variation Id is not  valid");
                }
            }
            Double totalPrice = orderProducts.stream().mapToDouble(ProductVariation::getPrice).sum();
            if(!isDirect){
                order.setAmountPaid(totalPrice);
            }
            else{
                order.setAmountPaid(productVariation.getPrice());
            }
            order.setDateCreated(new Date());
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrders(order);
            if(!isDirect){
                orderProduct.setProductVariation(orderProducts);
                orderProduct.setPrice(totalPrice);
            }
            else {
                List<ProductVariation> productVariationList = new ArrayList<>();
                productVariationList.add(productVariation);
                orderProduct.setProductVariation(productVariationList);
                orderProduct.setPrice(productVariation.getPrice());
            }
            if(!isDirect){
                Integer quantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
                orderProduct.setQuantity(quantity);
            }
            else {
                orderProduct.setQuantity(givenQuantity);
            }
            orderProductRepository.save(orderProduct);
            return order.getId();
        }
        else{
            throw new BadRequestException("There is no item in customer's cart");
        }
    }

    @Modifying
    public String cancelReturnOrder(HttpServletRequest httpServletRequest, UUID orderProductId, boolean isReturn){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        OrderProduct orderProduct = orderProductRepository.findById(orderProductId).get();
        if(orderProduct != null){
            if(orderProduct.getOrders().getCustomer().getEmail().equalsIgnoreCase(userName)){
                if(!isReturn){
                    if(orderProduct.getOrderStatus().getToStatus().equals(OrdersStatus.ORDER_PLACED) || orderProduct.getOrderStatus().getToStatus().equals(OrdersStatus.ORDER_CONFIRMED)){

                        OrderStatus orderStatus = orderProduct.getOrderStatus();
                        orderStatus.setFromStatus(orderProduct.getOrderStatus().getToStatus());
                        orderStatus.setToStatus(OrdersStatus.CANCELLED);
                        orderProduct.setOrderStatus(orderStatus);
                        orderProductRepository.save(orderProduct);
                        String message = "Order cancelled successfully!!!";
                        return message;
                    }
                    else{
                        throw new BadRequestException("Order can't be cancelled");
                    }
                }
                else{
                    if(orderProduct.getOrderStatus().getToStatus().equals(OrdersStatus.DELIVERED)){
                        OrderStatus orderStatus = orderProduct.getOrderStatus();
                        orderStatus.setFromStatus(orderProduct.getOrderStatus().getToStatus());
                        orderStatus.setToStatus(OrdersStatus.RETURN_REQUESTED);
                        orderProduct.setOrderStatus(orderStatus);
                        orderProductRepository.save(orderProduct);
                        String message = "Order return request initiated Successfully!!";
                        return message;
                    }
                    else {
                        throw new BadRequestException("Order can't be returned!!!");
                    }
                }
            }
            else{
                throw new BadRequestException("Order doesn't belong to the Customer");
            }
        }
        else{
            throw new BadRequestException("Given Id is not valid");
        }
    }

    public OrderProductDTO viewOrder(HttpServletRequest httpServletRequest, UUID orderId){
        String userName = headersUtil.getUserName(httpServletRequest);
        Orders order = ordersRepository.findById(orderId).get();
        if(order != null){
            if(order.getCustomer().getEmail().equalsIgnoreCase(userName)){
                OrderProduct orderProduct = order.getOrderProduct();
                OrderProductDTO dto = orderProductConverter.toDTO(orderProduct);
                return dto;
            }
            else{
                throw new BadRequestException("Customer is not authorized to view this order");
            }
        }
        else{
            throw new BadRequestException("Order id is not valid");
        }
    }

    public List<OrderProductDTO> viewAllOrders(HttpServletRequest httpServletRequest, Integer page, Integer size, String sortBy, String order){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        List<Orders> orderList  = ordersRepository.findAllByCustomer(customer, PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),sortBy))).getContent();
        List<OrderProductDTO> dto = new ArrayList<>();
        for(Orders orders : orderList){
            dto.add(orderProductConverter.toDTO(orders.getOrderProduct()));
        }
        return dto;
    }

    public String changeOrderStatus(HttpServletRequest httpServletRequest, UUID orderProductId, OrdersStatus fromStatus, OrdersStatus toStatus, boolean isAdmin){
        String userName = headersUtil.getUserName(httpServletRequest);
        Seller seller = null;
        if(!isAdmin)
         seller = sellerRepository.findByEmail(userName);

        OrderProduct orderProduct = orderProductRepository.findById(orderProductId).get();
        if(orderProduct != null){
            List<ProductVariation> productVariationList = orderProduct.getProductVariation();
            for(ProductVariation productVariation : productVariationList){
                if(isAdmin && productVariation.getProduct().getSeller() == null){
                    throw new BadRequestException("Product doesn't belong to any seller");
                }
                else if(!productVariation.getProduct().getSeller().getEmail().equalsIgnoreCase(userName)){
                    throw new BadRequestException("Seller is not authorized to change the status of this order");
                }
            }
            if(orderProduct.getOrderStatus().getToStatus().equals(fromStatus)){
                OrderStatus orderStatus = orderProduct.getOrderStatus();
                orderStatus.setFromStatus(fromStatus);
                orderStatus.setToStatus(toStatus);
                orderProduct.setOrderStatus(orderStatus);
                orderProductRepository.save(orderProduct);
                String message = "Order status changed Successfully!!";
                return message;
            }
            else {
                throw new BadRequestException("Given status is not valid");
            }
        }
        else{
            throw new BadRequestException("Order product id is not valid");
        }
    }

    public List<OrderProductDTO> viewAllOrdersForAdminSeller(HttpServletRequest httpServletRequest, Integer page, Integer size, String sortBy, String order, boolean isAdmin){
        String userName = headersUtil.getUserName(httpServletRequest);
        Seller seller = null;
        if(!isAdmin){
            seller = sellerRepository.findByEmail(userName);
        }
        List<OrderProduct> orderProducts = orderProductRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(order),sortBy))).getContent();
        List<OrderProductDTO> responseData = new ArrayList<>();

        if(isAdmin){
            for(OrderProduct orderProduct : orderProducts){
                responseData.add(orderProductConverter.toDTO(orderProduct));
            }
        }
        else{
            for(OrderProduct orderProduct : orderProducts){
                List<ProductVariation> productVariationList = orderProduct.getProductVariation();
                for(ProductVariation productVariation : productVariationList){
                    if(productVariation.getProduct().getSeller().getEmail().equalsIgnoreCase(userName)){
                        responseData.add(orderProductConverter.toDTO(orderProduct));
                        break;
                    }
                }
            }
        }
        return responseData;

    }
}
