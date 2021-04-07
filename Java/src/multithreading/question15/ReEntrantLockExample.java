package multithreading.question15;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Work {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 0;

    public void doIncrement(){

        for(int i=0; i<1E4; i++){
            count++;
        }
    }
    public void threadOne(String name) throws InterruptedException {

        lock.lock();
        System.out.println("Waiting....." + name);
        condition.await();

        System.out.println("Thread one back again with thread name :" + name);
        try{
            doIncrement();
        }
        finally {
            lock.unlock();
        }
    }

    public void threadTwo(){
        lock.lock();
        System.out.println("Press the enter key...");
        new Scanner(System.in).nextLine();
        System.out.println("Enter key pressed...");
        condition.signal();
        try{
            doIncrement();
        }
        finally {
            lock.unlock();
        }
    }

    public void threadThree(){
        lock.lock();
        System.out.println("Press the enter key...");
        new Scanner(System.in).nextLine();
        System.out.println("Enter key pressed...");
        condition.signalAll();
        try{
            doIncrement();
        }
        finally {
            lock.unlock();
        }
    }

    public void getCount(){
        System.out.println("Value of count is :" + count);
    }
}

public class ReEntrantLockExample {

    public void awaitAndSignal(){
        Work work = new Work();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    work.threadOne(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                work.threadTwo();
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

        work.getCount();

    }

    public void awaitAndSignalAll(){
        Work work = new Work();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    work.threadOne(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                work.threadThree();
            }
        });

        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    work.threadOne(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread3.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        work.getCount();

    }

    public static void main(String[] args) {

        ReEntrantLockExample reEntrantLockExample = new ReEntrantLockExample();
        System.out.println("Await & Signal called...");
        reEntrantLockExample.awaitAndSignal();

        System.out.println("Await & SignalAll called...");
        reEntrantLockExample.awaitAndSignalAll();

    }
}
