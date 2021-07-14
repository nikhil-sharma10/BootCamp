package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserAttemptService {

    @Autowired
    private UserRepository userRepository;

    public void lockUser(Users user){
            user.setAccountNonLocked(false);
            userRepository.save(user);
    }

    public void resetAttempt(Users user){
        user.setFailedLoginAttempt(0);
        userRepository.save(user);
    }


    public void increaseUserAttempt(Users user){
        user.setFailedLoginAttempt(user.getFailedLoginAttempt() + 1);
        userRepository.save(user);
    }

}
