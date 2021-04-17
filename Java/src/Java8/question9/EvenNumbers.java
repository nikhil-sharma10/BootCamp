package Java8.question9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumbers {

    public static void main(String[] args) {

        List<Integer>numbers = Arrays.asList(23,10,45,89,880);
        List<Integer> list = numbers.stream().filter(i -> i%2 == 0).collect(Collectors.toList());
        System.out.println(list);
    }
}
