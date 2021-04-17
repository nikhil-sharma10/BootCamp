package multithreading.question6;

import java.util.concurrent.*;

class Sum implements Callable<Integer> {

    private int length;

    public Sum(int length){
        this.length = length;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for(int i = 0; i<length; i++){
            sum += i;

        }
        Thread.sleep(60000);

        return sum;
    }
}

public class FutureAndCallable {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Integer> future=null;

        for (int i = 0; i < 5; i++) {
            future = executor.submit(new Sum(10000));
        }
//        executor.shutdown();
//
//        executor.awaitTermination(1,TimeUnit.MINUTES);

        if(future.isCancelled()){
            System.out.println("Task got cancelled...");
        }
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(future.isDone()){
            System.out.println("Task is completed...");
            try {
                System.out.println("The Sum is: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Exception occured...");
            }

        }

    }

}
