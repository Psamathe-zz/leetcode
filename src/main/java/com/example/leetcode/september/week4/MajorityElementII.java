package com.example.leetcode.september.week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII {
    public static void main(String[] args) {

    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> count = new HashMap<>();
        int times;
        for (int v : nums){
            times = count.getOrDefault(v,0)+1;
            if(times > nums.length /3 && !res.contains(v))
                res.add(v);
            count.put(v ,times );
        }
        return res;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public List<Integer> majorityElementV1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;

        int cand1 = 0, cand2 = 0;

        int count1 = 0, count2 = 0;

        /**
         * firstly, find bigger count
         */
        for(int num : nums){
            if(num == cand1){
                count1 ++;
                continue;
            }

            if(num == cand2){
                count2 ++;
                continue;
            }

            if(count1 == 0){
                cand1 = num;
                count1 = 1;
                continue;
            }

            if(count2 == 0){
                cand2 = num;
                count2 = 1;
                continue;
            }

            count1 --;
            count2 --;

        }

        /**
         * then compte them
         */
        count1 = 0;
        count2 = 0;

        for(int num : nums){
            if(num == cand1){
                count1 ++;
            }else if(num == cand2){
                count2 ++;
            }
        }

        if(count1 > nums.length / 3)
            result.add(cand1);

        if(count2 > nums.length / 3)
            result.add(cand2);

        return result;
    }
}
