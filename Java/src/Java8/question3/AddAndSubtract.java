package Java8.question3;

@FunctionalInterface
interface Mathematical{
    int doOperation(int a, int b);
}

public class AddAndSubtract {

    public int add(int a , int b){
        return a+b;
    }

    public int subtract(int a, int b){
        return (a>b)? a-b : b-a;
    }

    public static int multiply(int a, int b){
        return a*b;
    }

    public static void main(String[] args) {

        Mathematical add = new AddAndSubtract() :: add;
        Mathematical sub = new AddAndSubtract() :: subtract;
        Mathematical mul = AddAndSubtract::multiply;

        System.out.println("Addition of numbers is : "+ add.doOperation(20,30));
        System.out.println("Subtraction of numbers is : "+ sub.doOperation(20,30));
        System.out.println("Multiplication of numbers is : "+ mul.doOperation(20,30));
    }
}
