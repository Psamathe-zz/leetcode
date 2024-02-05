package com.example.leetcode.weeklycontest.old.test275;

/**
 * A swap is defined as taking two distinct positions in an array and swapping the values in them.
 *
 * A circular array is defined as an array where we consider the first element and the last element to be adjacent.
 *
 * Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,1,1,0,0]
 * Output: 1
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [0,0,1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0,0,0] using 1 swap.
 * [1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
 * There is no way to group all 1's together with 0 swaps.
 * Thus, the minimum number of swaps required is 1.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,0,1,1,0]
 * Output: 2
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
 * [1,1,1,1,1,0,0,0,0] using 2 swaps.
 * There is no way to group all 1's together with 0 or 1 swaps.
 * Thus, the minimum number of swaps required is 2.
 * Example 3:
 *
 * Input: nums = [1,1,0,0,1]
 * Output: 0
 * Explanation: All the 1's are already grouped together due to the circular property of the array.
 * Thus, the minimum number of swaps required is 0.
 */
public class MinimumSwaps {
    public static void main(String[] args) {
        MinimumSwaps minimumSwaps = new MinimumSwaps();
        minimumSwaps.minSwaps(new int[]{0,1,0,1,1,0,0});
    }

    public int minSwaps(int[] nums) {
        int length = nums.length;
        int[] arr = new int[length * 2];
        int maxOne = 0;
        for (int i = 0; i < length; i++) {
            if(nums[i] == 1)
                maxOne++;
            arr[i] = nums[i];
            arr[i + length] = nums[i];
        }

        int max = 0;
        int countOne = 0;
        for (int i = 0; i < maxOne; i++) {
            if(arr[i] == 1)
                countOne++;
        }
        max = countOne;

        for (int i = maxOne; i < length + maxOne; i++) {
            if(arr[i - maxOne] == 1)
                countOne--;
            if(arr[i] == 1)
                countOne++;
            max = Math.max(max, countOne);
        }
        return maxOne - max;

    }
}
