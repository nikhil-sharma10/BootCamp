package exerise1.question6;

public class ExceptionExamples {

    public static void main(String[] args) {

        try{
            int a = 45;
            int b = a/0;
            int [] c = {1,2};
            int d = c[2]/2;
        }
        catch(ArithmeticException e){
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("Finally block is here:");
        }
    }
}
