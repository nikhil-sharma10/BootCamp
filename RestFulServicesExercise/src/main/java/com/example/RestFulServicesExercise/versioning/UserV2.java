package com.example.RestFulServicesExercise.versioning;

public class UserV2 {

    private Name name;
    private int age;

    public UserV2(Name name, int age) {
        this.name = name;
        this.age = age;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
