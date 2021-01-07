package com.example.leetcode.challenge.Test2020.april.week1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HappyNumber {

    public boolean isHappy(int n) {

        if (n == 1) {
            return true;
        }
        if (n < 1) {
            return false;
        }
        HashSet<Integer> resultList = new HashSet<>();
        while (n != 1) {
            n = recalculate(n);
            if (!resultList.add(n))
                return false;
        }
        return true;

    }

    private int recalculate(int n) {
        int temp = n;
        int result = 0;
        while (temp / 10 > 0) {
            result += (int) Math.pow(temp % 10, 2);
            temp = temp / 10;
        }
        result += (int) Math.pow(temp, 2);
        return result;
    }

    public static boolean isHappyV2(int number) {
        HashSet<Integer> cycle = new HashSet<>();
        while (number != 1 && cycle.add(number)) {
            List<String> numStrList = Arrays.asList(String.valueOf(number).split(""));
            number = numStrList.stream().map(i -> Math.pow(Integer.parseInt(i), 2)).mapToInt(i -> i.intValue()).sum();
        }
        return number == 1;
    }


    public boolean isHappyV3(int n) {
        if (n < 1) {
            return false;
        }
        while (true) {
            n = squareAndSum(n);
            if (n == 1 || n == 7) return true;
            if (n < 10) return false;
        }
    }

    private int squareAndSum(int n) {
        int sum = 0;
        while (n != 0) {
            int rem = n % 10;
            n = n / 10;
            sum = sum + (rem * rem);
        }
        return sum;
    }


    /**
     * 递归
     */
    HashSet set = new HashSet<>();

    public boolean isHappyV4(int n) {

        set.add(n);
        if (n == 1) {
            return true;
        }
        int ans = 0;
        int temp = 0;

        while (n > 0) {
            temp = n % 10;

            ans = ans + (temp * temp);

            n = n / 10;
        }
        if (set.contains(ans)) {
            return false;
        }
        set.add(ans);
        return isHappy(ans);

    }

    public static void main(String[] args) {

        int value = 7;
        HappyNumber happyNumber = new HappyNumber();
        boolean result = happyNumber.isHappyV2(value);
        System.out.println(result);
    }
}