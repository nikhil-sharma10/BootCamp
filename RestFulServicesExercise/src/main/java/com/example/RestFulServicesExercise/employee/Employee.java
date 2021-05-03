package com.example.RestFulServicesExercise.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

@ApiModel(description = "Employee Details")
public class Employee {

    private Integer id;

    @Size(min=2)
    @ApiModelProperty(notes="Name of the employee should be at least of 2 characters")
    private String name;
    private int age;


    public Employee(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
