package Java8.question2;

@FunctionalInterface
interface Returnable{
    int returnInt(int a, int b);
}

public class ReturnOneInteger {

    public static void main(String[] args) {
        Returnable ret = (a,b) -> {return a;};

        System.out.println(ret.returnInt(13,67));
    }
}
