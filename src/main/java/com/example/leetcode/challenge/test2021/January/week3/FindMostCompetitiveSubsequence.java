package com.example.leetcode.challenge.test2021.January.week3;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 *
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 *
 * We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,6], k = 2
 * Output: [2,6]
 * Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 * Example 2:
 *
 * Input: nums = [2,4,3,3,5,4,9,6], k = 4
 * Output: [2,3,3,4]
 *
 *
 * Constraints:
 */
public class FindMostCompetitiveSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{3,5,2,6};
        int k = 2;
        FindMostCompetitiveSubsequence findMostCompetitiveSubsequence = new FindMostCompetitiveSubsequence();
        findMostCompetitiveSubsequence.mostCompetitive(nums, k);
    }

    /**
     * https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/solution/java-dan-diao-dui-lie-hua-dong-chuang-kou-by-deena/
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        Deque<Integer> queue = new ArrayDeque<>();
        int index = 0;
        int len = n - k + 1;
        for(int i = 0; i < n; i++) {
            //队头滑出
            if(queue.size() > 0 && (queue.peekFirst() < i - len + 1)){
                queue.pollFirst();
            }
            //与新进来元素比较，维护单调性
            while(queue.size() > 0 && nums[queue.peekLast()] > nums[i]){
                queue.pollLast();
            }
            //新元素入队
            queue.addLast(i);
            //更新答案
            if(i >= len - 1) {
                res[index++] = nums[queue.pollFirst()];
            }
        }
        return res;
    }

    /**
     * 容易理解
     * https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/solution/java-dan-diao-zhan-by-thedesalizes/
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitiveV0(int[] nums, int k) {

        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            //当前元素比队尾元素小，将队尾元素出栈
            //此处需要另外判断数组剩余长度够不够填满栈，不然最后答案长度可能会小于k
            while (nums[i] < stack.peek() && k - stack.size() + 1 < len - i) {
                stack.pop();
            }
            if (stack.size() < k + 1) {
                stack.add(nums[i]);
            }
        }

        int[] ret = new int[k];

        while (k > 0) {
            ret[--k] = stack.pop();
        }

        return ret;
    }


    public int[] mostCompetitiveOld(int[] nums, int k) {
        int length = nums.length;
        int[] res = new int[k];
        int index = 0;
        int start = 0;
        int i;
        int minIndex;
        while (index < k){
            minIndex = start;
            for (i = start; i <= length - (k-index) ; i++) {
                if(nums[i] < nums[minIndex]){
                    minIndex = i;
                }
            }
            res[index] = nums[minIndex];
            start = minIndex+1;
            index++;
        }
        return res;
    }
}

