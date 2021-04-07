package multithreading.question10;


public class SynchronizeMethod {

    private int count = 0;

    public static void main(String[] args) {

        SynchronizeMethod synchronizeMethod = new SynchronizeMethod();
        synchronizeMethod.work();

    }

    public synchronized void increment(String name){

        count++;
//        System.out.println("Working thread is :" + name + " and count value is " + count);
    }

    public void work(){

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i<1E4; i++){
                    increment(Thread.currentThread().getName());

                }
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i<1E3; i++){
                    increment(Thread.currentThread().getName());

                }
            }
        });

        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count value is: "+ count);

    }
}
