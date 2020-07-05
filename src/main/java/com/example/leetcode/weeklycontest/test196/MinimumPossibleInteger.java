package com.example.leetcode.weeklycontest.test196;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string num representing the digits of a very large integer and an integer k.
 *
 * You are allowed to swap any two adjacent digits of the integer at most k times.
 *
 * Return the minimum integer you can obtain also as a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: num = "4321", k = 4
 * Output: "1342"
 * Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
 * Example 2:
 *
 * Input: num = "100", k = 1
 * Output: "010"
 * Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
 * Example 3:
 *
 * Input: num = "36789", k = 1000
 * Output: "36789"
 * Explanation: We can keep the number without any swaps.
 * Example 4:
 *
 * Input: num = "22", k = 22
 * Output: "22"
 * Example 5:
 *
 * Input: num = "9438957234785635408", k = 23
 * Output: "0345989723478563548"
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 30000
 * num contains digits only and doesn't have leading zeros.
 * 1 <= k <= 10^9
 * 9438957234785635408
 * 0943895723478563548 17
 */
public class MinimumPossibleInteger {
    public static void main(String[] args) {
        String num = "4321";
        int k = 4;
        MinimumPossibleInteger minimumPossibleInteger = new MinimumPossibleInteger();
        String result = minimumPossibleInteger.minInteger(num,k);
        System.out.println(result);
    }

    public String minInteger(String num, int k) {
        char[] arr=num.toCharArray(); // 为了方便计算，将字符串转为数组
        return String.valueOf(help(arr,k, 0)); // 递归求解
    }
    // arr：数组
    // k：当前剩余步数
    // index：当前高位
    char[] help(char[] arr,int k, int index){
        // 如果下标越界或者k为0，返回
        if(index==arr.length||k==0)
            return arr;
        // 找出当前高位之后最小的数字以及所在下标
        char min=arr[index];
        int minIndex=index;
        for(int i=index;i<arr.length&&i-index<=k;i++){
            if(arr[i]<min){
                min=arr[i];
                minIndex=i;
            }
        }
        if (min == arr[index])
            return help(arr, k, index+1);
        // 将当前高位到minIndex-1间的的数字分别向后移动一位
        for(int i=minIndex;i>index;i--){
            arr[i]=arr[i-1];
        }
        // 将最高位设置为最小值（相当于冒泡排序）
        arr[index]=min;
        // 递归求解次高位
        return help(arr,k-(minIndex-index),index+1);
    }


    public String minIntegerOld(String num, int k) {
        int length = num.length();
        char[] chars = num.toCharArray();
        char[] target = Arrays.copyOf(chars,length);
        int min;
        int index;
        char temp;
        Arrays.sort(target);
        int count = 0;
        while (k > 0){
            if(String.valueOf(target).equals(String.valueOf(chars))){
                break;
            }
            min = chars[count];
            index = count;
            for (int i = count + 1; i < Math.min(count + 1 + k,length); i++) {
                if(chars[i] < min){
                    min = chars[i];
                    index = i;
                }
            }
            for (int i = index; i > count ; i--) {
                temp = chars[i];
                chars[i] = chars[i - 1];
                chars[i - 1] = temp;
            }

            k -= (index - count);
            count++;
        }
        return String.valueOf(chars);
    }
}
