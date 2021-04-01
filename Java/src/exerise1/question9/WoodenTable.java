package exerise1.question9;

public class WoodenTable extends Table {
    @Override
    public void stressTest() {
        System.out.println("Stress test is passed for Wooden Table");
    }

    @Override
    public void fireTest() {
        System.out.println("Fire test is failed for Wooden Table");
    }

    public static void main(String[] args) {
        Table table = new WoodenTable();
        table.stressTest();
        table.fireTest();
    }
}
