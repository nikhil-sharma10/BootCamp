package multithreading.question8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Task2 implements Runnable {

    private final String name;
    private final String type;

    Task2(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void run() {
        System.out.println("Task: "+ name + " of type : "+ type + " executed on : " + LocalDateTime.now().toString());
    }
}

class Task implements Callable {
    private final String name;

    Task(String name) {
        this.name = name;
    }


    @Override
    public String call() throws Exception {
        return "Task :" + name + " execueted on : " + LocalDateTime.now().toString();
    }
}

public class TaskScheduled {

    public void usingSchedule(){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        List<ScheduledFuture<String>>results = new ArrayList<>();

        for(int i=0; i<5; i++){
            Task task = new Task("Task-" + i);
            ScheduledFuture<String>result  = scheduledExecutorService.schedule(task, i*2, TimeUnit.SECONDS);
            results.add(result);
        }

        scheduledExecutorService.shutdown();

        try {
            scheduledExecutorService.awaitTermination(1,TimeUnit.HOURS);

            for(ScheduledFuture<String> result : results){
                System.out.println(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void usingScheduleAtFixedDelay()  {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        for(int i=0; i<5; i++){
            scheduledExecutorService.scheduleWithFixedDelay(new Task2("Task-" + i, "Fixed Delay"),1,2,TimeUnit.SECONDS);
        }

//        scheduledExecutorService.shutdown();

        try {
            scheduledExecutorService.awaitTermination(5,TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void usingScheduleAtFixedRate(){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        final int PERIOD = 2;

        for(int i=0; i<5; i++){
         scheduledExecutorService.scheduleAtFixedRate( new Task2("Task-" + i, "Fixed Rate"),1,PERIOD,TimeUnit.SECONDS);
        }

//        scheduledExecutorService.shutdown();

        try {
            scheduledExecutorService.awaitTermination(5,TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        TaskScheduled taskScheduled = new TaskScheduled();

        System.out.println("Scheduling task using normal schedule...");
        taskScheduled.usingSchedule();

        System.out.println("Scheduling task with fixed delay..");
        taskScheduled.usingScheduleAtFixedDelay();

        System.out.println("Scheduling task at fixed rate..");
        taskScheduled.usingScheduleAtFixedRate();
    }
}
