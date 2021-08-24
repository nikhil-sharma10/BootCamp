package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.ProfileDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.*;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class CommonService extends BaseService {

    @Autowired
    private HeadersUtil headersUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ImageTableRepository imageTableRepository;

    public List<CommonAddress> getAddress(HttpServletRequest request){
        String userName = headersUtil.getUserName(request);

        Users user = userRepository.findByEmail(userName);

        if(user != null){
            Set<Address> addresses = user.getAddresses();
            List<CommonAddress> addressList =  new ArrayList<>();
                for (Address address: addresses){
                    if(!address.isDeleted())
                    addressList.add(address.getCommonAddress());
                }
                return addressList;
        }
        else {
            throw new BadRequestException("Seller not found");
        }
    }


    public CommonAddress updateAddress(UUID addressId, CommonAddress updatedAddress){
        Address address = addressRepository.findById(addressId).get();
        if(address != null && !address.isDeleted()){
            address.setCommonAddress(updatedAddress);
            addressRepository.save(address);
        }
        else{
            throw new BadRequestException("Address not found");
        }
        return address.getCommonAddress();
    }

    public String deleteAddress(HttpServletRequest request,UUID addressId){
        String userName = headersUtil.getUserName(request);
        Users user = userRepository.findByEmail(userName);
        String responseMessage = "";

        if (user.getUserRole().getRole().getAuthority() != "ROLE_SELLER") {
            Address address = addressRepository.findById(addressId).get();

            if (address != null && !address.isDeleted()) {
                address.setDeleted(true);
                addressRepository.save(address);
                responseMessage = "Address Deleted Successfully";
            } else {
                throw new BadRequestException("Address not found");
            }

        }
        return responseMessage;
    }

    public String addAddress(HttpServletRequest request, CommonAddress newAddress){
        String userName = headersUtil.getUserName(request);

        Users user = userRepository.findByEmail(userName);

        String responseMessage = "";

        if(user.getUserRole().getRole().getAuthority() != "ROLE_SELLER"){
            Address address = new Address();
            address.setCommonAddress(newAddress);
            address.setDeleted(false);
            Set<Address> addressSet = new HashSet<>(user.getAddresses());
            addressSet.add(address);
            user.setAddresses(addressSet);
            userRepository.save(user);
            responseMessage = "Address Added Successfully";
        }
        else{
            throw new BadRequestException("User can not add multiple addresses");
        }
        return responseMessage;
    }

    public String updatePassword(HttpServletRequest request, String password, String confirmPassword){
        String userName = headersUtil.getUserName(request);
        String responseMessage = "";

        Users user = userRepository.findByEmail(userName);

        if(password.equals(confirmPassword)){
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            responseMessage = "Password Updated Successfully";
        }
        else {
            throw new BadRequestException("Password and Confirm Password do not match");
        }
        return responseMessage;
    }

    public String updateProfile(HttpServletRequest request, String sentProfile, MultipartFile file) throws JsonProcessingException {
        String userName = headersUtil.getUserName(request);
        String responseMessage = null;
        Seller seller = null;
        Customer customer = null;
        Users user = userRepository.findByEmail(userName);
        boolean isSeller = user.getUserRole().getRole().getAuthority() == "ROLE_SELLER";

        if(isSeller){
            seller = (Seller) user;
        }
        else {
            customer = (Customer) user;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ProfileDTO profile = objectMapper.readValue(sentProfile,ProfileDTO.class);

        if(profile != null || file != null){
            if(profile.getContact() != null && isSeller){
                seller.setCompanyContact(profile.getContact());
            }
            else if(profile.getContact() != null){
                customer.setContact(profile.getContact());
            }

            if(profile.getFirstName() != null && isSeller)
                seller.setFirstName(profile.getFirstName());
            else if(profile.getFirstName() != null)
                customer.setFirstName(profile.getFirstName());

            if(profile.getLastName() != null && isSeller)
                seller.setLastName(profile.getLastName());
            else if(profile.getLastName() != null)
                customer.setLastName(profile.getLastName());

            if(file != null){
                boolean isProfile = true;
                imageService.storeFile(file,user,isProfile,null);
            }

            responseMessage = "Profile Updated Successfully";
        }

        if ((isSeller)) {
            sellerRepository.save(seller);
        } else {
            customerRepository.save(customer);
        }

        return responseMessage;
    }


    public ProfileDTO getProfile(HttpServletRequest request) throws Exception {
        String userName = headersUtil.getUserName(request);
        Users user = userRepository.findByEmail(userName);
        boolean isSeller = user.getUserRole().getRole().getAuthority() == "ROLE_USER";

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName(user.getFirstName());
        profileDTO.setLastName(user.getLastName());
        profileDTO.setId(user.getId());
        profileDTO.setIsActive(user.isActive());
        if(isSeller){
            Seller seller = (Seller) user;
            profileDTO.setContact(seller.getCompanyContact());
        }
        else {
            Customer customer = (Customer) user;
            profileDTO.setContact(customer.getContact());
        }
        ImageTable image = imageTableRepository.findByUserId(user);
        if(image != null)
            profileDTO.setImage(imageService.loadImageAsResource(image.getFileName()));
        else{
            profileDTO.setImage(null);
        }

        return profileDTO;
    }


}
