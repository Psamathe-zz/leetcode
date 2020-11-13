package com.example.leetcode.sometest;

import java.util.Arrays;

public class AddStone {
    public static void main(String[] args) {
        int[] ints = new int[]{-9, 14, 37, 102};
        int k = 102;
        AddStone addStone = new AddStone();
        boolean result = addStone.existsV3(ints,k);
        System.out.println(result);
    }

    String sum(String... numbers) {
        double total = 0;
        Long a1 = 0l;
        Long a2 = 0l;
        for (String number : numbers) {
            String[] strs = number.split(".");
            a1 += Long.parseLong(strs[0]);
            a2 += Long.parseLong(strs[1]);
        }
        return String.valueOf(a1) + "." + String.valueOf(a2);
    }

    boolean exists(int[] ints, int k) {

        for (int value : ints){
            if(value == k)
                return true;
        }
        return false;

    }

    boolean existsV2(int[] ints, int k) {

        return  Arrays.binarySearch(ints,k) >= 0 ? true: false ;

    }

    boolean existsV3(int[] ints, int k) {
        int length = ints.length;
        int left = 0;
        int right = length - 1;
        int mid;

        while (left <= right){
            mid = left + (right - left) / 2;
            if(ints[mid] == k)
                return true;
            else if(ints[mid] < k){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;

    }
}
