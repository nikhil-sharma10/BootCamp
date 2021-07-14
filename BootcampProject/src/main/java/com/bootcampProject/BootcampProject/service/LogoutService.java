package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.domain.BlockedToken;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.BlockedTokenRepository;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService extends BaseService {
    @Autowired
    private BlockedTokenRepository blockedTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void doLogout(String token){
        if(jwtUtil.extractUserName(token) != null){
            if(!jwtUtil.isTokenExpired(token)){
                if(!jwtUtil.isBlockedToken(token)){
                    BlockedToken blockedToken = new BlockedToken(token,true);
                    blockedTokenRepository.save(blockedToken);
                }
                else {
                    throw new BadRequestException("Token is already blocked!!");
                }
            }
            else{
                throw new BadRequestException("Token is expired!!");
            }
        }
        else{
            throw new BadRequestException("Token is not valid!!");
        }
    }
}
