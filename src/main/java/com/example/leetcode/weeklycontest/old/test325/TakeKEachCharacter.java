package com.example.leetcode.weeklycontest.old.test325;

/**
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 *
 * Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabaaaacaabc", k = 2
 * Output: 8
 * Explanation:
 * Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
 * Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
 * A total of 3 + 5 = 8 minutes is needed.
 * It can be proven that 8 is the minimum number of minutes needed.
 * Example 2:
 *
 * Input: s = "a", k = 1
 * Output: -1
 * Explanation: It is not possible to take one 'b' or 'c' so return -1.
 */
public class TakeKEachCharacter {
    public static void main(String[] args) {
        TakeKEachCharacter takeKEachCharacter = new TakeKEachCharacter();
        takeKEachCharacter.takeCharacters("aabaaaacaabc", 2);
    }


    public int takeCharacters(String s, int k) {
        int left = 0, right = 0, length = s.length();
        char[] arr = s.toCharArray();
        int max = 0;
        int[] cnt = new int[3];//统计a,b,c的数量
        for (int i = 0; i < length; ++i) {
            cnt[arr[i] - 'a']++;
        }
        int cntA = cnt[0] - k, cntB = cnt[1] - k, cntC = cnt[2] - k;//分别为a,b,c可以剩下的最大数量
        if (cntA == 0 && cntB == 0 && cntC == 0)//此时要全部取走
            return length;
        if (cntA < 0 || cntB < 0 || cntC < 0)//剩下的数量为负的时候,说明a,b,c的数量不足k个
            return -1;
        cnt = new int[3];//每次循环统计剩下的a,b,c的数量
        while (right < length) {
            cnt[arr[right] - 'a']++;
            while (cnt[0] > cntA || cnt[1] > cntB || cnt[2] > cntC) {
                cnt[arr[left] - 'a']--;
                left++;//当剩下的字符串过长而不满足条件的时候,滑动窗口左端向右移
            }
            max = Math.max(max, right - left+1);
            right++;//窗口的左端向右移


        }

        return length - max;
    }
}
