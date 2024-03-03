package com.example.leetcode.biweeklycontest.old.contest61;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
 *
 * nums is considered continuous if both of the following conditions are fulfilled:
 *
 * All elements in nums are unique.
 * The difference between the maximum element and the minimum element in nums equals nums.length - 1.
 * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
 *
 * Return the minimum number of operations to make nums continuous.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,3]
 * Output: 0
 * Explanation: nums is already continuous.
 * Example 2:
 *
 * Input: nums = [1,2,3,5,6]
 * Output: 1
 * Explanation: One possible solution is to change the last element to 4.
 * The resulting array is [1,2,3,5,4], which is continuous.
 * Example 3:
 *
 * Input: nums = [1,10,100,1000]
 * Output: 3
 * Explanation: One possible solution is to:
 * - Change the second element to 2.
 * - Change the third element to 3.
 * - Change the fourth element to 4.
 * The resulting array is [1,2,3,4], which is continuous.
 */
public class MinimumNumberOperationsMakeContinuous {
    public static void main(String[] args) {
        MinimumNumberOperationsMakeContinuous minimumNumberOperationsMakeContinuous = new MinimumNumberOperationsMakeContinuous();
        minimumNumberOperationsMakeContinuous.minOperations(new int[]{1,10,100,1000});
    }

    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for(int i=0; i<n; i++) {
            if(set.contains(nums[i])) {
                count++;
                nums[i] = -1;
            } else {
                set.add(nums[i]);
            }
        }
        Arrays.sort(nums);
        int left = count, right = count;
        while(right < n) {
            int min = nums[left];
            int max = min + n - 1;
            while(right < n && nums[right] <= max) {
                right++;
            }
            ans = Math.max(ans, right - left);
            left++;
        }
        return n - ans;
    }
}
