package com.example.leetcode.weeklycontest.test259;

import java.util.Map;
import java.util.TreeMap;

/**
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 *
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 2.
 * Example 2:
 *
 * Input: nums = [2,4,6,4]
 * Output: 1
 * Explanation: For each index i in the range 1 <= i <= 2:
 * - The beauty of nums[1] equals 1.
 * - The beauty of nums[2] equals 0.
 * Example 3:
 *
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 0.
 * [3,48,33,17,21,95,24,67,89,1,50,76,6,32,20,5,1,45,79,81,96,96,15,37,44,63,4,40,58,71,99,78,95,6,34,97,52,80,91,20,61,29,12,85,88,41,14,4,58,17,67,75,100,51,63,66,42,19,44,34,34,78,54,84,3,90,72,18,86,8,33,5,17,21,22,13,59,82,30,66,91,5,32,30,92,57,10,33,11,76,30,80,80,91,47,33]
 */
public class SumBeauty {
    public static void main(String[] args) {
        SumBeauty sumBeauty = new SumBeauty();
        sumBeauty.sumOfBeauties(new int[]{3,48,33,17,21,95,24,67,89,1,50,76,6,32,20,5,1,45,79,81,96,96,15,37,44,63,4,40,58,71,99,78,95,6,34,97,52,80,91,20,61,29,12,85,88,41,14,4,58,17,67,75,100,51,63,66,42,19,44,34,34,78,54,84,3,90,72,18,86,8,33,5,17,21,22,13,59,82,30,66,91,5,32,30,92,57,10,33,11,76,30,80,80,91,47,33});
    }


    public int sumOfBeauties(int[] nums) {
        int length = nums.length;
        int res = 0;
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int i = 2; i < length; i++) {
            count.compute(nums[i], (k,v) -> {
               if(v == null)
                   return 1;
               else
                   return v + 1;
            });
        }

        int preMax = nums[0];
        int postMin;
        for (int i = 1; i <= length - 2; i++) {
            postMin = count.firstKey();
            if(nums[i] > preMax && nums[i] < postMin){
                res += 2;
                System.out.println("2" + i);
            } else if(nums[i - 1] < nums[i] && nums[i] < nums[i + 1]){
                res += 1;
                System.out.println("1" +i);
            }
            preMax = Math.max(preMax, nums[i]);
            int t = count.get(nums[i + 1]);
            if(t == 1)
                count.remove(nums[i + 1]);
            else
                count.put(nums[i + 1], t - 1);
        }

        return res;
    }
}
