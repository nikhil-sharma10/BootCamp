package com.example.SpringDataJPAExercise.repos;

import com.example.SpringDataJPAExercise.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface EmployeeRepo extends PagingAndSortingRepository<Employee,Integer> {

    List<Employee> findByName(String name);
    List<Employee> findByNameLike(String name);
    List<Employee> findByAgeBetween(int age1, int age2);
    List<Employee> findByAgeIn(List<Integer> age, Pageable pageable);



}
