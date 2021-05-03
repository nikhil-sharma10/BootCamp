package com.example.RestFulServicesExercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWord(){
        return "Hello World";
    }
    
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World!!!");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World %s", name));
    }

    @GetMapping(path="/hello-world-internationalized/{name}")
    public String helloWorldInternationalized(@PathVariable String name){
        String [] userName = new String[]{name};
        return messageSource.getMessage("good.morning.message", userName, LocaleContextHolder.getLocale());
    }

}
