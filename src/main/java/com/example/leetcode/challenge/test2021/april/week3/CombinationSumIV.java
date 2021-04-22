package com.example.leetcode.challenge.test2021.april.week3;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Example 2:
 *
 * Input: nums = [9], target = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 *
 *
 * Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
 * [3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
 * 1
 */
public class CombinationSumIV {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        int target = 2;
        CombinationSumIV combinationSumIV = new CombinationSumIV();
        int res = combinationSumIV.combinationSum4(nums, target);
        System.out.println(res);
    }

    Map<Integer,Integer> map;
    int length;
    public int combinationSum4(int[] nums, int target) {
        map = new HashMap<>();
        Arrays.sort(nums);
        length = nums.length;
        int res =  helper(nums, target);
        return res;
    }

    public int helper(int[] nums, int target){
        if(map.containsKey(target))
            return map.get(target);
        if(target == 0)
            return 1;
        int res = 0;
        int val;
        for (int i = 0; i < length; i++) {
            val = nums[i];
            if(val > target)
                break;
            res += helper(nums, target - val);
        }
        map.put(target,res);
        return res;
    }
}
