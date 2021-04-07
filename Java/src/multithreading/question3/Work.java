package multithreading.question3;

public class Work implements Runnable{
    int id;

    public Work(int id){
        this.id = id;
    }

    public void run(){
        System.out.println("Task with id:" + id + " Started..");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task with id:" + id + " completed..");
    }
}
