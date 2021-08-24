package com.bootcampProject.BootcampProject.controller;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/user")
@RestController
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping(path = "/forgotPassword")
    public ResponseEntity<?> forgotPassword(@Valid @RequestParam(value = "email") String email){
        ResponseBody<String,String> responseBody = passwordService.forgotPasswordToken(email);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PatchMapping(path = "/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestParam(value = "token") String token, @RequestParam(value = "password") String password, @RequestParam(value = "confirmPassword") String confirmPassword){
        ResponseBody<Users,String> responseBody = passwordService.resetPassword(token,password,confirmPassword);
        return new ResponseEntity<>(responseBody,HttpStatus.OK);
    }
}
