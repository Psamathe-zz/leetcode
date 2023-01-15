package com.example.leetcode.biweeklycontest.contest90;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * You are given a 0-indexed array of non-negative integers nums. For each integer in nums, you must find its respective second greater integer.
 *
 * The second greater integer of nums[i] is nums[j] such that:
 *
 * j > i
 * nums[j] > nums[i]
 * There exists exactly one index k such that nums[k] > nums[i] and i < k < j.
 * If there is no such nums[j], the second greater integer is considered to be -1.
 *
 * For example, in the array [1, 2, 4, 3], the second greater integer of 1 is 4, 2 is 3, and that of 3 and 4 is -1.
 * Return an integer array answer, where answer[i] is the second greater integer of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,0,9,6]
 * Output: [9,6,6,-1,-1]
 * Explanation:
 * 0th index: 4 is the first integer greater than 2, and 9 is the second integer greater than 2, to the right of 2.
 * 1st index: 9 is the first, and 6 is the second integer greater than 4, to the right of 4.
 * 2nd index: 9 is the first, and 6 is the second integer greater than 0, to the right of 0.
 * 3rd index: There is no integer greater than 9 to its right, so the second greater integer is considered to be -1.
 * 4th index: There is no integer greater than 6 to its right, so the second greater integer is considered to be -1.
 * Thus, we return [9,6,6,-1,-1].
 * Example 2:
 *
 * Input: nums = [3,3]
 * Output: [-1,-1]
 * Explanation:
 * We return [-1,-1] since neither integer has any integer greater than it.
 *
 */
public class NextGreaterElementIV {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/next-greater-element-iv/solution/by-rigwk-jx9i/
     * @param nums
     * @return
     */
    public int[] secondGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        //先全部设置为-1
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        //单调栈看后面是否有一个数比peek大,有就弹出放入优先队列然后继续判断
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        /*按照从小到大排序
          之后如果nums里有比peek大的话就一个个弹出给res赋值然后继续判断*/
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);

        //没啥说的这里，先判断优先队列里再判断单调栈
        for (int i = 0; i < nums.length; i++) {
            while (!pq.isEmpty() && nums[i] > nums[pq.peek()]) {
                res[pq.poll()] = nums[i];
            }
            while (!ad.isEmpty() && nums[i] > nums[ad.getLast()]) {
                pq.add(ad.pollLast());
            }
            ad.addLast(i);
        }
        return res;
    }
}
