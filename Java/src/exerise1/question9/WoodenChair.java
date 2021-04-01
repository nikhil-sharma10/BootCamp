package exerise1.question9;

public class WoodenChair extends Chair {

    @Override
    public void stressTest() {
        System.out.println("Stress test is passed for Wooden chair..");
    }

    @Override
    public void fireTest() {
        System.out.println("Fire test is failed for Wooden chair...");
    }

    public static void main(String[] args) {
        Chair woodenChair  = new WoodenChair();
        woodenChair.stressTest();
        woodenChair.fireTest();
    }
}
