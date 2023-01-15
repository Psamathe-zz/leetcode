package com.example.leetcode.weeklycontest.test327;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given two 0-indexed strings word1 and word2.
 *
 * A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 *
 * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "ac", word2 = "b"
 * Output: false
 * Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
 * Example 2:
 *
 * Input: word1 = "abcc", word2 = "aab"
 * Output: true
 * Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
 * Example 3:
 *
 * Input: word1 = "abcde", word2 = "fghij"
 * Output: true
 * Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
 */
public class MakeNumberDistinct {
    public static void main(String[] args) {

    }

    private final int[] count1 = new int[26];
    private final int[] count2 = new int[26];
    private int num1 = 0;
    private int num2 = 0;

    public boolean isItPossible(String word1, String word2) {
        initialize(word1, word2);
        return num1 > num2 ? check(count1, count2) : check(count2, count1);
    }

    /**
     * https://leetcode.cn/problems/make-number-of-distinct-characters-equal/solution/by-danicat-bnjs/
     * @param countA
     * @param countB
     * @return
     */
    private boolean check(int[] countA, int[] countB) {
        boolean isI = false;
        int[] iA = new int[]{0, 0};
        int[] iB = new int[]{0, 0};
        int[] dA = new int[]{0, 0};
        int[] dB = new int[]{0, 0};
        // 统计
        for (int k = 0; k < 26; ++k) {
            if (0 == countA[k] && 0 == countB[k]) continue;
            if (0 < countA[k] && 0 < countB[k]) {
                isI = true;
                iA[1 < countA[k] ? 1 : 0] += 1;
                iB[1 < countB[k] ? 1 : 0] += 1;
            } else if (0 < countA[k]) {
                dA[1 < countA[k] ? 1 : 0] += 1;
            } else {
                dB[1 < countB[k] ? 1 : 0] += 1;
            }
        }
        // 判断
        return switch (Math.abs(num1 - num2)) {
            case 0 -> isI || (0 < dA[0] && 0 < dB[0]) || (0 < dA[1] && 0 < dB[1]);
            case 1 -> (0 < dA[0] && 0 < dB[1]) || (0 < dA[1] && 0 < iB[1]) ||
                    (0 < dA[0] && 0 < iB[0]) || (0 < iA[0] && 0 < iB[1] && (2 < (iA[0] + iB[1])));
            case 2 -> 0 < dA[0] && 0 < iB[1];
            default -> false;
        };
    }

    private void initialize(String word1, String word2) {
        Set<Integer> set = new HashSet<>(26);
        for (int i = 0; i < word1.length(); ++i) {
            int index = word1.charAt(i) - 'a';
            ++count1[index];
            set.add(index);
        }
        num1 = set.size();
        set.clear();
        for (int j = 0; j < word2.length(); ++j) {
            int index = word2.charAt(j) - 'a';
            ++count2[index];
            set.add(index);
        }
        num2 = set.size();
    }

}
