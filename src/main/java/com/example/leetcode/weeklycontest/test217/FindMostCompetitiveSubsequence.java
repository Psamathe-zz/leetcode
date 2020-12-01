package com.example.leetcode.weeklycontest.test217;


import java.util.ArrayDeque;
import java.util.Deque;

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
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 1 <= k <= nums.length
 */
public class FindMostCompetitiveSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{2,4,3,3,5,4,9,6};
        int k = 4;
        FindMostCompetitiveSubsequence findMostCompetitiveSubsequence = new FindMostCompetitiveSubsequence();
        findMostCompetitiveSubsequence.mostCompetitive(nums,k);
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
}
