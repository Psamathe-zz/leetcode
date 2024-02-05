package com.example.leetcode.weeklycontest.old.test258;


/**
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.
 *
 * Return the maximum possible product of the lengths of the two palindromic subsequences.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * example-1
 * Input: s = "leetcodecom"
 * Output: 9
 * Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
 * The product of their lengths is: 3 * 3 = 9.
 * Example 2:
 *
 * Input: s = "bb"
 * Output: 1
 * Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
 * The product of their lengths is: 1 * 1 = 1.
 * Example 3:
 *
 * Input: s = "accbcaxxcxx"
 * Output: 25
 * Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
 * The product of their lengths is: 5 * 5 = 25.
 */
public class MaximumProduct {
    public static void main(String[] args) {

    }

    int ans = 0;
    public int maxProduct(String s) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        dfs(s, sb1, sb2, 0);
        return ans;
    }

    public void dfs(String s, StringBuilder sb1, StringBuilder sb2, int index) {
        if(check(sb1) && check(sb2)) {
            ans = Math.max(ans, sb1.length() * sb2.length());
        }

        if(index == s.length()) return;

        dfs(s, sb1.append(s.charAt(index)), sb2, index + 1); // 子序列s1 使用
        sb1.setLength(sb1.length() - 1);

        dfs(s, sb1, sb2.append(s.charAt(index)), index + 1); // 子序列 s2 使用
        sb2.setLength(sb2.length() - 1);

        dfs(s, sb1, sb2, index + 1); // 子序列 sb1 和 sb2 者均不使用
    }

    public boolean check(StringBuilder sb1) {  // 检查是否为回文字符串
        int left = 0, right = sb1.length() - 1;
        while(left < right) {
            if(sb1.charAt(left) == sb1.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

}
