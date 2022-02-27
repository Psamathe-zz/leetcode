package com.example.leetcode.challenge.test2022.february;

/**
 * Given two version numbers, version1 and version2, compare them.
 *
 * Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
 *
 * To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 *
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 *
 *
 * Example 1:
 *
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 * Example 2:
 *
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: version1 does not specify revision 2, which means it is treated as "0".
 * Example 3:
 *
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 */
public class CompareVersionNumbers {
    public static void main(String[] args) {
        CompareVersionNumbers compareVersionNumbers = new CompareVersionNumbers();
        int v = compareVersionNumbers.compareVersion("1.01", "1.001");
        System.out.println(v);
    }

    public int compareVersion(String version1, String version2) {
        String[] strings1 = version1.split("\\.");
        String[] strings2 = version2.split("\\.");
        int length1 = strings1.length;
        int length2 = strings2.length;
        int v1;
        int v2;
        for (int i = 0; i < Math.max(length1, length2); i++) {
            if(i >= length1 )
                v1 = 0;
            else
                v1 = helper(strings1[i]);
            if(i >= length2 )
                v2 = 0;
            else
                v2 = helper(strings2[i]);
            int v =  v1 - v2;
            if(v != 0)
                return v > 0? 1:-1;
        }
        return 0;
    }

    public int helper(String str1) {
        str1 = trim(str1);
        int length1 = str1.length();
        int val = 0;
        for (int i = 0; i < length1; i++) {
            val = val * 10 + (str1.charAt(i) - '0');
        }
        return val;
    }

    public String trim(String s) {
        int length = s.length();
        int i = 0;
        for (; i < length; i++) {
            if(s.charAt(i) != '0')
                break;
        }
        return s.substring(i);
    }
}
