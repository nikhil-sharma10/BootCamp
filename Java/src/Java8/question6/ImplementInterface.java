package Java8.question6;


interface Showable{

    default void show(){
        System.out.println("Inside show mehtod..");
    }

    public static void des(){
        System.out.println("Inside description method..");
    }
}

public class ImplementInterface implements Showable {

    public static void main(String[] args) {
        ImplementInterface imp = new ImplementInterface();
        imp.show();
        Showable.des();
    }
}
