package com.example.leetcode.biweeklycontest.old.contest47;


import java.util.Arrays;

/**
 * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
 *
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * Given a string s, return the sum of beauty of all of its substrings.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabcb"
 * Output: 5
 * Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
 * Example 2:
 *
 * Input: s = "aabcbaa"
 * Output: 17
 */
public class SumBeauty {
    public static void main(String[] args) {
        SumBeauty sumBeauty = new SumBeauty();
        sumBeauty.beautySum("aabcb");

    }

    public int beautySum(String s) {
        int n = s.length(),sum = 0;
        int[] alphbet = new int[26];
        char[] cs = s.toCharArray();
        for (int i = 0; i < n-1; i++) {
            Arrays.fill(alphbet,0);
            alphbet[cs[i]-'a']++;
            alphbet[cs[i+1]-'a']++;
            for (int j = i+2; j < n; j++) {
                alphbet[cs[j]-'a']++;
                int max = 0;
                int min = j-i+1;
                for (int k = 0; k < 26; k++) {
                    int val = alphbet[k];
                    if(val>0){
                        max = Math.max(max,val);
                        min = Math.min(min,val);
                    }
                }
                sum += max - min;
            }
        }
        return sum;
    }
}
