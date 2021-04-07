package multithreading.question5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorMethods {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0; i<1E4; i++){
            int finalI = i;
            executor.submit(new Runnable() {

                @Override
                public void run() {
                    int count =0;
                    for(int i=0;i<1E5;i++){
                        count++;
                    }
                }
            });
        }
        executor.shutdown();
        if(executor.isShutdown()){
            System.out.println("Thread Shut down");
        }
//        if(executor.isTerminated()){
//            System.out.println("All tasks completed");
//        }
        executor.awaitTermination(1, TimeUnit.MINUTES);
        if(executor.isTerminated()){
            System.out.println("All tasks completed");
        }
    }
}
