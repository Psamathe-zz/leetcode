package com.example.leetcode.weeklycontest.old.test255;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * Example 2:
 *
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * Example 3:
 *
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 */
public class UniqueBinaryString {
    public static void main(String[] args) {
        UniqueBinaryString uniqueBinaryString = new UniqueBinaryString();
        String res = uniqueBinaryString.findDifferentBinaryString(new String[]{"01","10"});
        System.out.println(res);
    }


    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> set = new HashSet<>();
        for (String num : nums){
            set.add(Integer.parseInt(num, 2));
        }
        String str;
        char[] chars;
        int length = nums[0].length();
        for (int i = 0; i < Math.pow(2,length); i++) {
            if(!set.contains(i)){
                str = Integer.toBinaryString(i);
                chars = new char[length - str.length()];
                Arrays.fill(chars, '0');
                return String.valueOf(chars) + str;
            }
        }
        return Integer.toBinaryString(0);
    }
}
