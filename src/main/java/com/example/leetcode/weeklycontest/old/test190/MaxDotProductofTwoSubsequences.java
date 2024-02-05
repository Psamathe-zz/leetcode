package com.example.leetcode.weeklycontest.old.test190;

/**
 * Given two arrays nums1 and nums2.
 *
 * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
 *
 * A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters.
 * (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * Output: 18
 * Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
 * Their dot product is (2*3 + (-2)*(-6)) = 18.
 * Example 2:
 *
 * Input: nums1 = [3,-2], nums2 = [2,-6,7]
 * Output: 21
 * Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
 * Their dot product is (3*7) = 21.
 * Example 3:
 *
 * Input: nums1 = [-1,-1], nums2 = [1,1]
 * Output: -1
 * Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
 * Their dot product is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 * [5,-4,-3]
 * [-4,-3,0,-4,2]
 * [5,1,8,10,2,9,-4]
 * [-6,-5,5,-10,3,4,-7,8]
 */
public class MaxDotProductofTwoSubsequences {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2,1,-2,5};
        int[] nums2 = new int[]{3,0,-6};
        MaxDotProductofTwoSubsequences maxDotProductofTwoSubsequences = new MaxDotProductofTwoSubsequences();
        int result = maxDotProductofTwoSubsequences.maxDotProduct(nums1,nums2);
        System.out.println(result);
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int result = Integer.MIN_VALUE;
        int[][] maxMatrics = new int[nums1.length][nums2.length];
        int[][] matrics = new int[nums1.length][nums2.length];
        int maxTemp;
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0 ; j--) {
                maxMatrics[i][j] = nums1[i] * nums2[j];
                matrics[i][j] = nums1[i] * nums2[j];
                if(i!= nums1.length - 1 && j != nums2.length - 1){
                    maxTemp = Integer.MIN_VALUE;
                    for (int k = i + 1; k < nums1.length; k++) {
                        if(maxTemp < maxMatrics[k][j+1])
                            maxTemp = maxMatrics[k][j+1];
                    }
                    for (int l = j + 1; l < nums2.length; l++) {
                        if(maxTemp < maxMatrics[i+1][l])
                            maxTemp = maxMatrics[i+1][l];
                    }
                    if(maxMatrics[i][j] > 0) {
                        maxMatrics[i][j] = Math.max(maxTemp, 0) + maxMatrics[i][j];
                        matrics[i][j] = maxMatrics[i][j];
                    } else {
                        maxMatrics[i][j] = Math.max(maxTemp, 0);
                        matrics[i][j] = maxMatrics[i][j] + matrics[i][j];
                    }
                }
                if(result < matrics[i][j] )
                    result = matrics[i][j];
            }
        }
        return result;
    }
    public int maxDotProductV1(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[][] dp = new int[l1][l2];
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = nums1[0] * nums2[0];
                } else if (i == 0) {
                    dp[i][j] = Math.max(nums1[i] * nums2[j], dp[i][j-1]);
                } else if (j == 0) {
                    dp[i][j] = Math.max(nums1[i] * nums2[j], dp[i-1][j]);
                } else {
                    int val = Math.max(dp[i][j-1], dp[i-1][j]);
                    dp[i][j] = Math.max(nums1[i] * nums2[j], Math.max(dp[i-1][j-1] + nums1[i] * nums2[j], val));
                }
            }
        }
        return dp[l1-1][l2-1];
    }
}
