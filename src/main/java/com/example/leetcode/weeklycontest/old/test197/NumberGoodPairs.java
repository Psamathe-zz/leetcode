package com.example.leetcode.weeklycontest.old.test197;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums.
 *
 * A pair (i,j) is called good if nums[i] == nums[j] and i < j.
 *
 * Return the number of good pairs.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 * Example 2:
 *
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class NumberGoodPairs {
    public static void main(String[] args) {

    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int value:nums){
            map.put(value,map.getOrDefault(value,0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            res += count(entry.getValue());
        }
        return res;
    }

    public int count(int n){
        if(n==1)
            return 0;
        int res = 1;
        for (int i = n - 1; i > 1 ; i--) {
            res += i;
        }
        return res;
    }
}
