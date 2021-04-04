package exercise2.question7;

import java.util.Stack;

public class SpecialDS extends Stack<Integer> {

    Stack<Integer> stack = new Stack<Integer>();

    public void push(int element){
        if(isEmpty() == true){
            super.push(element);
            stack.push(element);
        }

        else{
            super.push(element);
            int newElem = stack.pop();
            stack.push(newElem);
            if(element < newElem){
                stack.push(element);
            }
            else{
                stack.push(newElem);
            }
        }
    }

    public Integer pop(){
        int element = super.pop();
        stack.pop();
        return element;
    }

    public int getMin(){
        int element = stack.pop();
        stack.push(element);
        return element;
    }

    public static void main(String[] args) {
        SpecialDS ds = new SpecialDS();
        ds.push(24);
        ds.push(65);
        System.out.println(ds.getMin());
        ds.pop();
        ds.push(2);
        ds.push(46);
        ds.push(1);
        ds.pop();
        System.out.println(ds.getMin());
    }

}
