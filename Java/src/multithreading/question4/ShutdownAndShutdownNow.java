package multithreading.question4;

import multithreading.question3.Work;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable{
    private int count;

    @Override
    public void run() {
        for(int i=0; i<1E4; i++){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interruption occured..");
                break;
            }
            count++;
        }
        System.out.println("Value of count at the end is:" + count);
    }
}

public class ShutdownAndShutdownNow {

    public void usingShutdown(){
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new Worker());
        executor.shutdown();
        try {
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void usingShutdownNow(){
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new Worker());
        executor.shutdownNow();
        try {
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ShutdownAndShutdownNow shutdownAndShutdownNow = new ShutdownAndShutdownNow();
        System.out.println("Calling shutDown..");
        shutdownAndShutdownNow.usingShutdown();
        System.out.println("Calling shutDownNow...");
        shutdownAndShutdownNow.usingShutdownNow();
    }
}
