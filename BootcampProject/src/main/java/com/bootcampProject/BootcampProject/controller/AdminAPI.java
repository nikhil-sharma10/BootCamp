package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.Seller;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.CustomerDTO;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/admin")
public class AdminAPI {

    @Autowired
    private AdminService adminService;


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
        ResponseBody<Customer,String> responseBody = adminService.activateCustomer(id);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }

    @PatchMapping(path = "/activate-seller")
    public ResponseEntity<?> activateSeller(@RequestParam(value = "id") UUID id){
        ResponseBody<Seller,String> responseBody = adminService.activateSeller(id);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }

    @PatchMapping(path = "/deactivate-customer")
    public ResponseEntity<?> deactivateCustomer(@RequestParam(value = "id") UUID id){
        ResponseBody<Customer,String> responseBody = adminService.deactivateCustomer(id);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }

    @PatchMapping(path = "/deactivate-seller")
    public ResponseEntity<?> deactivateSeller(@RequestParam(value = "id") UUID id){
        ResponseBody<Seller,String> responseBody = adminService.deactivateSeller(id);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }

}
