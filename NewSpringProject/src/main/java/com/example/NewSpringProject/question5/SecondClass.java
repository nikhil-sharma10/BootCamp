package com.example.NewSpringProject.question5;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SecondClass implements Implementable {

    @Override
    public void display() {
        System.out.println("This is the display method of Second Class....");
    }
}
