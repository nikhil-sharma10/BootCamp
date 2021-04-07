package multithreading.question14;

import java.util.Scanner;

class Work{

    public void produce(String name){

        synchronized(this){

            System.out.println("Producer "+ name + " is here...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer " + name + " is back again..");
        }


    }

    public void consumer(){

        synchronized(this){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Press Enter key..");
            sc.nextLine();
            System.out.println("Key is pressed..");
            notifyAll();

        }
    }
}

public class WaitAndNotifyAll {

    public static void main(String[] args) {
        Work work = new Work();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                work.produce(Thread.currentThread().getName());
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                work.consumer();
            }
        });

        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                work.produce(Thread.currentThread().getName());
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
