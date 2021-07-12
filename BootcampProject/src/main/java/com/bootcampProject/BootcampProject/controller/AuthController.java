package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.Seller;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.CustomerDTO;
import com.bootcampProject.BootcampProject.dto.LogInDTO;
import com.bootcampProject.BootcampProject.dto.SellerDTO;
import com.bootcampProject.BootcampProject.service.AuthenticationService;
import com.bootcampProject.BootcampProject.service.CUserDetailsService;
import com.bootcampProject.BootcampProject.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import com.bootcampProject.BootcampProject.common.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;


@RestController
@RequestMapping(path = "/register")
public class AuthController {

    @Autowired
    CUserDetailsService userDetailsService;

    @Autowired
    ValidationService validationService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/seller")
    public ResponseEntity<?> createSeller(@Valid @RequestBody SellerDTO sellerDTO){
        validationService.validateSeller(sellerDTO);
        ResponseBody<Seller,String> responseBody = userDetailsService.registerSeller(sellerDTO);
        return new ResponseEntity<>(responseBody,HttpStatus.CREATED);
    }

    @PostMapping(path = "/customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        validationService.validateCustomer(customerDTO);
        ResponseBody<Customer,String> responseBody = userDetailsService.registerCustomer(customerDTO);
        return new ResponseEntity<>(responseBody,HttpStatus.CREATED);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LogInDTO logInDTO){
        validationService.validateUser(logInDTO);
        ResponseBody<String,String> responseBody = authenticationService.authenticate(logInDTO);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }
//
//    @PutMapping(path = "/confirm-account")
//    public ResponseEntity<?> confirmCustomerAccount(@RequestParam("token") String token){
//        Users customer = validationService.activateCustomer(token);
//        return new ResponseEntity<>(new ResponseBody<>(customer,"Customer Activated Successfully"),HttpStatus.OK);
//    }
//
//
//    @PostMapping(path = "/admin")
//    public ResponseEntity<?> createAdmin(@RequestBody Users users) throws JsonProcessingException {
//        String message = "User Created Successfully!!!!";
//
//        Users createdUsers =  userDetailsService.registerAdmin(users);
//
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","email","firstName","middleName","lastName","addresses");
//        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("User-Filter",filter);
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
//        mappingJacksonValue.setFilters(filterProvider);
//        Object response = mappingJacksonValue.getValue();
//        ResponseBody<?,String> responseBody = new ResponseBody<>(response,message);
//
//        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.CREATED);
//    }
}
