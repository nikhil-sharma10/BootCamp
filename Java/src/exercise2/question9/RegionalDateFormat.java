package exercise2.question9;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class RegionalDateFormat {

    public static String regionalDateTimeFormat(String region, String lang){

        Date date = new Date();
        System.out.println("In our region:"+ date.toString());
        Locale locale = new Locale(lang,region);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL,locale);
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println("Enter region in which you format you want to see date:");
        Scanner scanner = new Scanner(System.in);
        String region = scanner.nextLine();
        System.out.println("Enter the language in which you format you want to see date:");
        String language = scanner.nextLine();
        System.out.println("Date format in " + region + " is " + regionalDateTimeFormat(region,language));
    }
}
