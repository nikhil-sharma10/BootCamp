package multithreading.question13;

import java.util.Scanner;

class Work {

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer.....");
            wait();
            System.out.println("Producer is Back.....");
        }

    }

    public void consumer() throws InterruptedException {
        Scanner sc=new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this){
            System.out.println("Press Enter key");
            sc.nextLine();
            System.out.println("Key pressed");
            notify();
            Thread.sleep(1000);
        }

    }
}

public class WaitAndNotify {

    public static void main(String[] args) {

        Work work=new Work();

        Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    work.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    work.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
