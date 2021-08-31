package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Address;
import com.bootcampProject.BootcampProject.domain.CommonAddress;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.dto.*;
import com.bootcampProject.BootcampProject.service.*;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CommonService commonService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/getAddress")
    public ResponseEntity<?> getAddresses(HttpServletRequest request){
        List<CommonAddress> addressList = commonService.getAddress(request);
        final String responseMessage = "Address Fetched Successfully";
        return new ResponseEntity<>(new ResponseBody<>(addressList,responseMessage), HttpStatus.OK);
    }

    @PatchMapping("/{addressId}/updateAddress")
    public ResponseEntity<?> updateAddress(@PathVariable("addressId") UUID addressId, @RequestBody CommonAddress address){
        CommonAddress updatedAddress = commonService.updateAddress(addressId,address);
        final String responseMessage = "Address Updated Successfully";
        return new ResponseEntity<>(new ResponseBody<>(updatedAddress,responseMessage),HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}/deleteAddress")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") UUID addressId, HttpServletRequest request){
        final String responseMessage = commonService.deleteAddress(request, addressId);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(HttpServletRequest request, @RequestBody CommonAddress address){
        final String responseMessage = commonService.addAddress(request,address);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword){
        final String responseMessage = commonService.updatePassword(request,password,confirmPassword);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @GetMapping("/getProfile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) throws Exception{
        ProfileDTO profile = commonService.getProfile(request);
        final String responseMessage = "Profile fetched Successfully";
        return new ResponseEntity<>(new ResponseBody<>(profile,responseMessage),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/updateProfile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestPart(value = "profile",required = false) String profileDTO, @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        String responseMessage = commonService.updateProfile(request,profileDTO,file);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(HttpServletRequest httpServletRequest, @PathVariable("productId") UUID productId){
        ProductViewDTO responseData = productService.getProduct(productId,httpServletRequest);
        String responseMessage = "Product details fetched Successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllProduct")
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name = "order",defaultValue = "ASC") String order, @RequestParam("categoryId") UUID id){
        List<ProductViewDTO> responseData = productService.getAllProductByQuery(page,size,sortBy,order,"Category",id);
        String responseMessage = "Product List fetched Successfully!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.OK);
    }

    @PostMapping(path = "/addToCart")
    public ResponseEntity<?> addToCart(HttpServletRequest httpServletRequest, @RequestParam("productVariationId") UUID productVariationId, @RequestParam("quantity") Integer quantity){
        String responseMessage = cartService.addToCart(httpServletRequest,productVariationId,quantity);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.CREATED);
    }

    @GetMapping(path = "/viewCart")
    public ResponseEntity<?> viewCart(HttpServletRequest httpServletRequest){
        List<CartItemDTO> responseData = cartService.viewCart(httpServletRequest);
        String responseMessage = "Cart data fetched Successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteCartItem/{productVariationId}")
    public ResponseEntity<?> deleteCartItem(HttpServletRequest httpServletRequest, @PathVariable(name = "productVariationId") UUID productVariationId){
        String responseMessage = cartService.deleteCartItem(httpServletRequest,productVariationId);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.OK);
    }

    @PatchMapping(path = "/updateCart/{productVariationId}")
    public ResponseEntity<?> updateCartItem(HttpServletRequest httpServletRequest, @PathVariable("productVariationId") UUID productVariationId, @RequestParam("quantity") Integer quantity){
        String responseMessage = cartService.updateCart(httpServletRequest,productVariationId,quantity);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteCart")
    public ResponseEntity<?> deleteCart(HttpServletRequest httpServletRequest){
        String responseMessage = cartService.deleteCart(httpServletRequest);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.OK);
    }

    @PostMapping(path = "/orderAllCartProducts")
    public ResponseEntity<?> orderAllCartProducts(HttpServletRequest httpServletRequest){
        UUID responseData = orderService.orderCartProducts(httpServletRequest,false,null,false,null,null);
        String responseMessage = "Items ordered successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.CREATED);
    }

    @PostMapping(path = "/orderPartialProducts")
    public ResponseEntity<?> orderPartialProductsFromCart(HttpServletRequest httpServletRequest, @RequestParam("productVariationIds") List<UUID> productVariationsIds){
        UUID responseData = orderService.orderCartProducts(httpServletRequest,true,productVariationsIds,false,null,null);
        String responseMessage = "Items ordered successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.CREATED);
    }

    @PostMapping(path = "/orderProduct")
    public ResponseEntity<?> orderProduct(HttpServletRequest httpServletRequest, @RequestParam("productVariationId") UUID productVariationId, @RequestParam("quantity") Integer quantity){
        UUID responseData = orderService.orderCartProducts(httpServletRequest,false,null,true,productVariationId,quantity);
        String responseMessage = "Item ordered Successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.CREATED);
    }

    @PatchMapping(path = "/cancelOrder/{orderProductId}")
    public ResponseEntity<?> cancelOrder(HttpServletRequest httpServletRequest, @PathVariable("orderProductId") UUID orderProductId){
        String responseMessage = orderService.cancelReturnOrder(httpServletRequest,orderProductId,false);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PatchMapping(path = "/returnOrder/{orderProductId}")
    public ResponseEntity<?> returnOrder(HttpServletRequest httpServletRequest, @PathVariable("orderProductId")UUID orderProductId){
        String responseMessage = orderService.cancelReturnOrder(httpServletRequest,orderProductId,true);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @GetMapping(path = "/viewOrder/{orderId}")
    public ResponseEntity<?> viewOrder(HttpServletRequest httpServletRequest, @PathVariable("orderId") UUID orderId){
        OrderProductDTO responseData = orderService.viewOrder(httpServletRequest,orderId);
        String responseMessage = "Order details fetched successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.OK);
    }

    @GetMapping(path = "/viewOrders")
    public ResponseEntity<?> viewAllOrders(HttpServletRequest httpServletRequest, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name = "order", defaultValue = "ASC") String order){
        List<OrderProductDTO> responseData = orderService.viewAllOrders(httpServletRequest,page,size,sortBy,order);
        String responseMessage = "Orders details fetched successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage), HttpStatus.OK);
    }




}
