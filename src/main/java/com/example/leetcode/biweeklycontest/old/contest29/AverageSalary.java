package com.example.leetcode.biweeklycontest.old.contest29;

public class AverageSalary {
    public static void main(String[] args) {

    }

    public double average(int[] salary) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < salary.length; i++) {
            sum += salary[i];
            min = Math.min(min,salary[i]);
            max = Math.max(max,salary[i]);
        }
        return (double) (sum - min - max) / (salary.length - 2);
    }
}
