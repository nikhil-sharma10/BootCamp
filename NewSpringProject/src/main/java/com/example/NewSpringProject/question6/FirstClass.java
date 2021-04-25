package com.example.NewSpringProject.question6;

import org.springframework.stereotype.Component;

@Component
public class FirstClass implements Implementable {

    @Override
    public void display() {
        System.out.println("This is the display method of First Class....");
    }
}
