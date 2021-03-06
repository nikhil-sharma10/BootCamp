package com.example.SpringSecurity.user;

import com.example.SpringSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<User> userList = userRepository.findUserByName(userName);
        UserDetails userDetails = null;
        for (User user: userList) {
            if(user.getName().equals(userName))
                userDetails = user;
        }
        return userDetails;
//        return new User("Nikhil Sharma","Nikhil@1999",new ArrayList<>());
    }
}
