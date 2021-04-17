package Java8.question4;

@FunctionalInterface
interface informations{
    Employee getStudent(String name, int age, String city);
}

public class Employee {

    private String name;
    private int age;
    private String city;

    public void details(){
        System.out.println("Employee name is : "+ name + " ,age is : "+ age + " & city is : "+ city);
    }

    Employee(String name, int age, String city){
        this.name = name;
        this.age= age;
        this.city= city;
    }

    public static void main(String[] args) {
        informations inf = Employee :: new;

        inf.getStudent("Nikhil",21,"NCR").details();
    }


}
