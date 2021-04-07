package multithreading.question9;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    @Override
    public void run() {
        for(int i=0; i<5; i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class IncreaseConcurrency {

    public void usingFixedThreadPool(){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i=0; i<1E2; i++){
            executor.submit(new Task());
        }
        System.out.println("Task submitted In Fixed Thread Pool");
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task completed by Fixed Thread Pool");
    }

    public void usingCachePool(){

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0; i<1E2; i++){
            executor.submit(new Task());
        }
        System.out.println("Task submitted In Cached Thread Pool");
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task completed by Fixed Thread Pool");
    }

    public static void main(String[] args) {
        IncreaseConcurrency increaseConcurrency = new IncreaseConcurrency();

        System.out.println("Calling Fixed Thread Pool...");
        long startTimeFixed = new Date().getTime();
        increaseConcurrency.usingFixedThreadPool();
        long endTimeFixed = new Date().getTime();

        System.out.println("Calling Cached Thread Pool...");
        long startTimeCache = new Date().getTime();
        increaseConcurrency.usingCachePool();
        long endTimeCache = new Date().getTime();

        System.out.println("Time taken by Fixed thread pool:" + (double)(endTimeFixed - startTimeFixed)/1000 + " seconds");

        System.out.println("Time taken by Cached thread pool:" + (double)(endTimeCache - startTimeCache)/1000 + " seconds");
    }
}
