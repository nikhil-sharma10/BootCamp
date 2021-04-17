package Java8.question5;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ImplementLamdaMethods {

    public static void main(String[] args) {

        Consumer<String> con = (a) -> {
            if(a.equalsIgnoreCase("Nikhil")){
                System.out.println("String matched");
            }
        };

        Supplier<Integer>sup = () -> {
            return 21;
        };

        Predicate<Integer> pred = (a) -> {
            return a>2;
        };

        Function<Integer, Integer> fun = (a) -> {return a*2;};
        System.out.print("Consumer value: ");
        con.accept("Nikhil");

        System.out.println("Supplier value: " + sup.get());

        System.out.println("Predicate value: "+ pred.test(1));

        System.out.println("Function value: "+ fun.apply(3));
    }
}
