package com.example.leetcode.challenge.test2022.february;

import java.util.TreeSet;
/**
 * You are given an array nums of n positive integers.
 *
 * You can perform two types of operations on any element of the array any number of times:
 *
 * If the element is even, divide it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
 * If the element is odd, multiply it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
 * The deviation of the array is the maximum difference between any two elements in the array.
 *
 * Return the minimum deviation the array can have after performing some number of operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
 * Example 2:
 *
 * Input: nums = [4,1,5,20,3]
 * Output: 3
 * Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
 * Example 3:
 *
 * Input: nums = [2,10,8]
 * Output: 3
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 105
 * 1 <= nums[i] <= 109
 */
public class MinimizeDeviationArray {

    /**
     *
     //相当于枚举所有情况，
     //第一步操作：为啥是要把所有数变成最大偶数，是偶数的时候只能做除2操作；
     //所以将每个数开始变为最大的偶数（若是偶数，就是本身，相反则乘2操作就是偶数）
     //第二步操作：然后依次讲最大的数做除2操作，直到遇到第一个最大值是奇数时，表示结束条件；
     //结束条件：第一个最大值是奇数时，就代表结束了呢，
     //TreeSet 默认是有序的；
     * @param nums
     * @return
     */
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set=new TreeSet<>();
        for(int x:nums){
            set.add(x%2==0?x:x*2);
        }
        int res=set.last()-set.first();
        int mx;
        while(set.last()%2==0){
            mx=set.last();
            set.remove(mx);
            set.add(mx/2);
            res=Math.min(res,set.last()-set.first());
        }
        return res;
    }
}
