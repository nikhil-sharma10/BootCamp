package com.example.RestFulServicesExercise.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    List<User> userList = new ArrayList<>();
    private static int userCount=0;

    public List<User> getAllUser(){
        return userList;
    }

    public User saveUser(User user){
        if(user.getId() == null){
            user.setId(++userCount);
        }
        userList.add(user);
        return user;
    }
}
