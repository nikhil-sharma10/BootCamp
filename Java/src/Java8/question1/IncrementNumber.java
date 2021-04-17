package Java8.question1;

@FunctionalInterface
interface Incrementable{
    int increment(int a);
}

public class IncrementNumber {

    public static void main(String[] args) {
        Incrementable inc = a -> {return a+1;};

        System.out.println(inc.increment(9));
    }
}
