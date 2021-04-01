package exerise1.question10;

public class Customer extends User {
    private double cash;


    public boolean cashPaid(Order order){
        if(cash >= order.getPrice()){
            System.out.println("Cash paid");
            return true;
        }
        else{
            System.out.println("payment not done");
            return false;
        }
    }

    public int getTokenFromCashier(){
        return new Cashier().giveToken(new Customer(), new Order());
    }

    public void getOrder(){
        if(new Bariesta().notification(new Order().getOrderId())){
            System.out.println("Order received");
        }

    }
}
