package exercise2.question2;

import java.lang.reflect.Array;

public class UniqueCharacter {

    public static void uniqueChar(String string){
        int[] array = new int[256];
        int i;
        for(i=0; i<string.length();i++){
            if(string.charAt(i) != ' '){
                array[(int)string.charAt(i)]++;
            }
        }
        int n = i;
        for(i = 0; i<n; i++){
            if(array[(int)string.charAt(i)] == 1){
                System.out.print(string.charAt(i));
                System.out.print("\t");
            }
        }
    }

    public static void main(String[] args) {
        uniqueChar("Nikhil is a very good boy");
    }
}
