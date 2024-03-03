package com.example.leetcode.biweeklycontest.old.contest63;

/**
 * Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,5], nums2 = [3,4], k = 2
 * Output: 8
 * Explanation: The 2 smallest products are:
 * - nums1[0] * nums2[0] = 2 * 3 = 6
 * - nums1[0] * nums2[1] = 2 * 4 = 8
 * The 2nd smallest product is 8.
 * Example 2:
 *
 * Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
 * Output: 0
 * Explanation: The 6 smallest products are:
 * - nums1[0] * nums2[1] = (-4) * 4 = -16
 * - nums1[0] * nums2[0] = (-4) * 2 = -8
 * - nums1[1] * nums2[1] = (-2) * 4 = -8
 * - nums1[1] * nums2[0] = (-2) * 2 = -4
 * - nums1[2] * nums2[0] = 0 * 2 = 0
 * - nums1[2] * nums2[1] = 0 * 4 = 0
 * The 6th smallest product is 0.
 * Example 3:
 *
 * Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
 * Output: -6
 * Explanation: The 3 smallest products are:
 * - nums1[0] * nums2[4] = (-2) * 5 = -10
 * - nums1[0] * nums2[3] = (-2) * 4 = -8
 * - nums1[4] * nums2[0] = 2 * (-3) = -6
 * The 3rd smallest product is -6.
 * [-100000,100000]
 * [-100000,100000]
 * 1
 */
public class KthSmallestProduct {
    public static void main(String[] args) {
        KthSmallestProduct kthSmallestProduct = new KthSmallestProduct();
        kthSmallestProduct.kthSmallestProduct(new int[]{-100000,100000}, new int[]{-100000,100000},1);
    }
    int[] A, B;
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        A = nums1; B = nums2;
        long head = -10000000000L, tail = 10000000000L;
        while (head < tail) {
            long mid = (head + tail) >> 1;
            long x = helper(mid);
            if (x >= k)
                tail = mid;
            else
                head = mid + 1;
        }
        return head;
    }

    long helper(long lim) {
        long ret = 0;
        for (long x : A) {
            if (x > 0) {
                if (x * B[0] > lim) continue;
                int head = 0, tail = B.length - 1;
                while (head < tail) {
                    int mid = (head + tail + 1) >> 1;
                    if (x * B[mid] <= lim) head = mid;
                    else tail = mid - 1;
                }
                ret += head + 1;
            } else if (x < 0) {
                if (x * B[B.length - 1] > lim) continue;
                int head = 0, tail = B.length - 1;
                while (head < tail) {
                    int mid = (head + tail) >> 1;
                    if (x * B[mid] <= lim) tail = mid;
                    else head = mid + 1;
                }
                ret += B.length - head;
            } else if (lim >= 0) ret += B.length;
        }
        return ret;
    }
}
