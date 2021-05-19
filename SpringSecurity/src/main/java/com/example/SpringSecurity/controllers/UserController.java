package com.example.SpringSecurity.controllers;

import com.example.SpringSecurity.models.AuthenticationRequest;
import com.example.SpringSecurity.models.AuthenticationResponse;
import com.example.SpringSecurity.repository.UserRepo;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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



    @RequestMapping("/hello")
    public String firstPage(){
        return "Hello Welcome home!!!";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try{
            System.out.println("Hello there!!!!");
            System.out.println(authenticationRequest.getUserName() + " " + authenticationRequest.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
            );

        }
        catch (BadCredentialsException e){
            throw new Exception("Invalid username or password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        System.out.println("user name fetched..." + userDetails.getUsername());
        System.out.println("Getting token....");

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody AuthenticationRequest authenticationRequest){
        User user = new User();
        user.setName(authenticationRequest.getUserName());
        user.setPassword(authenticationRequest.getPassword());
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

}
