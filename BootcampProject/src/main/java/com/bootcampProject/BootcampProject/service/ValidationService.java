package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.domain.Seller;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.CustomerDTO;
import com.bootcampProject.BootcampProject.dto.LogInDTO;
import com.bootcampProject.BootcampProject.dto.SellerDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.exceptions.NotFoundException;
import com.bootcampProject.BootcampProject.repository.SellerRepository;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.repository.UserRoleRepository;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService extends BaseService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private JwtUtil jwtUtil;

    public Users activateCustomer(String token){
        String email = jwtUtil.extractUserName(token);
        boolean flag = jwtUtil.validateToken(token,email);
        if(flag){
            Users customer = userRepository.findByEmail(email);
            customer.setActive(true);
            customer.setDeleted(false);
            userRepository.save(customer);
            sendMail.sendMail("Account Activated","Your account has been activated",email);
            return customer;
        }
        else if(jwtUtil.isTokenExpired(token)){
            throw new BadRequestException("Token is expired");
        }
        else{
            throw new BadRequestException("Invalid token");
        }

    }

    public void validateSeller(SellerDTO sellerDTO){
        Users user = userRepository.findByEmail(sellerDTO.getEmail());
        Seller sellerByGST = sellerRepository.findByGst(sellerDTO.getGst());
        Seller sellerByCompanyName = sellerRepository.findByCompanyName(sellerDTO.getCompanyName());

        if(user != null){
            throw new BadRequestException("Email Already Exists");
        }
        else if(!sellerDTO.getPassword().equals(sellerDTO.getConfirmPassword())){
            throw new BadRequestException("Password and Confirm Password didn't match");
        }
        else if(sellerByGST != null){
            throw new BadRequestException("GST number already exist");
        }
        else if(sellerByCompanyName != null){
            throw new BadRequestException("Company name is already registered");
        }
    }

    public void validateCustomer(CustomerDTO customerDto) {

        Users user = userRepository.findByEmail(customerDto.getEmail());
        if (null != user) {
            throw new BadRequestException("Email already exist");
        } else if (!customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
            throw new BadRequestException("Passswords not matched");
        }
    }

    public void validateUser(LogInDTO logInDTO){
        Users user = userRepository.findByEmail(logInDTO.getEmail());

        if(user == null) {
            throw new NotFoundException("User Not Found");
        }
        else if(!user.isActive()){
            throw new BadRequestException("User is not active");
        }
    }
}
