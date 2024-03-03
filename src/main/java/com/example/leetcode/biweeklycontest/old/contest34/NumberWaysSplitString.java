package com.example.leetcode.biweeklycontest.old.contest34;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).
 *
 * Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
 * "1|010|1"
 * "1|01|01"
 * "10|10|1"
 * "10|1|01"
 * Example 2:
 *
 * Input: s = "1001"
 * Output: 0
 * Example 3:
 *
 * Input: s = "00000"
 * Output: 3
 * Explanation: There are three ways to split s in 3 parts.
 * "0|0|00"
 * "0|00|0"
 * "00|0|0"
 * Example 4:
 *
 * Input: s = "100100010100110"
 * Output: 12
 *
 *
 * Constraints:
 *
 * s[i] == '0' or s[i] == '1'
 * 3 <= s.length <= 10^5
 */
public class NumberWaysSplitString {
    public static void main(String[] args) {
        NumberWaysSplitString numberWaysSplitString =  new NumberWaysSplitString();
        int res = numberWaysSplitString.numWays("100100010100110");
        System.out.println(res);
    }

    public int numWays(String s) {
        int count1 = 0;
        int length = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            if(chars[i] == '1'){
                count1++;
            }
        }
        if(count1 % 3 != 0)
            return 0;
        else if(count1 == 0) {
            long res = 0;
            for (int i = 1; i < length - 1 ; i++) {
                res += i;
                res = res % 1000000007;
            }
            return (int)res;
        } else {
            int size = count1/3;
            int a = -1,b = -1;
            int resa = 1,resb = 1;
            int index = 0;
            for (int i = 0; i < length; i++) {
                if(chars[i] == '1'){
                    index++;
                    if(index == size){
                        a = i;
                    }
                    if(index == size + 1){
                        resa = i - a;
                    }
                    if(index == size * 2){
                        b = i;
                    }
                    if(index == size * 2 + 1){
                        resb = i - b;
                    }
                }
            }
            Stack<Integer> stack = new Stack<>();
            int res = 0;
            int val;
            while (resb > 0){
                val = resb%10;
                resb = resb/10;
                stack.add(resa * val);
            }
            while (!stack.isEmpty()){
                res = res * 10 + stack.pop();
                res = res % 1000000007;
            }
            return res;
        }
    }
}
