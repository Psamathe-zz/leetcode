package com.example.leetcode.biweeklycontest.contest55;


/**
 * Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
 *
 * Find the leftmost occurrence of the substring part and remove it from s.
 * Return s after removing all occurrences of part.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "daabcbaabcbc", part = "abc"
 * Output: "dab"
 * Explanation: The following operations are done:
 * - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
 * - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
 * - s = "dababc", remove "abc" starting at index 3, so s = "dab".
 * Now s has no occurrences of "abc".
 * Example 2:
 *
 * Input: s = "axxxxyyyyb", part = "xy"
 * Output: "ab"
 * Explanation: The following operations are done:
 * - s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
 * - s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
 * - s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
 * - s = "axyb", remove "xy" starting at index 1 so s = "ab".
 * Now s has no occurrences of "xy".
 */
public class RemoveAllOccurrences {
    public static void main(String[] args) {
        RemoveAllOccurrences removeAllOccurrences = new RemoveAllOccurrences();
        String s = removeAllOccurrences.removeOccurrences("axxxxyyyyb","xy");
        System.out.println(s);
    }

    public String removeOccurrences(String s, String part) {
        int lengthS = s.length();
        int lengthP = part.length();
        boolean hasNext;
        StringBuilder stringBuilder;
        do {
            stringBuilder = new StringBuilder();
            hasNext = false;
            lengthS = s.length();
            for (int i = 0; i < lengthS; ) {
                if (i + lengthP <= lengthS && s.substring(i, i + lengthP).equals(part)) {
                    i += lengthP;
                    hasNext = true;
                } else {
                    stringBuilder.append(s.charAt(i));
                    i++;
                }
            }
            s = stringBuilder.toString();
        } while (hasNext);
        return s;
    }
}
