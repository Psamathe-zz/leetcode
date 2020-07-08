package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * ["4","13","5","/","+"]
 * ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();
        evaluateReversePolishNotation.evalRPN(tokens);
    }

    public int evalRPN(String[] tokens) {
        if(tokens.length==0||tokens==null)
            return 0;
        List<String> operators = Arrays.asList(new String[]{"+","-","*","/"});
        int length = tokens.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            if(operators.contains(tokens[i])){
                int a = stack.pop();
                int b = stack.pop();
                switch (tokens[i]){
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                }
            } else
                stack.push(Integer.valueOf(tokens[i]));

        }
        int res = stack.peek();
        return res;
    }
}
