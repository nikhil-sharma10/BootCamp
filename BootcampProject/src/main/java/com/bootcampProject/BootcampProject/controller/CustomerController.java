package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Address;
import com.bootcampProject.BootcampProject.domain.CommonAddress;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.dto.AddressDTO;
import com.bootcampProject.BootcampProject.dto.ProfileDTO;
import com.bootcampProject.BootcampProject.service.CommonService;
import com.bootcampProject.BootcampProject.service.CustomerService;
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




}
