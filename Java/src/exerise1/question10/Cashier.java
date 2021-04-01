package exerise1.question10;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cashier extends User {

    public boolean placeOrder(Order order, Customer customer){
        if(customer.cashPaid(order)){
            LinkedHashMap<Order,Customer>map = new LinkedHashMap<>();
            map.put(order,customer);
            OrderQueue.orderList.add(map);
            order.setOrderId(1);//1 is dummy
            order.setUser(customer);
            return true;
        }
        else{
            return false;
        }
    }
    public int giveToken(Customer customer, Order order){
        //Check that for given customer order has been placed in the order list or not
        //If not placed, place the order using placeOrder function
        //and return the order id
        return order.getOrderId();
    }
}
