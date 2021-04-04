package exercise2.question5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortStudent {

    public static void main(String[] args) {
        List<Student>studentList = new ArrayList<Student>();
        addStudent(studentList);
        studentList = sort(studentList);
        for(Student student: studentList){
            System.out.println("Student " + student.name + " of age " + student.age + " has scored " + student.score);
        }

    }

    public static List<Student> addStudent(List<Student>students){

        Student st1 = new Student("Vimlesh",13.1,233.4);
        Student st2 = new Student("Kamal",13.0,287.4);
        Student st3 = new Student("Suhan",13.5,298.4);
        Student st4 = new Student("Sunny",14.0,209.4);
        Student st5 = new Student("Dharmendra",13.8,276.4);
        Student st6 = new Student("Prajjawal",13.4,298.4);
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);
        students.add(st6);
        return students;
    }

    public static List<Student> sort(List<Student>students){
        Collections.sort(students, new Comparator<Student>(){

            @Override
            public int compare(Student o1, Student o2) {
                if(o1.score > o2.score){
                    return 1;
                }
                else if(o1.score < o2.score){
                    return -1;
                }
                else{
                    return o1.name.compareTo(o2.name);
                }
            }
        });

        return students;
    }
}
