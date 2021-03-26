package exerise1.question7;

import java.util.Scanner;

public class SecondConversion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter time in seconds:");
        int time = sc.nextInt();
        conversion(time);
    }

    public static void conversion(int t){
        int MINUTE = 60, HOUR = 3600, DAY = 86400;
        int min, day, hour, second;
        day = t/DAY;
        t = t%DAY;
        hour = t/HOUR;
        t = t%HOUR;
        min = t/MINUTE;
        t = t%MINUTE;
        second = t;
        System.out.println("Day:" + day + " Hour:" + hour + " Minute:" + min + " Seconds:" + second);
    }
}
