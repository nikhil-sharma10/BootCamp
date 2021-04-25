package com.example.NewSpringProject.question2;



public class ImplementingClass {

    private Implementable implementable;

    public ImplementingClass(Implementable implementable) {
        this.implementable = implementable;
    }

    public void display(){
        System.out.println("This is the display method of Implementing class...");
        implementable.display();
    }
}
