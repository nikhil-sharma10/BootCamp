package multithreading.question12;

import java.util.concurrent.atomic.AtomicInteger;

class Work extends Thread{

    AtomicInteger atomicInteger;
    int count = 0;

    public Work(){
        atomicInteger = new AtomicInteger();
    }

    public void run(){

        for(int i=0; i<1E4; i++){
            atomicInteger.addAndGet(1);
            count++;
        }
    }
}

public class AtomicClassExample {

    public static void main(String[] args) {
        Work work = new Work();

        Thread thread1 = new Thread(work);
        Thread thread2 = new Thread(work);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value of Atomic count :" + work.atomicInteger);
        System.out.println("Final value of normal count :" + work.count);
    }

}
