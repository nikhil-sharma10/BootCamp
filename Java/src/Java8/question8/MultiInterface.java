package Java8.question8;

interface Inter1{

    default void show(){
        System.out.println("Show method of Inter1");
    }

    public void des();
}

interface Inter2{

    default void show(){
        System.out.println("Inside show method of Inter2...");
    }

    public void sum();
}

public class MultiInterface implements Inter1,Inter2 {


    @Override
    public void show() {
        System.out.println("Inside show of class....");
    }

    @Override
    public void des() {
        System.out.println("Inside des...");
    }

    @Override
    public void sum() {
        System.out.println("Inside sum....");
    }

    public static void main(String[] args) {

        MultiInterface mul = new MultiInterface();
        mul.des();
        mul.show();
        mul.sum();
    }
}
