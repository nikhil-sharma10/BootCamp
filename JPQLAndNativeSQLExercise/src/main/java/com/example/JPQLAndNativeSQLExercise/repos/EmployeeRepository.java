package com.example.JPQLAndNativeSQLExercise.repos;

import com.example.JPQLAndNativeSQLExercise.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("select firstName, lastName from Employee where salary > (select avg(salary) from Employee)")
    public List<Object []> findAllEmployee(Sort sort);

    @Modifying
    @Query(value = "update Employee set salary =:increasedSalary where salary < (select avgSalary from (select avg(salary) as avgSalary from Employee) as tempEmp)")
    public void updateSalary(@Param("increasedSalary") int increasedSalary);

    @Query(value = "select empid,empfirstname,empage from employee where emplastname LIKE '%Singh'",nativeQuery = true)
    public List<Object []> nativeQuery();

    @Modifying
    @Query(value = "delete from employee where empage>:age",nativeQuery = true)
    public void deleteEmployee(@Param("age") int age);


 }
