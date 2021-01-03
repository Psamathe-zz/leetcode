package com.example.leetcode.challenge.april.week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

 */
public class SingleNumber {

    public static void main(String[] args) {

        int[] nums1 = new int[]{11,2,2};
        SingleNumber singleNumber = new SingleNumber();
        int result = singleNumber.singleNumberV5(nums1);
        System.out.println(result);
    }


    public int singleNumber(int[] nums) {
        int length = nums.length;
        if(length == 1)
            return nums[0];
        if(length%2 == 0)
            return 0;
        int time;
        for(int i = 0;i<length;i++){
            time = 0;
            for(int j = 0;j<length;j++){
                if(i!=j && nums[i]==nums[j]){
                    time++;
                    break;
                }
            }
            if(time == 0){
                return nums[i];
            }
        }
        return 0;
    }

    /*
    异或
    如果相对应位值相同，则结果为0，否则为1
    对一个数异或两次等于没有异或
    a ^ b ^ b = a;
    x ^ x = 0
    0 ^ x = x.
     */
    public int singleNumberV2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int ans = 0;
        for(int i=0; i<n; i++) {
            ans  = ans ^ nums[i];
            System.out.println(ans);
        }

        return ans;
    }
    /*
    也是异或
     */
    public int singleNumberV3(int[] nums) {
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            b = ~b & nums[i] | b & ~nums[i];
        }
        return b;
    }

    /*
    异或求和 => 相当于异或
     */
    public int singleNumberV4(int[] nums) {
        return Arrays.stream(nums).reduce(0, (acc, x) -> acc ^ x);
    }

    /**
     *
     * @param nums
     * @return
     */

    public int singleNumberV5(int[] nums) {

        HashMap<Integer, Integer> freq = new HashMap<>();

        for(int n : nums) {
            freq.put(n , freq.getOrDefault(n,0) + 1);
        }

        /**
         * 使用PriorityQueue 对HashMap 的 vaule 排序，第一个就是唯一值
         */
        PriorityQueue<Integer> min = new PriorityQueue<Integer>((n1, n2) -> freq.get(n1) - freq.get(n2));

        for(int key : freq.keySet()) {
            min.add(key);
        }
        return min.remove();
    }


}
