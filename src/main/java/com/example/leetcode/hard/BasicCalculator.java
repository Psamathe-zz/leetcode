package com.example.leetcode.hard;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
        String s = "";
        BasicCalculator basicCalculator = new BasicCalculator();
        int result = basicCalculator.calculate(s);
        System.out.println(result);
    }

    public int calculate(String s) {
        char[] chars = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        int index = 0;
        boolean isReset = false;
        for(char c : chars){
            if(c == '+' || c == '-' || c == '(') {
                stack.push(c);
                isReset = true;
            } else if(c == ')') {
                char cPop;
                StringBuffer aStringBuffer = new StringBuffer();
                StringBuffer bStringBuffer = new StringBuffer();
                char calculate;
                while ((cPop = stack.peek()) != '+' && cPop != '-'){
                    aStringBuffer.insert(0,cPop);
                }
                calculate = cPop;
                while ((cPop = stack.peek()) != '('){
                    bStringBuffer.insert(0,cPop);
                }
                int temp = calculate == '+'? Integer.valueOf(aStringBuffer.toString()) + Integer.valueOf(bStringBuffer.toString()) : Integer.valueOf(bStringBuffer.toString()) - Integer.valueOf(aStringBuffer.toString());
                String tempString = String.valueOf(temp);
                for(char toInsert : tempString.toCharArray()){
                    stack.push(toInsert);
                }

            } else {

            }


        }
        return 0;
    }
}
