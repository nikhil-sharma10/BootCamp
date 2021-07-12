package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.dto.LogInDTO;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationService extends BaseService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    public ResponseBody<String,String> authenticate(LogInDTO logInDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInDTO.getEmail(),logInDTO.getPassword())
        );

        final String jwt =  jwtUtil.generateToken(logInDTO.getEmail());

        ResponseBody<String,String> responseBody = new ResponseBody<>(jwt,"User Logged In Successfully");
        return responseBody;
    }
}
