package exerise1.question1;

import java.util.Date;

public class Member extends Account {

    private Date dateOfMembership;
    private int totalBooksCheckedOut;

    public int getTotalBooksCheckedOut(){
        return totalBooksCheckedOut;
    }

    public boolean reserveBookItem(BookItem bookItem){
        System.out.println("Book item reserved");
        return true;
    }

    private void incrementTotalBooksCheckedOut(){
        totalBooksCheckedOut++;
    }

    public boolean checkoutBook(BookItem bookItem){
        if(this.getTotalBooksCheckedOut() > 100){
            return false;
        }
        //check all conditions
        System.out.println("Book successfully checked out..");
        this.incrementTotalBooksCheckedOut();
        return true;
    }
}
