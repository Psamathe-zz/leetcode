package com.example.leetcode.weeklycontest.test229;


/**
 * You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
 *
 * In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
 *
 * Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
 *
 * Each answer[i] is calculated considering the initial state of the boxes.
 *
 *
 *
 * Example 1:
 *
 * Input: boxes = "110"
 * Output: [1,1,3]
 * Explanation: The answer for each box is as follows:
 * 1) First box: you will have to move one ball from the second box to the first box in one operation.
 * 2) Second box: you will have to move one ball from the first box to the second box in one operation.
 * 3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
 * Example 2:
 *
 * Input: boxes = "001011"
 * Output: [11,8,5,4,3,4]
 *
 *
 * Constraints:
 *
 * n == boxes.length
 * 1 <= n <= 2000
 * boxes[i] is either '0' or '1'.
 */
public class MinimumNumberOperations {
    public static void main(String[] args) {

    }

    public int[] minOperations(String boxes) {
        int length = boxes.length();
        int[] res = new int[length];
        int count;
        for (int i = 0; i < length; i++) {
            count = 0;
            for (int j = 0; j < length; j++) {
                if(i != j && boxes.charAt(j) == '1'){
                    count += Math.abs(i - j);
                }
            }
            res[i] = count;
        }
        return res;
    }

    public int[] minOperationsV1(String boxes) {
        int curr = 0;
        int[] count = new int[2];
        int n = boxes.length();
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                curr += i;
                count[1]++;
            }
        }
        int[] ans = new int[n];
        ans[0] = curr;
        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i-1) == '1') {
                count[0]++;
                count[1]--;
            }
            curr += count[0] - count[1];
            ans[i] = curr;
        }
        return ans;
    }
}
