package com.example.JPQLAndNativeSQLExercise.repos;

import com.example.JPQLAndNativeSQLExercise.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("select firstName, lastName from Employee where salary > (select avg(salary) from Employee)")
    public List<Object []> findAllEmployee(Sort sort);

    @Modifying
    @Transactional
    @Query(value = "update Employee set salary =:increasedSalary where salary<:avgSalary")
    public void updateSalary(@Param("increasedSalary") int increasedSalary, @Param("avgSalary") Integer avgSalary);

    @Query(value = "select empid,empfirstname,empage from employee where emplastname LIKE '%Singh'",nativeQuery = true)
    public List<Object []> nativeQuery();

    @Modifying
    @Query(value = "delete from employee where empage>:age",nativeQuery = true)
    public void deleteEmployee(@Param("age") int age);

    @Query("select avg(salary) from Employee")
    public Object[] avgSalaryEmployee();


 }
