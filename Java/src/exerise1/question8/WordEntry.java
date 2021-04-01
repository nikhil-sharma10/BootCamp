package exerise1.question8;

import org.openjsse.sun.security.rsa.RSAUtil;

import java.util.Scanner;

public class WordEntry {

    public static void doWhile(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text for Do While function:");
        String string = sc.next();
        do{
            if(string.charAt(0) == string.charAt(string.length()-1)){
                System.out.println(string + " has first and last character equal");
            }
            else{
                System.out.println(string + " doesn't have first and last character equal");
            }
            string = sc.next();
        }while(!string.equals("done"));
    }

    public static void whileFun(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text for While function:");
        String string = sc.next();

        while(!string.equals("done")){
            if(string.charAt(0) == string.charAt(string.length()-1)){
                System.out.println(string + " has first and last character same");
            }
            else{
                System.out.println(string + " doesn't have first and last character same");
            }
            string = sc.next();
        }
    }

    public static void main(String[] args) {

        whileFun();
        doWhile();
    }
}
