package com.example.leetcode.weeklycontest.old.test231;


/**
 * Given a binary string s ​​​​​without leading zeros, return true​​​ if s contains at most one contiguous segment of ones. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1001"
 * Output: false
 * Explanation: The ones do not form a contiguous segment.
 * Example 2:
 *
 * Input: s = "110"
 * Output: true
 *
 */
public class CheckBinaryString {
    public static void main(String[] args) {

    }

    public boolean checkOnesSegment(String s) {
        int length = s.length();
        if(length <= 2)
            return true;
        char[] chars = s.toCharArray();
        boolean re = false;
        for (int i = 1; i < length; i++) {
            if(chars[i] == '0')
                re = true;
            else {
                if(re)
                    return  false;
            }
        }
        return true;
    }
}
