package multithreading.question2;

public class SleepAndJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Value for thread1:" + i);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable(){

            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Value for thread2:" + i);
                }
            }
        });

        System.out.println("Calling threads..");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Task completed");

    }
}
