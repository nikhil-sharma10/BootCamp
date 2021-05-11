package com.example.JPQLAndNativeSQLExercise;

import com.example.JPQLAndNativeSQLExercise.entities.Employee;
import com.example.JPQLAndNativeSQLExercise.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JpqlAndNativeSqlExerciseApplicationTests {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
	}

	@Test
	public void createEmployeeDataTest() {
		Employee employee = new Employee();
		employee.setFirstName("Ananya");
		employee.setLastName("Shukla");
		employee.setSalary(18000);
		employee.setAge(28);
		repository.save(employee);
	}


	@Test
	public void allEmployeeDataTest(){
		List<Order> orderList = new ArrayList<>();
		Order order1 = new Order(Sort.Direction.ASC,"age");
		Order order2 = new Order(Sort.Direction.DESC,"salary");
		orderList.add(order1);
		orderList.add(order2);

		List<Object []> employees = repository.findAllEmployee(Sort.by(orderList));
		for (Object[] emp:employees) {
			System.out.print(emp[0] + " ");
			System.out.println(emp[1]);
		}
	}

	@Test
	public void updateSalaryTest(){
		repository.updateSalary(0);
	}


	@Test
	public void selectLikeTest(){
		List<Object []> objects = repository.nativeQuery();
		for (Object[] object: objects) {
			System.out.print(object[0] + " ");
			System.out.print(object[1] + " ");
			System.out.println(object[2]);
		}
	}

	@Test
	@Transactional
	@Rollback(false)
	public void deleteEmployeeTest(){
		repository.deleteEmployee(40);
	}



}
