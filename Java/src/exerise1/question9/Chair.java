package exerise1.question9;

public class Chair implements ImplementableInterface {

    @Override
    public void stressTest() {
        System.out.println("Stress test is passed for Chairs..");
    }

    @Override
    public void fireTest() {
        System.out.println("Fire test is passed for Chairs...");
    }
}
