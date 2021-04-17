package Java8.question1;

@FunctionalInterface
interface Comparable{
    boolean compare(int a, int b);
}

public class FindGreaterNumber {

    public static void main(String[] args) {
        Comparable c =(int x, int y) -> {return x>y;};
        System.out.println(c.compare(1,8));
    }
}
