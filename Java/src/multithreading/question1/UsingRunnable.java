package multithreading.question1;

public class UsingRunnable implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<5; i++){
            System.out.println("Value inside runnable:" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
