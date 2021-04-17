package Java8.question12;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FirstEvenGreaterThan3 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(23,78,90,6,88);
        Optional<Integer> num  = numbers.stream().sorted().filter(i -> i>3 && i%2 ==0).findFirst();

        System.out.println(num.get());
    }
}
