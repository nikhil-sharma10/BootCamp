package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.dto.LogInDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationService {

    protected static final int MAX_ATTEMPTS = 3;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserAttemptService userAttemptService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public ResponseBody<String,String> authenticate(LogInDTO logInDTO){

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInDTO.getEmail(),logInDTO.getPassword())
            );

            final String jwt =  jwtUtil.generateToken(logInDTO.getEmail());

            return new ResponseBody<>(jwt,"User Logged In Successfully");

        }
        catch (RuntimeException e){
                Users user = userRepository.findByEmail(logInDTO.getEmail());
                if(user.getFailedLoginAttempt() < MAX_ATTEMPTS){
                    userAttemptService.increaseUserAttempt(user);
                    throw new BadRequestException("Bad Credentials");
                }
                else{
                    userAttemptService.lockUser(user);
                    throw new BadRequestException("Your Account has been locked!!");
                }
        }

    }
}
