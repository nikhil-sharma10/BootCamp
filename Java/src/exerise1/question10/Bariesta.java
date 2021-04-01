package exerise1.question10;

public class Bariesta extends User {

    public void prepareCoffee(){
        System.out.println("Order prepared:");
        OrderQueue.completionList.add(OrderQueue.orderList.poll());
    }

    public boolean notification(int token){
        //check if this token is in completion queue or not
        if(OrderQueue.completionList.contains(token)){
            return true;
        }
        else{
            return false;
        }
    }
}
