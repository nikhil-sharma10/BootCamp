package com.example.SpringDataJPAExercise;

import com.example.SpringDataJPAExercise.entities.Employee;
import com.example.SpringDataJPAExercise.repos.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringDataJpaExerciseApplicationTests {

	@Autowired
	EmployeeRepo repository;
	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
	}

	@Test
	public void createEmployeeTest(){
		Employee employee = new Employee();
		employee.setAge(21);
		employee.setName("Nikhil Sharma");
		employee.setLocation("Kanpur U.P.");
		employee.setId(1);
		repository.save(employee);
	}

	@Test
	public void readEmployeeTest(){
		Employee employee = repository.findById(1).get();
		System.out.println("Employee details are:: Name: " + employee.getName() + " Age: " + employee.getAge() + " Location: " + employee.getLocation());
	}

	@Test
	public void updateEmployeeTest(){
		Employee employee = repository.findById(1).get();
		employee.setLocation("Ghaziabad U.P.");
		repository.save(employee);
	}

	@Test
	public void deleteEmployeeTest(){
		repository.deleteById(1);
	}

	@Test
	public void getTotalCountEmployeeTest(){
		System.out.println(repository.count());
	}

	@Test
	public void findByNameTest(){
		List<Employee> employeeList = repository.findByName("Nikhil Sharma");
		employeeList.forEach(employee -> System.out.println(employee.getName()));
	}

	@Test
	public void findByStartingCharacterTest(){
		List<Employee> employeeList = repository.findByNameLike("A%");
		employeeList.forEach(employee -> System.out.println(employee.getName()));
	}

	@Test
	public void findByAgeBetweenTest(){
		List<Employee> employeeList = repository.findByAgeBetween(20, 30);
		employeeList.forEach(employee -> System.out.println("Employee Name " + employee.getName() + " & Age " + employee.getAge()));
	}


	@Test
	public void findByAgePagingAndSortingTest(){
		Pageable pageable = PageRequest.of(1,1, Sort.Direction.DESC, "age");
		List<Employee> employeeList = repository.findByAgeIn(Arrays.asList(21, 26), pageable);
		employeeList.forEach(employee -> System.out.println("Employee name " + employee.getName() + " & Employee Age " + employee.getAge()));


	}


}
