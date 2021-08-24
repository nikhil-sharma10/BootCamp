package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.CUserDetails;
import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.convertor.CustomerTransformer;
import com.bootcampProject.BootcampProject.convertor.SellerTransformer;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.CustomerDTO;
import com.bootcampProject.BootcampProject.dto.SellerDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.AddressRepository;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.repository.UserRoleRepository;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("userDetailsService")
public class CUserDetailsService extends BaseService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    SendMail sendMail;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    SellerTransformer sellerTransformer;

    @Autowired
    CustomerTransformer customerTransformer;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AddressRepository addressRepository;

    public ResponseBody<Customer,String> registerCustomer(CustomerDTO customerDTO){
        Customer customer = customerTransformer.fromDTO(customerDTO);
        String pass = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(pass);
        Role role = new Role("ROLE_CUSTOMER");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUsers(customer);
        customer.setAccountNonLocked(true);
        customer.setDeleted(false);
        customer.setFailedLoginAttempt(0);
        Set<Address> addressSet = new HashSet<>();
        for(Address address : customerDTO.getAddresses()){
            Address address1 = new Address();
            address1.setCommonAddress(address.getCommonAddress());
            address1.setDeleted(false);
            addressSet.add(address1);
        }
        customer.setAddresses(addressSet);
        userRoleRepository.save(userRole);
        final String jwtToken = jwtUtil.generateToken(customer.getEmail());
        sendMail.sendMail("Account Activation Token","To confirm your account please click here: http://localhost:8081/register/confirm-account?token=" + jwtToken,customer.getEmail());
        ResponseBody<Customer,String> responseBody = new ResponseBody<>();
        responseBody.setData(customer);
        responseBody.setMessage("Customer has been created and Activation Mail has been sent to " + customer.getEmail() + " Activation token: " + jwtToken);
        return responseBody;
    }

    public ResponseBody<Seller, String> registerSeller(SellerDTO sellerDTO){
        Seller seller = sellerTransformer.fromDTO(sellerDTO);
        ResponseBody<Seller,String> responseBody = new ResponseBody<>();

            String pass = passwordEncoder.encode(seller.getPassword());
            seller.setPassword(pass);
            Role roles = new Role("ROLE_SELLER");
            UserRole userRole = new UserRole();
            userRole.setRole(roles);
            userRole.setUsers(seller);
            seller.setActive(true);
            seller.setDeleted(false);
            seller.setAccountNonLocked(true);
            seller.setFailedLoginAttempt(0);
            Set<Address> addressSet = new HashSet<>();
            Address address = new Address();
            address.setCommonAddress(sellerDTO.getCompanyAddress().getCommonAddress());
            address.setDeleted(false);
            addressSet.add(address);
            seller.setAddresses(addressSet);
            userRoleRepository.save(userRole);
            responseBody.setData(seller);
            responseBody.setMessage("Seller has been created Successfully");
            return responseBody;
        }

    public Users registerAdmin(Users users){
        String pass = passwordEncoder.encode(users.getPassword());
        users.setPassword(pass);
        users.setActive(true);
        users.setDeleted(false);
        Role role = new Role("ADMIN");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUsers(users);
        userRoleRepository.save(userRole);


        return users;
//        return mappingJacksonValue;
    }

    @Override
    public CUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email);

        if(users != null){
            if(users.isActive()) {
                if (users.isAccountNonLocked()) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(users.getUserRole().getRole());
                    return new CUserDetails(users.getEmail(), users.getPassword(), users.isActive(), true, true, users.isAccountNonLocked(), authorities, users.getId());
                } else {
                    throw new RuntimeException("User Account is locked...");
                }
            }
            else{
                throw new RuntimeException("User is not active...");
            }
        }
        else
        {
            throw new UsernameNotFoundException("No user found with userName: " + email);
        }
    }
}
