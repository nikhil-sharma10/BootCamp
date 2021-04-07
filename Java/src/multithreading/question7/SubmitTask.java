package multithreading.question7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SubmitTask {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0; i<1E4; i++){
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    for(int i=0; i<4; i++){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.shutdown();
        System.out.println("Task submitted");
        executorService.awaitTermination(2, TimeUnit.MINUTES);
        System.out.println("Tasks completed...");

    }
}
