package com.bootcampProject.BootcampProject.controller;


import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;
    
    @GetMapping(path = "/logout")
    public ResponseEntity<?> doLogout(@RequestParam(value = "token") String token){
        logoutService.doLogout(token);
        return new ResponseEntity<ResponseBody<Users,String>>(new ResponseBody<Users,String>(null,"Logged out successfully!!!"), HttpStatus.OK);
    }
}
