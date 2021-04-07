package multithreading.question11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedBlock {

    private static List<Integer>list1 = new ArrayList<>();
    private static List<Integer>list2 = new ArrayList<>();
    Random random= new Random();
    Object lock1 = new Object();
    Object lock2 = new Object();

    public  void doOne(){
        synchronized(lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }


    public void doTwo(){
        synchronized(lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }

    }

    public void complete(){

        for(int i=0; i<1E3; i++){
            doOne();
            doTwo();
        }
    }

    public static void main(String[] args) {

        SynchronizedBlock block = new SynchronizedBlock();

        long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {


            @Override
            public void run() {
                block.complete();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                block.complete();
            }
        });

        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Size of list1: " + list1.size());
        System.out.println("Size of list2: " + list2.size());

        System.out.println("Total time taken: " + (double)(endTime - startTime)/1000 + " seconds");

    }
}
