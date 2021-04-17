package Java8.question7;

interface Implementable{
    default void show(){
        System.out.println("Inside Implementable....");
    }
}

public class OverrideDefault implements Implementable {

    public void show(){
        System.out.println("Inside show of implemented class..");
    }
    public static void main(String[] args) {

        new OverrideDefault().show();
    }

}
