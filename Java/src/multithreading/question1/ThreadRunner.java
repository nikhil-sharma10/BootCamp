package multithreading.question1;

public class ThreadRunner {

    public static void main(String[] args) {
        Thread t1 = new Thread(new UsingRunnable());
        Thread t2 = new Thread(new UsingRunnable());
        System.out.println("Calling Threads of Runnable...");
        t1.start();
        t2.start();

        UsingThread thread1 = new UsingThread();
        UsingThread thread2 = new UsingThread();
        System.out.println("Calling Threads of Thread..");
        thread1.start();
        thread2.start();
    }
}
