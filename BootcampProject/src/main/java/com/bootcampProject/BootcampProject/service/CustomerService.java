package com.bootcampProject.BootcampProject.service;


import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.ProfileDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.exceptions.NotFoundException;
import com.bootcampProject.BootcampProject.repository.AddressRepository;
import com.bootcampProject.BootcampProject.repository.CustomerRepository;
import com.bootcampProject.BootcampProject.repository.ImageTableRepository;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.zip.CheckedOutputStream;

@Component
public class CustomerService extends BaseService{


    @Autowired
    private HeadersUtil headersUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageTableRepository imageTableRepository;

    @Autowired
    private ImageService imageService;

//    public ResponseBody<List<CommonAddress>,String>  getAddress(HttpServletRequest request){
//        String userName = headersUtil.getUserName(request);
//
//        Customer customer = customerRepository.findByEmail(userName);
//
//        if(customer != null){
//            List<Address> addresses = addressRepository.findByUsers(customer);
//            List<CommonAddress> addressList =  new ArrayList<>();
//            if(addresses != null){
//                for (Address address: addresses){
//                    addressList.add(address.getCommonAddress());
//                }
//
//                return new ResponseBody<List<CommonAddress>,String>(addressList,"Address fetched successfully");
//            }
//            else{
//                return new ResponseBody<List<CommonAddress>,String>(null,"There is no any saved address for this user");
//            }
//
//        }
//        else {
//            throw new NotFoundException("Customer not found");
//        }
//    }

    public ResponseBody<CommonAddress,String> updateAddress(UUID addressId, CommonAddress updatedAddress){
        Address address = addressRepository.findById(addressId).get();
        if(address != null && !address.isDeleted()){
            address.setCommonAddress(updatedAddress);
            addressRepository.save(address);
        }
        else{
            throw new BadRequestException("Address not found");
        }
        return new ResponseBody<CommonAddress,String>(address.getCommonAddress(),"Address updated Successfully!!");
    }


    public ResponseBody<CommonAddress,String> deleteAddress(UUID addressId){
        Address address = addressRepository.findById(addressId).get();

        if(address != null && !address.isDeleted()){
            address.setDeleted(true);
            addressRepository.save(address);
        }
        else {
            throw new BadRequestException("Address not found");
        }

        return new ResponseBody<CommonAddress, String>(null,"Address Deleted Successfully!!!");
    }

//    public ResponseBody<Address,String> addAddress(HttpServletRequest request, CommonAddress newAddress){
//       String userName = headersUtil.getUserName(request);
//
//        Customer customer = customerRepository.findByEmail(userName);
//
//        Address address = new Address();
//        address.setCommonAddress(newAddress);
//        address.setUsers(customer);
//        addressRepository.save(address);
//
//        return new ResponseBody<>(address,"Address Added Successfully");
//    }

    public ResponseBody<Customer,String> updatePassword(HttpServletRequest request, String password, String confirmPassword){
        String userName = headersUtil.getUserName(request);

        Customer customer = customerRepository.findByEmail(userName);
        if(password.equals(confirmPassword)){
            String encryptedPassword = passwordEncoder.encode(password);
            customer.setPassword(encryptedPassword);
            customerRepository.save(customer);
        }
        else {
            throw new BadRequestException("Password and Confirm Password do not match");
        }
        return new ResponseBody<>(null,"Password Updated Successfully");
    }

    public ProfileDTO getProfile(HttpServletRequest request)  throws Exception{
        String userName = headersUtil.getUserName(request);
        Customer customer = customerRepository.findByEmail(userName);

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName(customer.getFirstName());
        profileDTO.setLastName(customer.getLastName());
        profileDTO.setId(customer.getId());
        profileDTO.setIsActive(customer.isActive());
        profileDTO.setContact(customer.getContact());
        ImageTable image = imageTableRepository.findByUserId(customer);
        if(image != null)
        profileDTO.setImage(imageService.loadImageAsResource(image.getFileName()));
        else{
            profileDTO.setImage(null);
        }

        return profileDTO;
    }

    public String updateProfile(HttpServletRequest request, String sentProfile, MultipartFile file) throws JsonProcessingException {
        String userName = headersUtil.getUserName(request);
        String responseMessage = null;
        Customer seller = customerRepository.findByEmail(userName);
        ObjectMapper objectMapper = new ObjectMapper();
        ProfileDTO profile = objectMapper.readValue(sentProfile,ProfileDTO.class);

        if(profile != null || file != null){
            if(profile.getContact() != null)
                seller.setContact(profile.getContact());
            if(profile.getFirstName() != null)
                seller.setFirstName(profile.getFirstName());
            if(profile.getLastName() != null)
                seller.setLastName(profile.getLastName());
            if(file != null){
                imageService.storeFile(file,seller);
            }
            responseMessage = "Profile Updated Successfully";
        }
        customerRepository.save(seller);
        return responseMessage;
    }

}
