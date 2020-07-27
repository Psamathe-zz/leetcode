package com.example.leetcode.sometest;

import java.util.Arrays;

public class FindSmallestIntervalSolution {
    public static void main(String args[]) {

        int[] numbers = new int[]{1,6,4,8,2};
        int res = findSmallestInterval(numbers);
        System.out.println(res);
    }
    public static int findSmallestInterval(int[] numbers) {
        // Write your code here
        // To debug: System.err.println("Debug messages...");
        Arrays.sort(numbers);
        int length = numbers.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < length - 1; i++) {
            if(res > numbers[i+1] - numbers[i])
                res = numbers[i+1] - numbers[i];
        }
        return res;
    }

}
