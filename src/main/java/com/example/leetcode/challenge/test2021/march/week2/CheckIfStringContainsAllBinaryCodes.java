package com.example.leetcode.challenge.test2021.march.week2;


import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary string s and an integer k.
 *
 * Return True if every binary code of length k is a substring of s. Otherwise, return False.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
 * Example 2:
 *
 * Input: s = "00110", k = 2
 * Output: true
 * Example 3:
 *
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
 * Example 4:
 *
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
 * Example 5:
 *
 * Input: s = "0000000001011100", k = 4
 * Output: false
 */
public class CheckIfStringContainsAllBinaryCodes {
    public static void main(String[] args) {
        CheckIfStringContainsAllBinaryCodes checkIfStringContainsAllBinaryCodes = new CheckIfStringContainsAllBinaryCodes();
        checkIfStringContainsAllBinaryCodes.hasAllCodes("00110", 2);
    }


    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i,i+k));
        }
        return set.size() ==  Math.pow(2,k);
    }


    /**
     * faster solution
     * @param s
     * @param k
     * @return
     */
    public boolean hasAllCodesV0(String s, int k) {

        int need = 1 << k;
        boolean[] got = new boolean[need];
        int allOne = need - 1;
        int hashVal = 0;

        for (int i = 0; i < s.length(); i++) {
            hashVal = ((hashVal << 1) & allOne) | (s.charAt(i) - '0');

            if (i >= k - 1 && !got[hashVal]) {
                got[hashVal] = true;
                need--;
                if (need == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
