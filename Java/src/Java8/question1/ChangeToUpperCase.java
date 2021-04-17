package Java8.question1;

@FunctionalInterface
interface UpperCaseConvertable{
    String convertToUpperCase(String a);
}

public class ChangeToUpperCase {

    public static void main(String[] args) {
        UpperCaseConvertable up = (a) -> {return a.toUpperCase();};

        System.out.println(up.convertToUpperCase("nikhil"));
    }
}
