package exercise2.question6;

import java.util.*;

public class FrequencySort {

    public static List<Integer> addElements(List<Integer> list){
        list.add(23);
        list.add(24);
        list.add(26);
        list.add(29);
        list.add(28);
        list.add(25);
        list.add(25);
        list.add(23);
        list.add(80);
        list.add(80);
        return list;
    }

    public static void frequencySort(List<Integer> list){
        Map<Integer, Integer>mapCount = new LinkedHashMap<>();
        for(Integer num: list){
            if(mapCount.containsKey(num)){
                mapCount.put(num, mapCount.get(num)+1);
            }
            else{
                mapCount.put(num,1);
            }
        }

      List<Map.Entry<Integer, Integer>> sortedList = new LinkedList<>(mapCount.entrySet());
      Collections.sort(sortedList, new Comparator<Map.Entry<Integer,Integer>>(){

          @Override
          public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
              return -o1.getValue().compareTo(o2.getValue());
          }
      });
        System.out.println("Sorted Frequency list:");
        System.out.println(sortedList);
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        addElements(list);
        frequencySort(list);

    }
}
