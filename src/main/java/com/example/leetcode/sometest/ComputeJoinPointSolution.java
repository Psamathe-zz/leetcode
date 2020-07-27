package com.example.leetcode.sometest;

public class ComputeJoinPointSolution {
    public static void main(String[] args) {
        int s1 = 471;
        int s2 = 480;
        ComputeJoinPointSolution computeJoinPointSolution = new ComputeJoinPointSolution();
        int res = computeJoinPointSolution.computeJoinPoint(s1,s2);
        System.out.println(res);
    }

    public static int computeJoinPoint(int s1, int s2) {
        // Write your code here
        // To debug: System.err.println("Debug messages...");
        if(s1 > s2)
            return computeJoinPoint(s2,s1);
        else if(s1 == s2)
            return s1;
        else {
            int res = s1;
            while (s1 > 0){
                res += s1%10;
                s1 = s1 / 10;
            }
            return computeJoinPoint(res,s2);
        }
    }
}
