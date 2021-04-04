package exercise2.question1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SumOfNumbers {

    public static void main(String[] args) {
        List<Float> list = new ArrayList<Float>();
        addItem(list);
        System.out.println("Sum of floating numbers:"+ sumOfNumbers(list));
    }

    public static List<Float> addItem(List<Float>list){
        list.add(20.8f);
        list.add(20.9f);
        list.add(32.0f);
        list.add(39.7f);
        list.add(45.6f);
        return list;
    }

    public static float sumOfNumbers(List<Float>list){
        Iterator<Float> it = list.iterator();
        float sum = 0;
        while(it.hasNext()){
            sum += it.next();
        }
        return sum;
    }
}
