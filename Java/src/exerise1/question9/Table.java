package exerise1.question9;

public class Table implements ImplementableInterface{

    @Override
    public void fireTest() {
        System.out.println("Fire test is passed for Tables...");
    }

    @Override
    public void stressTest() {
        System.out.println("Stress test is passed for Tables....");
    }
}
