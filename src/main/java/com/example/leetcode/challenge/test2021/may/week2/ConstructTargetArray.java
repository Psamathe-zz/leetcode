package com.example.leetcode.challenge.test2021.may.week2;


import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :
 *
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return True if it is possible to construct the target array from A otherwise return False.
 *
 *
 *
 * Example 1:
 *
 * Input: target = [9,3,5]
 * Output: true
 * Explanation: Start with [1, 1, 1]
 * [1, 1, 1], sum = 3 choose index 1
 * [1, 3, 1], sum = 5 choose index 2
 * [1, 3, 5], sum = 9 choose index 0
 * [9, 3, 5] Done
 * Example 2:
 *
 * Input: target = [1,1,1,2]
 * Output: false
 * Explanation: Impossible to create target array from [1,1,1,1].
 * Example 3:
 *
 * Input: target = [8,5]
 * Output: true
 */
public class ConstructTargetArray {
    public static void main(String[] args) {
        ConstructTargetArray constructTargetArray = new ConstructTargetArray();
        boolean res = constructTargetArray.isPossible(new int[]{8,5});
        System.out.println(res);
    }

    /**
     * https://leetcode.jp/leetcode-1354-construct-target-array-with-multiple-sums-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
     * https://zhuanlan.zhihu.com/p/128521927
     * 999999999次减一计算才能将target数组变成 [1,1]。这时我们可以直接使用求余运算，余数即是max前身。
     * @param target
     * @return
     */

    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        long sum = 0;
        for (int val : target) {
            queue.offer(val);
            sum += val;
        }

        int val;
        long rest;
        while (sum > target.length) {
            val = queue.poll();
            rest = sum - val;
            if(rest == 0 || rest >= val)
                return false;
            int pre = (int)( val % rest);
            if( pre==0 )
                return rest==1;
            sum -= val - pre;
            queue.offer(pre);
        }

        return true;
    }
}
