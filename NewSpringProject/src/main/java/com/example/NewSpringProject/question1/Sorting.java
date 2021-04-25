package com.example.NewSpringProject.question1;

public class Sorting {

    Searching searching;

    public Sorting(){
        searching = new Searching();
    }

    public void display(){
        System.out.println("This is the display method of Sorting....");
        System.out.println("Calling Tightly coupled object here...");
        searching.display();
    }
}
