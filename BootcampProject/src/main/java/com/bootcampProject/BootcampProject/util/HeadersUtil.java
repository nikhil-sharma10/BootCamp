package com.bootcampProject.BootcampProject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeadersUtil {

    @Autowired
    private JwtUtil jwtUtil;


    public String getUserName(HttpServletRequest request){
        final String authorizationHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUserName(token);
        }
        return userName;
    }

}
