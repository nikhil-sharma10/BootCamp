package exerise1.question13;

public class NoStackTrace extends Exception{
    /* this flag value is used for making the stack trace suppressible or not
    * if set true then stack trace will not be printed otherwise stack trace will be printed */
    private boolean flag = false;


    public NoStackTrace(String message, boolean flag){
        super(message,null,flag,!flag);
        this.flag = flag;
    }

    public String toString() {
        if(flag){
            return getLocalizedMessage();
        }
        else{
            return super.toString();
        }

    }

    public static void main(String[] args) {
        try{
            throw new NoStackTrace("For this exception Stack trace will be suppressed",true);
        }
        catch(NoStackTrace e){
            e.printStackTrace();
        }
    }
}
