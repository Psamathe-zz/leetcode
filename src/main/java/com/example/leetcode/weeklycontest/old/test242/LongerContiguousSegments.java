package com.example.leetcode.weeklycontest.old.test242;

/**
 * Given a binary string s, return true if the longest contiguous segment of 1s is strictly longer than the longest contiguous segment of 0s in s. Return false otherwise.
 *
 * For example, in s = "110100010" the longest contiguous segment of 1s has length 2, and the longest contiguous segment of 0s has length 3.
 * Note that if there are no 0s, then the longest contiguous segment of 0s is considered to have length 0. The same applies if there are no 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1101"
 * Output: true
 * Explanation:
 * The longest contiguous segment of 1s has length 2: "1101"
 * The longest contiguous segment of 0s has length 1: "1101"
 * The segment of 1s is longer, so return true.
 * Example 2:
 *
 * Input: s = "111000"
 * Output: false
 * Explanation:
 * The longest contiguous segment of 1s has length 3: "111000"
 * The longest contiguous segment of 0s has length 3: "111000"
 * The segment of 1s is not longer, so return false.
 * Example 3:
 *
 * Input: s = "110100010"
 * Output: false
 * Explanation:
 * The longest contiguous segment of 1s has length 2: "110100010"
 * The longest contiguous segment of 0s has length 3: "110100010"
 * The segment of 1s is not longer, so return false.
 */
public class LongerContiguousSegments {
    public static void main(String[] args) {
        LongerContiguousSegments longerContiguousSegments = new LongerContiguousSegments();
        boolean res = longerContiguousSegments.checkZeroOnes("1101");
        System.out.println(res);
    }

    public boolean checkZeroOnes(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int maxZero = 0;
        int maxOne = 0;
        int curZero = 0;
        int curOne = 0;
        for (int i = 0; i < length; i++) {
            if(chars[i] == '0'){
                curZero++;
                if(curOne != 0){
                    maxOne = Math.max(maxOne, curOne);
                    curOne = 0;
                }
            } else {
                curOne++;
                if(curZero != 0){
                    maxZero = Math.max(maxZero, curZero);
                    curZero = 0;
                }
            }
        }
        maxZero = Math.max(maxZero, curZero);
        maxOne = Math.max(maxOne, curOne);

        return maxZero < maxOne;
    }
}
