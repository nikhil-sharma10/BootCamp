package Java8.question10;

import java.util.Arrays;
import java.util.List;

public class Sum {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(2,9,8,2,90);

        int sum = numbers
                .stream()
                .filter( i -> i>5)
                .reduce(0,(x,y) -> {return x+y;});
        System.out.println(sum);
    }
}
