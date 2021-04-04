package exercise2.question8;

import java.time.LocalDate;
import java.util.Date;

public class FormatDate {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.print("Original format:");
        System.out.println(date);
        String dateString = date.toString();
        System.out.print("Formatted date:");
        System.out.println(formateDate(dateString));
    }

    public static String formateDate(String date){

        String[] splittedString = date.split("-");
        String month = null;

        switch (splittedString[1]){
            case "01":

                month = splittedString[1].replace(splittedString[1],"January");
                break;
            case "02":
                month = splittedString[1].replace(splittedString[1],"February");
                break;
            case "03":
                month = splittedString[1].replace(splittedString[1],"March");
                break;
            case "04":
                month = splittedString[1].replace(splittedString[1],"April");
                break;
            case "05":
                month = splittedString[1].replace(splittedString[1],"May");
                break;
            case "06":
                month = splittedString[1].replace(splittedString[1],"June");
                break;
            case "07":
                month = splittedString[1].replace(splittedString[1],"July");
                break;
            case "08":
                month = splittedString[1].replace(splittedString[1],"August");
                break;
            case "09":
                month = splittedString[1].replace(splittedString[1],"September");
                break;
            case "10":
                month = splittedString[1].replace(splittedString[1],"October");
                break;
            case "11":
                month = splittedString[1].replace(splittedString[1],"November");
                break;
            case "12":
                month = splittedString[1].replace(splittedString[1],"December");
                break;
        }

        String formatedString = splittedString[2] + "-" + month + "-" + splittedString[0];

        return formatedString;
    }
}
