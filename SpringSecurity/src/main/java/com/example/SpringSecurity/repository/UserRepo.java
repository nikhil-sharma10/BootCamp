package com.example.SpringSecurity.repository;

import com.example.SpringSecurity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends CrudRepository<User,Integer> {

    @Query("from User where name =:name")
    public List<User> findUserByName(@Param("name") String name);
}
