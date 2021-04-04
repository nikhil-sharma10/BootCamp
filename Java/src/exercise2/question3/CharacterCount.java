package exercise2.question3;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharacterCount {

    public static void count(String string){

        char[] charArray = string.toCharArray();
        Map<Character,Integer>mapCount = new LinkedHashMap<>();
        for(char ch: charArray){
            if (ch == ' ') {
                continue;
            }
            if(mapCount.containsKey(ch)){
                mapCount.put(ch, mapCount.get(ch)+1);
            }
            else{
                mapCount.put(ch,1);
            }
        }
        System.out.println(mapCount);
    }

    public static void main(String[] args) {
        count("Nikhil Sharma");
    }
}
