package exerise1.question9;

public class MetalTable extends  Table{

    @Override
    public void fireTest() {
        System.out.println("Fire test is passed for Metal table");
    }

    @Override
    public void stressTest() {
        System.out.println("Stress test is passed for Metal table");
    }

    public static void main(String[] args) {
        Table table = new MetalTable();
        table.fireTest();
        table.stressTest();
    }
}
