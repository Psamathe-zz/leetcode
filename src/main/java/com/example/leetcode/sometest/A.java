package com.example.leetcode.sometest;

import java.util.Optional;
import java.util.Stack;

public class A {

    public static class User {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    public static void main(String[] args) {
        User user = new User();
        String city = Optional.ofNullable(user).map(u-> u.getName()).get();

        Optional.ofNullable(user)
                .ifPresent(u->{
                    dosomething(u);
                });
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

    private static void dosomething(User user){

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
