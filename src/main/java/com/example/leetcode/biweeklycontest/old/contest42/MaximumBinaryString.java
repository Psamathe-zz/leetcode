package com.example.leetcode.biweeklycontest.old.contest42;


import java.util.Arrays;

/**
 * You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following operations any number of times:
 *
 * Operation 1: If the number contains the substring "00", you can replace it with "10".
 * For example, "00010" -> "10010"
 * Operation 2: If the number contains the substring "10", you can replace it with "01".
 * For example, "00010" -> "00001"
 * Return the maximum binary string you can obtain after any number of operations. Binary string x is greater than binary string y if x's decimal representation is greater than y's decimal representation.
 *
 *
 *
 * Example 1:
 *
 * Input: binary = "000110"
 * Output: "111011"
 * Explanation: A valid transformation sequence can be:
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 * Example 2:
 *
 * Input: binary = "01"
 * Output: "01"
 * Explanation: "01" cannot be transformed any further.
 *
 *
 * Constraints:
 *
 * 1 <= binary.length <= 105
 * binary consist of '0' and '1'.
 */
public class MaximumBinaryString {
    public static void main(String[] args) {
        MaximumBinaryString maximumBinaryString = new MaximumBinaryString();
        String res = maximumBinaryString.maximumBinaryString("000110");
        System.out.println(res);
    }

    public String maximumBinaryString(String binary) {
        int len = binary.length();
        if(len < 2)
            return binary;
        char[] chs = binary.toCharArray();
        for(int i = 1; i < len; ++i) {
            if(chs[i-1]=='0' && chs[i]=='0'){   //用方法1转换开头连续为0的串
                chs[i-1] = '1';
            }
            else
                break;
        }
        int cnt0 = 0, idx = 0;  //统计的个数和首位0出现的下标
        for(int i = 0; i < len; ++i) {
            if(chs[i]=='0') {
                if(++cnt0 == 1)
                    idx = i;
                chs[i] = '1';
            }
        }
        if(cnt0 > 0)
            chs[idx+cnt0-1] = '0'; //首位0的位移
        return new String(chs);
    }
}
