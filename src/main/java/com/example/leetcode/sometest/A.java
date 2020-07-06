package com.example.leetcode.sometest;

import java.util.Stack;

public class A {
    public static void main(String[] args) {
        A A = new A();
        System.out.println(A.check("[()]"));   // true
        System.out.println(A.check("(()[])")); // true
        System.out.println(A.check("([)]"));   // false
        System.out.println(A.check("(("));     // false
        System.out.println(A.check("[(()])")); // false

        System.out.println(A.check("([(([[(([]))]]))])"));   // true
        System.out.println(A.check("[](()()[[]])()[]([])")); // true
        System.out.println(A.check("([((([(([]))])))))])")); // false
        System.out.println(A.check("[](()()[[]])[][[([])")); // false
    }

    public boolean check(String str) {
        if(str.length() == 0)
            return true;
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c: chars){
            if(c == ')'){
                if( stack.isEmpty() || stack.peek() != '('){
                    return false;
                } else {
                    stack.pop();
                }
            } else if(c == ']'){
                if( stack.isEmpty() || stack.peek() != '[')
                    return false;
                else
                    stack.pop();
            } else
                stack.push(c);
        }

        return stack.isEmpty();
    }
}
