package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Address;
import com.bootcampProject.BootcampProject.domain.CommonAddress;
import com.bootcampProject.BootcampProject.domain.Customer;
import com.bootcampProject.BootcampProject.dto.ProductDTO;
import com.bootcampProject.BootcampProject.dto.ProductViewDTO;
import com.bootcampProject.BootcampProject.dto.ProfileDTO;
import com.bootcampProject.BootcampProject.service.CommonService;
import com.bootcampProject.BootcampProject.service.ProductService;
import com.bootcampProject.BootcampProject.service.SellerService;
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
@RequestMapping("/seller")
public class SellerController {


    @Autowired
    private CommonService commonService;

    @Autowired
    private ProductService productService;

    @GetMapping("/getAddress")
    public ResponseEntity<?> getAddresses(HttpServletRequest request){
        List<CommonAddress> addressList = commonService.getAddress(request);
        final String responseMessage = "Address fetched Successfully";
        return new ResponseEntity<>(new ResponseBody<>(addressList,responseMessage), HttpStatus.OK);
    }

    @PatchMapping("/{addressId}/updateAddress")
    public ResponseEntity<?> updateAddress(@PathVariable("addressId") UUID addressId, CommonAddress address){
        CommonAddress updatedAddress = commonService.updateAddress(addressId,address);
        final String responseMessage = "Address Updated Successfully";
        return new ResponseEntity<>(new ResponseBody<>(updatedAddress,responseMessage),HttpStatus.OK);
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword){
        final String responseMessage = commonService.updatePassword(request,password,confirmPassword);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/updateProfile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestPart(value = "profile",required = false) String profileDTO, @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        String responseMessage = commonService.updateProfile(request,profileDTO,file);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(HttpServletRequest httpServletRequest, @RequestBody ProductDTO productDTO){
        String responseMessage = productService.addProduct(productDTO,httpServletRequest);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(HttpServletRequest httpServletRequest, @RequestParam("productId") UUID productId){
        String responseMessage = productService.deleteProduct(productId,httpServletRequest);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.OK);
    }

    @PostMapping("/addProductVariation")
    public ResponseEntity<?> addProductVariation(@RequestPart(value = "productVariationImage") MultipartFile file, @RequestPart(value = "productVariationData") String productVariationData) throws JsonProcessingException {
        String responseMessage = productService.addProductVariation(file,productVariationData);
        return new ResponseEntity<>(new ResponseBody<>(null,responseMessage),HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProduct(HttpServletRequest httpServletRequest, @PathVariable("productId") UUID productId){
        ProductViewDTO data = productService.getProduct(productId,httpServletRequest);
        String responseMessage = "Product fetched successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(data,responseMessage), HttpStatus.OK);
    }


    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct(HttpServletRequest httpServletRequest, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name = "order", defaultValue = "ASC") String order){
        List<ProductViewDTO> responseData = productService.getAllProducts(httpServletRequest,page,size,sortBy,order);
        String responseMessage = "Products fetched successfully!!!";
        return new ResponseEntity<>(new ResponseBody<>(responseData,responseMessage),HttpStatus.OK);
    }



}
