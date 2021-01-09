package com.example.leetcode.challenge.test2020.april.week2;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class MinStack {

    List<Integer> stock;
    int index;
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    /** initialize your data structure here. */
    public MinStack() {
        stock = new ArrayList<>();
        index = 0;
    }

    public void push(int x) {
        stock.add(x);
        index++;
    }

    public void pop() {
        index--;
        stock.remove(index);
    }

    public int top() {
        return stock.get(index-1);
    }

    public int getMin() {
        int result = stock.get(0);
        for(int i = 0; i < index ; i ++){
            if(stock.get(i) < result)
                result = stock.get(i);
        }
        return result;
    }

    /**
     * better solution
     */

    /*
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
        if(stack2.isEmpty() || x <= stack2.peek()) {
            stack2.push(x);
        }
    }

    public void pop() {
        int x = stack1.pop();
        if(x == stack2.peek()) {
            stack2.pop();
        }
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }
    */
}
