package exerise1.question1;

import java.util.Date;

public class BookLending {

    private Date creationDate;
    private Date dueDate;
    private Date returnDate;
    private int bookItemId;
    private String memberId;

    public  boolean lendBook(int id, String memberId){
        this.bookItemId = id;
        this.memberId = memberId;
        System.out.println("Book with id:"+ id + " Successfully lended to member with id:" + memberId);
        return true;
    }

    public BookLending fetchLendingDetails(int bookItemId){
        BookLending bookLending = new BookLending();
        return bookLending;
    }


}
