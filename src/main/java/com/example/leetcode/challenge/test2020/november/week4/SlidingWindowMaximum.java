package com.example.leetcode.challenge.test2020.november.week4;

import java.util.TreeMap;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 *
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = new int[]{9,11};
        int k = 2;
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        slidingWindowMaximum.maxSlidingWindow(nums,k);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;

        int[] res = new int[length - k + 1];
        TreeMap<Integer,Integer> map = new TreeMap<>();

        for (int i = 0; i < k; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        res[0] = map.lastKey();

        for (int i = k; i < length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            int v = map.get(nums[i - k]);
            if(v == 1){
                map.remove(nums[i - k]);
            } else
                map.put(nums[i - k],v-1);
            res[i - k + 1] = map.lastKey();
        }

        return res;
    }

    /**
     * faster solution
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowV1(int[] nums, int k) {
        int length = nums.length;
        if (length*k == 0)
            return new int[0];
        if (k == 1) return nums;

        int[] left = new int[length], right = new int[length];
        left[0] = nums[0];
        right[length-1] = nums[length-1];
        for (int i = 1; i < length; i++){
            // left to right direction
            // beginning of a sub-block
            if (i%k == 0) left[i] = nums[i];
            else left[i] = Math.max(left[i-1],nums[i]);
            // right to left direction
            int j = length-i-1;
            if ((j+1)%k == 0) right[j] = nums[j];
            else right[j] = Math.max(right[j+1], nums[j]);
        }

        int[] result = new int[length-k+1];
        for (int i = 0; i < length-k+1; i++){
            result[i] = Math.max(left[i+k-1], right[i]);
        }
        return result;
    }
}
