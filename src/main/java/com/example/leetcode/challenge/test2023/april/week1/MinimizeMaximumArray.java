package com.example.leetcode.challenge.test2023.april.week1;

/**
 * You are given a 0-indexed array nums comprising of n non-negative integers.
 *
 * In one operation, you must:
 *
 * Choose an integer i such that 1 <= i < n and nums[i] > 0.
 * Decrease nums[i] by 1.
 * Increase nums[i - 1] by 1.
 * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,7,1,6]
 * Output: 5
 * Explanation:
 * One set of optimal operations is as follows:
 * 1. Choose i = 1, and nums becomes [4,6,1,6].
 * 2. Choose i = 3, and nums becomes [4,6,2,5].
 * 3. Choose i = 1, and nums becomes [5,5,2,5].
 * The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
 * Therefore, we return 5.
 * Example 2:
 *
 * Input: nums = [10,1]
 * Output: 10
 * Explanation:
 * It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
 */
public class MinimizeMaximumArray {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimize-maximum-of-array/solution/cppjava-you-shi-yi-dao-jing-dian-de-er-f-w3i6/
     * @param nums
     * @return
     */
    static boolean check(int[] nums, int k) {
        long have = 0;//前方的数字还可以帮我们后方的大数承载多少数字
        for (int n : nums) {
            if (n <= k) {
                have += k - n;//较小数，可以算入承载量
            } else {
                if (have < n - k) return false;//承载不了了，该答案不可行
                else have -= (n - k);//减去承载量
            }
        }
        return true;
    }

    public int minimizeArrayValue(int[] nums) {
        int left = 0, right = 1000000000;
        while (left < right) {//二分答案，寻找最大值
            int mid = left + (right - left) / 2;
            if (check(nums, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
