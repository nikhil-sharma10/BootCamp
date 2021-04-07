package multithreading.question16;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    private int balance = 100000;

    public void depositMoney(int amount){
        balance += amount;
    }

    public void withdrawMoney(int amount){
        balance -= amount;
    }

    public int getAmount(){
        return balance;
    }

    public static void transferMoney(BankAccount bankAccount1, BankAccount bankAccount2, int amount){
        bankAccount1.withdrawMoney(amount);
        bankAccount2.depositMoney(amount);
    }
}

class Customer {

    private BankAccount bankAccount1 = new BankAccount();
    private BankAccount bankAccount2 = new BankAccount();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void getLock(Lock firstLock, Lock secondLock){

        while(true){

            boolean getFirstLock = false;
            boolean getSecondLock = false;

            try{
                getFirstLock = firstLock.tryLock();
                getSecondLock = secondLock.tryLock();
            }
            finally{
                if(getFirstLock && getSecondLock){
                    return;
                }
                else if(getFirstLock){
                    firstLock.unlock();
                }
                else if(getFirstLock){
                    secondLock.unlock();
                }

            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void transferOne(){

        Random random = new Random();

        for(int i=0; i<1E4; i++){
            getLock(lock1,lock2);

            try{
                BankAccount.transferMoney(bankAccount1,bankAccount2,random.nextInt(1000));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void transferTwo(){

        Random random = new Random();

        for(int i=0; i<1E4; i++){
            getLock(lock2,lock1);

            try{
                BankAccount.transferMoney(bankAccount2,bankAccount1,random.nextInt(1000));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void getFinalBalance(){

        System.out.println("Bank Account1 balance: " + bankAccount1.getAmount());
        System.out.println("Bank Account2 balance: "+ bankAccount2.getAmount());
        System.out.println("Total Balance: "+ (bankAccount1.getAmount() + bankAccount2.getAmount()));
    }
}

public class DeadlockRunner {

    public static void main(String[] args) {
        Customer customer = new Customer();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                customer.transferOne();
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                customer.transferTwo();
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
        customer.getFinalBalance();
    }

}
