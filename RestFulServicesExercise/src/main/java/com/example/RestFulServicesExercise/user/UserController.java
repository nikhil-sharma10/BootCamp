package com.example.RestFulServicesExercise.user;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpResponse;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    public ResponseEntity<Object> saveuser(@RequestBody User user){
        User saveduser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
        return new ResponseEntity<>(saveduser, HttpStatus.CREATED);
    }
    @PostMapping("/user/dynamicFiltering")
    public ResponseEntity<Object> saveUserDynamicFiltering(@RequestBody User user){
        User saveduser = service.saveUser(user);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
        MappingJacksonValue mapping = new MappingJacksonValue(saveduser);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","id");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter",filter);
        mapping.setFilters(filterProvider);

        return new ResponseEntity<>(mapping, HttpStatus.CREATED);
    }
}
