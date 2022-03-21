package com.example.leetcode.challenge.test2022.february;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 *
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.summaryRanges(new int[]{0,2,3,4,6,8,9});
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int length = nums.length;
        if(length == 0)
            return res;


        int pre = nums[0];
        int start = nums[0];
        for (int i = 1; i < length; i++) {
            if(pre + 1 != nums[i]) {
                if(start != pre) {
                    res.add(start+"->"+pre);
                } else {
                    res.add(String.valueOf(start));
                }
                start = nums[i];
            }
            pre = nums[i];
        }
        if(start != pre) {
            res.add(start+"->"+pre);
        } else {
            res.add(String.valueOf(start));
        }
        return res;
    }
}
