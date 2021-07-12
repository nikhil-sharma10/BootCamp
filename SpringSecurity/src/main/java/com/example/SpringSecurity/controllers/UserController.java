package com.example.SpringSecurity.controllers;

import com.example.SpringSecurity.models.AuthenticationRequest;
import com.example.SpringSecurity.models.AuthenticationResponse;
import com.example.SpringSecurity.repository.UserRepo;
import com.example.SpringSecurity.services.EmailService;
import com.example.SpringSecurity.user.MyUserDetailsService;
import com.example.SpringSecurity.user.User;
import com.example.SpringSecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class UserController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;



    @RequestMapping("/hello")
    public String firstPage(){
        return "Hello Welcome home!!!";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());


            System.out.println(authenticationRequest.getUserName() + " " + authenticationRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
            );



//        System.out.println("user name fetched..." + userDetails.getUsername());
        System.out.println("Getting token....");

        final String jwt = jwtTokenUtil.generateToken(authenticationRequest.getUserName());
        System.out.println(jwt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user){
        user.setName(user.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole(user.getRole());
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @RequestMapping("/sendMail")
    public ResponseEntity<?> sendEmail(){
        emailService.sendMail("nitinkumarsharma96@gmail.com");
        return new ResponseEntity<>("Mail sent successfully",HttpStatus.OK);
    }



}
