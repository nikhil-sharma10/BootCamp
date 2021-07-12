package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.convertor.CustomerTransformer;
import com.bootcampProject.BootcampProject.convertor.UserTransformer;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.Seller;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.CustomerDTO;
import com.bootcampProject.BootcampProject.dto.SellerDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.exceptions.NotFoundException;
import com.bootcampProject.BootcampProject.repository.CustomerRepository;
import com.bootcampProject.BootcampProject.repository.SellerRepository;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService extends BaseService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CustomerTransformer customerTransformer;

    @Autowired
    SendMail sendMail;

    public ResponseBody<List<Customer>, String> getAllRegisteredCustomer(Integer page, Integer size, String sortBy){
        List<Customer> customers = customerRepository.findAll(PageRequest.of(page,size, Sort.by(sortBy))).getContent();
//        List<CustomerDTO> customerDTOList = customerTransformer.toDTO(customers);
        ResponseBody<List<Customer>,String> responseBody = new ResponseBody<>(customers,"Customers Fetched Successfully");
        return responseBody;
    }

    public ResponseBody<List<Seller>, String> getAllRegisteredSeller(Integer page, Integer size, String sortBy){
        List<Seller> sellers = sellerRepository.findAll(PageRequest.of(page,size,Sort.by(sortBy))).getContent();
        ResponseBody<List<Seller>,String> responseBody = new ResponseBody<>(sellers,"Sellers Fetched Successfully");
        return responseBody;

    }

    public ResponseBody<Customer,String> activateCustomer(UUID id){
        Users customer = userRepository.findById(id).get();
        ResponseBody<Customer,String> responseBody = new ResponseBody<>();
        if(customer != null){
            if(customer.isActive()){
                responseBody.setData(null);
                responseBody.setMessage("Customer Account Activated Successfully");
                return responseBody;
            }
            else {
                customer.setActive(true);
                userRepository.save(customer);
                sendMail.sendMail("Account Activation","Your account has been activated",customer.getEmail());
                responseBody.setData(null);
                responseBody.setMessage("Customer Account Activated Successfully");
                return responseBody;
            }
        }
        else{
            throw new NotFoundException("Customer not found");
        }
    }

    public ResponseBody<Seller,String> activateSeller(UUID id){
        Users seller = userRepository.findById(id).get();
        ResponseBody<Seller,String> responseBody = new ResponseBody<>();
        if(seller != null){
            if(seller.isActive()){
                responseBody.setData(null);
                responseBody.setMessage("Seller Account Activated Successfully");
                return responseBody;
            }
            else {
                seller.setActive(true);
                userRepository.save(seller);
                sendMail.sendMail("Account Activation","Your account has been activated",seller.getEmail());
                responseBody.setData(null);
                responseBody.setMessage("Seller Account Activated Successfully");
                return responseBody;
            }
        }
        else{
            throw new NotFoundException("Seller not found");
        }
    }

    public ResponseBody<Customer,String> deactivateCustomer(UUID id){
        Users customer = userRepository.findById(id).get();

        if(customer != null){
            ResponseBody<Customer,String> responseBody = new ResponseBody<>();
            if(customer.isActive()){
                customer.setActive(false);
                userRepository.save(customer);
                sendMail.sendMail("Account De-Activation","Your account has been de-activated",customer.getEmail());
                responseBody.setData(null);
                responseBody.setMessage("Customer Account De-Activated Successfully");
                return responseBody;
            }
            else{
                responseBody.setData(null);
                responseBody.setMessage("Customer Account De-Activated Successfully");
                return responseBody;
            }
        }
        else{
            throw new NotFoundException("Customer not found");
        }
    }

    public ResponseBody<Seller,String> deactivateSeller(UUID id){
        Users seller = userRepository.findById(id).get();

        if(seller != null){
            ResponseBody<Seller,String> responseBody = new ResponseBody<>();
            if(seller.isActive()){
                seller.setActive(false);
                userRepository.save(seller);
                sendMail.sendMail("Account De-Activation","Your account has been de-activated",seller.getEmail());
                responseBody.setData(null);
                responseBody.setMessage("Seller Account De-Activated Successfully");
                return responseBody;
            }
            else{
                responseBody.setData(null);
                responseBody.setMessage("Seller Account De-Activated Successfully");
                return responseBody;
            }
        }
        else{
            throw new NotFoundException("Seller not found");
        }
    }
}
