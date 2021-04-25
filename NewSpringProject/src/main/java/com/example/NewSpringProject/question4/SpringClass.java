package com.example.NewSpringProject.question4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringClass {

    public static void main(String[] args) {
        ApplicationContext newContext = SpringApplication.run(SpringClass.class,args);
        ConfigurableApplicationContext context = SpringApplication.run(SpringClass.class,args);
        ImplementingClass implementingClass = context.getBean(ImplementingClass.class);
        System.out.println("Application name: " + newContext);
        implementingClass.display();
    }
}
