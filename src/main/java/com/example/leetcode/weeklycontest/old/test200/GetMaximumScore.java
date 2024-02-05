package com.example.leetcode.weeklycontest.old.test200;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 *
 * A valid path is defined as follows:
 *
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array. (Only one repeated value is considered in the valid path).
 * Score is defined as the sum of uniques values in a valid path.
 *
 * Return the maximum score you can obtain of all possible valid paths.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 * Example 2:
 *
 * Input: nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * Output: 109
 * Explanation: Maximum sum is obtained with the path [1,3,5,100].
 * Example 3:
 *
 * Input: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * Output: 40
 * Explanation: There are no common elements between nums1 and nums2.
 * Maximum sum is obtained with the path [6,7,8,9,10].
 * Example 4:
 *
 * Input: nums1 = [1,4,5,8,9,11,19], nums2 = [2,3,4,11,12]
 * Output: 61
 *
 */
public class GetMaximumScore {
    public static void main(String[] args) {

    }

    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/1133656/
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int module = 1000000007;
        long res = 0, sum1 = 0, sum2 = 0;
        int pos1 = 0, pos2 = 0;

        while (pos1 < m && pos2 < n) {
            if (nums1[pos1] < nums2[pos2])
                sum1 += nums1[pos1++];
            else if (nums1[pos1] > nums2[pos2])
                sum2 += nums2[pos2++];
            else {
                res += Math.max(sum1, sum2) + nums1[pos1++];
                ++pos2;
                sum1 = sum2 = 0;
            }
        }

        if (pos1 < m) {
            for (int i = pos1; i < m; ++i)
                sum1 += nums1[i];
        }
        if (pos2 < n) {
            for (int i = pos2; i < n; ++i)
                sum2 += nums2[i];
        }

        res += Math.max(sum1, sum2);
        res = res % module;
        return (int) res;
    }


    /**
     * faster solution
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxSumV1(int[] nums1, int[] nums2) {
        List<Integer> common = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < nums1.length; i++) {
            while (j < nums2.length && nums2[j] < nums1[i])
                j++;
            if (j == nums2.length)
                break;
            if (nums1[i] == nums2[j])
                common.add(nums1[i]);
        }
        long ans = 0;
        long seg1 = 0;
        long seg2 = 0;
        int i1 = 0;
        int i2 = 0;
        for (int x : common) {
            while (nums1[i1] < x)
                seg1 += nums1[i1++];
            while (nums2[i2] < x)
                seg2 += nums2[i2++];
            ans += Math.max(seg1, seg2) + x;
            i1++;
            i2++;
            seg1 = seg2 = 0;
        }
        while (i1 < nums1.length) seg1 += nums1[i1++];
        while (i2 < nums2.length) seg2 += nums2[i2++];
        ans += Math.max(seg1, seg2);
        return (int) (ans % 1000000007);
    }
}
