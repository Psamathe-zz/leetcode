package com.example.leetcode.challenge.test2022.december;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {

    }

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int val1;
        int val2;
        int temp;
        for (String token : tokens){
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                val1 = Integer.valueOf(stack.pop());
                val2 = Integer.valueOf(stack.pop());
                switch (token){
                    case "+" :
                        temp = val1 + val2;
                        break;
                    case "-" :
                        temp = val2 - val1;
                        break;
                    case "*" :
                        temp = val1 * val2;
                        break;
                    case "/" :
                    default:
                        temp = val2 / val1;
                }
                stack.push(String.valueOf(temp));
            } else {
                stack.push(token);
            }
        }
        System.out.println(stack.size());
        return Integer.valueOf(stack.pop());

    }
}
