package Java8.question11;

import java.util.Arrays;
import java.util.List;

public class FindAvg {

    public static void main(String[] args) {

        List<Integer>numbers = Arrays.asList(23,86,0,6);

        int sum = numbers
                .stream()
                .map(i -> i*2)
                .reduce(0,(x,y) -> {return x+y;});
        System.out.println("Average is : " + (double)sum/numbers.size());
    }
}
