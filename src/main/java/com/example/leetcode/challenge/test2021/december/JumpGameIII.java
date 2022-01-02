package com.example.leetcode.challenge.test2021.december;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 */
public class JumpGameIII {
    public static void main(String[] args) {
        JumpGameIII jumpGameIII = new JumpGameIII();
        boolean res = jumpGameIII.canReach(new int[]{4,2,3,0,3,1,2}, 0);
        System.out.println(res);
    }
    boolean[] visited;
    public boolean canReach(int[] arr, int start) {
        visited = new boolean[arr.length];
        return helper(arr, start);
    }

    public boolean helper(int[] arr, int start) {
        if(start < 0 || start >= arr.length || visited[start])
            return false;
        if(arr[start] == 0)
            return true;
        visited[start] = true;
        boolean res = helper(arr, start + arr[start]) || helper(arr, start - arr[start]);
        return res;
    }
}
