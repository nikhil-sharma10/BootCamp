package com.example.NewSpringProject.question5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImplementingClass {

    @Autowired
    private Implementable implementable;

    public void display(){
        System.out.println("This is the display method of Implementing class...");
        implementable.display();
    }
}
