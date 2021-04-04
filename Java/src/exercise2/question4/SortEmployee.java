package exercise2.question4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortEmployee {

    public static void main(String[] args) {
        Employee emp1 = new Employee(24.0,30000.40,"Nihal");
        Employee emp2 = new Employee(24.4,36000.40,"Nikam");
        Employee emp3 = new Employee(34.0,40000.40,"Naved");
        Employee emp4 = new Employee(28.0,50000.0,"Abhijeet");
        Employee emp5 = new Employee(29.0,32000.40,"Pushpendra");
        Employee emp6 = new Employee(23.0,36000.40,"Vijay");
        List<Employee>employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);
        Collections.sort(employees, new Comparator<Employee>(){

            @Override
            public int compare(Employee o1, Employee o2) {
                return -o1.salary.compareTo(o2.salary);
            }
        });
        for(Employee employee: employees){
            System.out.println("Employee:"+ employee.name + " with age:"+ employee.age + " has salary:"+ employee.salary);
        }
    }
}
