package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.BlockedToken;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.exceptions.NotFoundException;
import com.bootcampProject.BootcampProject.repository.BlockedTokenRepository;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService extends BaseService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockedTokenRepository blockedTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private UserAttemptService userAttemptService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Modifying
    public ResponseBody<String,String> forgotPasswordToken(String email){
        Users user = userRepository.findByEmail(email);

        if(user != null){
            if(user.isActive()){
                BlockedToken blockedToken = blockedTokenRepository.findByUser(user);

                if(blockedToken != null){
                    blockedTokenRepository.delete(blockedToken);
                }
                String token = jwtUtil.generateToken(email);
                sendMail.sendMail("Reset Password","Reset your password using link: http://localhost:8081/reset-password?token=" + token,email);
                blockedTokenRepository.save(new BlockedToken(token,true,user));
                return new ResponseBody<String,String>(token,"Reset Password URL generated successfully");
            }
            else {
                throw new BadRequestException("User is not active");
            }
        }
        else {
            throw new NotFoundException("User Not Found");
        }
    }

    @Modifying
    public ResponseBody<Users,String> resetPassword(String token, String password, String confirmPassword){
        if(!jwtUtil.isTokenExpired(token)){
            if(jwtUtil.isResetToken(token)){
                if(password.equals(confirmPassword)){
                    String email = jwtUtil.extractUserName(token);
                    Users user = userRepository.findByEmail(email);
                    String encryptedPassword = passwordEncoder.encode(password);
                    user.setPassword(encryptedPassword);
                    user.setActive(true);
                    user.setAccountNonLocked(true);
                    userAttemptService.resetAttempt(user);
                    sendMail.sendMail("Password Changed","Your password has been reset",email);
                    blockedTokenRepository.delete(blockedTokenRepository.findByToken(token));
                    return new ResponseBody<>(null,"Password Reset Successfully && Account is Unlocked");
                }
                else {
                    throw new BadRequestException("Password and Confirm Password are not same");
                }
            }
            else{
                throw new BadRequestException("Invalid token");
            }
        }
        else {
            throw new BadRequestException("Token is expired");
        }
    }
}
