package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.convertor.CartItemConverter;
import com.bootcampProject.BootcampProject.domain.Cart;
import com.bootcampProject.BootcampProject.domain.CartItem;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.ProductVariation;
import com.bootcampProject.BootcampProject.dto.CartItemDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.CartItemRepository;
import com.bootcampProject.BootcampProject.repository.CartRepository;
import com.bootcampProject.BootcampProject.repository.CustomerRepository;
import com.bootcampProject.BootcampProject.repository.ProductVariationRepository;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CartService extends BaseService {

    @Autowired
    private HeadersUtil headersUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductVariationRepository productVariationRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemConverter cartItemConverter;

    @Autowired
    private CartItemRepository cartItemRepository;

    public String addToCart(HttpServletRequest httpServletRequest, UUID productVariationId, Integer quantity){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        ProductVariation productVariation = productVariationRepository.findById(productVariationId).get();
        if(productVariation != null){
            if(productVariation.isActive()){
                if(!productVariation.getProduct().isDeleted()){
                    if(quantity > 0){
                        Cart cart = cartRepository.findByCustomer(customer);
                        if(cart != null){
                            CartItem cartItem = new CartItem();
                            cartItem.setQuantity(quantity);
                            cartItem.setProductVariation(productVariation);
                            cartItem.setCart(cart);
                            cartItemRepository.save(cartItem);
                        }
                        else{
                            Cart newCart = new Cart();
                            newCart.setCustomer(customer);
                            CartItem cartItem = new CartItem();
                            cartItem.setQuantity(quantity);
                            cartItem.setProductVariation(productVariation);
                            cartItem.setCart(newCart);
                            cartItemRepository.save(cartItem);
                        }
                        String responseMessage = "Item Added to cart Successfully!!!";
                        return responseMessage;
                    }
                    else{
                        throw new BadRequestException("Quantity should be greater than zero");
                    }
                }
                throw new BadRequestException("Associated Product does not found!!!");
            }
            else{
                throw new BadRequestException("Product Variation is not active");
            }
        }
        else{
            throw new BadRequestException("Product Variation ID is not valid");
        }
    }

    public List<CartItemDTO> viewCart(HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        Cart cart = cartRepository.findByCustomer(customer);
        List<CartItemDTO> responseData = new ArrayList<>();
        if(cart != null){
            List<CartItem> cartItems = cart.getCartItems();
            for(CartItem cartItem : cartItems){
                responseData.add(cartItemConverter.toDTO(cartItem));
            }
            return responseData;
        }
        else{
            return responseData;
        }
    }

    @Modifying
    public String deleteCartItem(HttpServletRequest httpServletRequest, UUID productVariationID){
        String userName = headersUtil.getUserName(httpServletRequest);
        Cart cart = cartRepository.findByCustomer(customerRepository.findByEmail(userName));
        if(cart != null){
            ProductVariation productVariation = productVariationRepository.findById(productVariationID).get();
            if(productVariation != null){
                List<CartItem> cartItems = cart.getCartItems();
                for (CartItem cartItem : cartItems){
                    if(cartItem.getProductVariation().equals(productVariation)){
                        cartItems.remove(cartItem);
                        break;
                    }
                }
                cart.setCartItems(cartItems);
                cartRepository.save(cart);
                String message = "Cart Item deleted Successfully!!";
                return message;
            }
            else{
                throw new BadRequestException("Product Variation ID is not valid");
            }
        }
        else{
            throw new BadRequestException("There is no item in cart of the User");
        }
    }

    @Modifying
    public String updateCart(HttpServletRequest httpServletRequest, UUID productVariationId, Integer quantity){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        Cart cart = cartRepository.findByCustomer(customer);

        if(cart != null && cart.getCartItems() != null){
            ProductVariation productVariation = productVariationRepository.findById(productVariationId).get();
            if(productVariation != null){
                if(quantity >= 0){
                    int position = this.getCartItem(cart,productVariationId);
                    if(position != -1){
                        CartItem cartItem = new CartItem();
                        cartItem.setQuantity(quantity);
                        List<CartItem> cartItems = cart.getCartItems();
                        cartItems.set(position,cartItem);
                        cart.setCartItems(cartItems);
                        cartRepository.save(cart);
                        String message = "Cart updated successfully!!!";
                        return message;
                    }
                    else {
                        throw new BadRequestException("Product doesn't exist in the Cart");
                    }
                }
                else{
                    throw new BadRequestException("Quantity can't be less than zero!!");
                }

            }
            else{
                throw new BadRequestException("Product Variation ID is not valid");
            }
        }
        else {
            throw new BadRequestException("Customer doesn't have any item in the Cart!!!");
        }
    }

    private int getCartItem(Cart cart, UUID productVariationId){
        List<CartItem> cartItems = cart.getCartItems();
        int position = -1;
        for(int i=0; i< cartItems.size(); i++){
            if(cartItems.get(i).getProductVariation().getId().equals(productVariationId)){
                return i;
            }
        }
        return position;
    }

    @Modifying
    public String deleteCart(HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Customer customer = customerRepository.findByEmail(userName);
        Cart cart = cartRepository.findByCustomer(customer);
        if(cart != null){
            cartRepository.delete(cart);
            String message = "Cart deleted Successfully!!!";
            return message;
        }
        else{
            throw new BadRequestException("Cart doesn't exist for this customer");
        }
    }
}
