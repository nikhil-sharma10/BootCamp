package com.example.NewSpringProject.question6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringClass {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringClass.class,args);
        ImplementingClass implementingClass = context.getBean(ImplementingClass.class);
        implementingClass.display();
    }
}
