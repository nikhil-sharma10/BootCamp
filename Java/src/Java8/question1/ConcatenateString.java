package Java8.question1;

@FunctionalInterface
interface Concatenable{
    String concatenate(String a, String b);
}

public class ConcatenateString {

    public static void main(String[] args) {
        Concatenable con = (a,b) -> {return a+b; };

        System.out.println(con.concatenate("Nikhil","Sharma"));
    }
}
