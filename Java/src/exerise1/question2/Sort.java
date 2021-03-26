package exerise1.question2;

import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        System.out.println("Enter any string:");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        System.out.println("Revresed String:"+sortFun(string));
    }

    public static String sortFun(String string){
        char tempArray [] = string.toCharArray();

        for(int i=0,j=tempArray.length-1;i<j;j--,i++){

            char temp = tempArray[i];
            tempArray[i] = tempArray[j];
            tempArray[j] = temp;
        }

        String newString = new String(tempArray);
        return newString;
    }
}
