package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.constants.OrdersStatus;
import com.bootcampProject.BootcampProject.domain.CategoryMetadataField;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.Seller;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.*;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.service.AdminService;
import com.bootcampProject.BootcampProject.service.OrderService;
import com.bootcampProject.BootcampProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/admin")
public class AdminAPI {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @GetMapping(path = "/get-customer")
    public ResponseEntity<?> getAllCustomer(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy){
        ResponseBody<List<Customer>,String>responseBody = adminService.getAllRegisteredCustomer(page,size,sortBy);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping(path = "/get-seller")
    public ResponseEntity<?> getAllSellers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy){
        ResponseBody<List<Seller>,String> responseBody = adminService.getAllRegisteredSeller(page,size,sortBy);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }

    @PatchMapping(path = "/activate-customer/")
    public ResponseEntity<?> activateCustomer(@RequestParam(value = "id") UUID id){
        String responseMessage = adminService.activateDeactivateUser(id,true);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PatchMapping(path = "/activate-seller")
    public ResponseEntity<?> activateSeller(@RequestParam(value = "id") UUID id){
        String responseMessage = adminService.activateDeactivateUser(id,true);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PatchMapping(path = "/deactivate-customer")
    public ResponseEntity<?> deactivateCustomer(@RequestParam(value = "id") UUID id){
        String responseMessage = adminService.activateDeactivateUser(id,false);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PatchMapping(path = "/deactivate-seller")
    public ResponseEntity<?> deactivateSeller(@RequestParam(value = "id") UUID id){
        String responseMessage = adminService.activateDeactivateUser(id,false);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PostMapping(path = "/addMetaDataField")
    public ResponseEntity<?> addMetadataField(@RequestParam("field") String fieldName){
        String responseMessage = adminService.addMetadataField(fieldName);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllMetadatField")
    public ResponseEntity<?> getAllMetadataField(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") String order){
        List<CategoryMetaDataFieldDTO> data = adminService.getAllCategoryMetadataField(page, size, sortBy, order);
        final String responseMessage = "MetaData Field fetched Successfully";
        return new ResponseEntity<>(new ResponseBody<>(data,responseMessage), HttpStatus.OK);

    }

    @PostMapping(path = "/createCategory")
    public ResponseEntity<?> createNewCategory(@RequestBody CategoryDTO categoryDTO){
        String responseMessage = adminService.createNewCategory(categoryDTO);

        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.CREATED);
    }

    @GetMapping(path = "/getCategory/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") UUID categoryId){
        CategoryDTO responseData = adminService.getCategory(categoryId);
        final String responseMessage = "Category details fetched successfully";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage), HttpStatus.OK);
    }

    @GetMapping(path = "/getCategory")
    public ResponseEntity<?> getAllCategories(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "ASC") String order){
        List<CategoryDTO> data = adminService.getAllCategories(page, size, sortBy, order);
        final String responseMessage = "Categories fetched Successfully";
        return new ResponseEntity<>(new ResponseBody<>(data,responseMessage), HttpStatus.OK);
    }

    @PatchMapping(path = "/updateCategory/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") UUID categoryId, @RequestParam("categoryName") String categoryName){
        String responseMessage = adminService.updateCategory(categoryId,categoryName);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.OK);
    }

    @PostMapping(path = "/addMetaDataFieldValue")
    public ResponseEntity<?> addMetadataFieldValue(@RequestBody CategoryMetadataFieldValueDTO categoryMetadataFieldValueDTO){
        String responseMessage = adminService.addMetaFieldValue(categoryMetadataFieldValueDTO);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/updateMetaDataFieldValue")
    public ResponseEntity<?> updateMetadataFieldValue(@RequestBody CategoryMetadataFieldValueDTO categoryMetadataFieldValueDTO){
       String responseMessage = adminService.updateMetaFieldValue(categoryMetadataFieldValueDTO);
       return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PostMapping(path = "/activateProduct")
    public ResponseEntity<?> activateProduct(@RequestParam("productId") UUID productId){
        boolean activate = true;
        String responseMessage =  productService.activateDeactivateProduct(productId,activate);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.OK);
    }

    @PostMapping(path = "/deactivateProduct")
    public ResponseEntity<?> deactivateProduct(@RequestParam("productId") UUID productId){
        boolean activate = false;
        String responseMessage = productService.activateDeactivateProduct(productId,activate);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @GetMapping(path = "/product/{productId}")
    public ResponseEntity<?> getProduct(HttpServletRequest httpServletRequest, @PathVariable("productId") UUID productId){
        ProductViewDTO responseData = productService.getProduct(productId,httpServletRequest);
        String responseMessage = "Product details fetched Successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllProduct")
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name = "order",defaultValue = "ASC") String order, @RequestParam(name = "query") String query, @RequestParam("id") UUID id){
        List<ProductViewDTO> responseData = productService.getAllProductByQuery(page,size,sortBy,order,query,id);
        String responseMessage = "Product List fetched Successfully!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.OK);
    }

    @GetMapping(path = "/getAllOrders")
    public ResponseEntity<?> getAllOrders(HttpServletRequest httpServletRequest, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name = "order", defaultValue = "ASC") String order){
        List<OrderProductDTO> responseData  = orderService.viewAllOrdersForAdminSeller(httpServletRequest,page,size,sortBy,order,true);
        String responseMessage = "All orders fethced Successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage), HttpStatus.OK);
    }

    @PatchMapping(path = "/changeStatus/{orderProductId}")
    public ResponseEntity<?> changeStatus(HttpServletRequest httpServletRequest, @PathVariable("orderProductId") UUID orderProductId, @RequestParam("fromStatus") OrdersStatus fromStatus, @RequestParam("toStatus") OrdersStatus toStatus){
        String responseMessage = orderService.changeOrderStatus(httpServletRequest,orderProductId,fromStatus,toStatus,true);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.OK);
    }


}
