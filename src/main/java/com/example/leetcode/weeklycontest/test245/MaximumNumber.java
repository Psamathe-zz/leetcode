package com.example.leetcode.weeklycontest.test245;


/**
 * You are given two strings s and p where p is a subsequence of s. You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).
 *
 * You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices in removable, p is still a subsequence of s. More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then remove all marked characters and check if p is still a subsequence.
 *
 * Return the maximum k you can choose such that p is still a subsequence of s after the removals.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcacb", p = "ab", removable = [3,1,0]
 * Output: 2
 * Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
 * "ab" is a subsequence of "accb".
 * If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
 * Hence, the maximum k is 2.
 * Example 2:
 *
 * Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
 * Output: 1
 * Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
 * "abcd" is a subsequence of "abcddddd".
 * Example 3:
 *
 * Input: s = "abcab", p = "abc", removable = [0,1,2,3,4]
 * Output: 0
 * Explanation: If you remove the first index in the array removable, "abc" is no longer a subsequence.
 *
 * "qlevcvgzfpryiqlwy"
 * "qlecfqlw"
 * [12,5]
 */
public class MaximumNumber {
    public static void main(String[] args) {
        MaximumNumber maximumNumber = new MaximumNumber();
        int res = maximumNumber.maximumRemovals("qlevcvgzfpryiqlwy","qlecfqlw",new int[]{12,5});
        System.out.println(res);
    }

    public int maximumRemovals(String s, String p, int[] removable) {
        int i = 0;
        int j = removable.length-1;
        while(i <= j){
            int m = i + (j-i)/2;
            StringBuffer sb = new StringBuffer(s);
            for(int left = 0; left <= m; left++){
                sb.setCharAt(removable[left], ' ');// 将对应下标处的元素变为空（删除下标处元素）
            }
            if(!isSubsequence(sb.toString(), p)) {
                j = m-1;
            }  else{
                i = m+1;
            }
        }
        return j+1;
    }

    public boolean isSubsequence(String s, String p) {// 判断p是否是s的子串
        int n = p.length(), m = s.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (p.charAt(i) == s.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }

}
