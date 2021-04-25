package com.example.NewSpringProject.question2;

public class LooselyCoupled {

    public static void main(String[] args) {

        Implementable implementable1  = new FirstClass();
        Implementable implementable2 = new SecondClass();

        ImplementingClass implementingClass1 = new ImplementingClass(implementable1);
        ImplementingClass implementingClass2 = new ImplementingClass(implementable2);

        implementingClass1.display();
        implementingClass2.display();
    }
}
