package exerise1.question9;

public class MetalChair extends Chair {
    @Override
    public void fireTest() {
        System.out.println("Fire test is passed for Metal Chair..");
    }

    @Override
    public void stressTest() {
        System.out.println("Stress test is passed for Metal chair..");
    }

    public static void main(String[] args) {
        Chair metalChair = new MetalChair();
        metalChair.fireTest();
        metalChair.stressTest();
    }
}
