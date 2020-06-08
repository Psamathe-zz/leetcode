package com.example.leetcode.medium;

/**
 * Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
 * Example 2:
 *
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
 * Example 3:
 *
 * Input: text = "aaabbaaa"
 * Output: 4
 * Example 4:
 *
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
 * Example 5:
 *
 * Input: text = "abcdef"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 20000
 * text consist of lowercase English characters only.
 * "bbababaaaa"
 */
public class SwapForLongestRepeatedCharacterSubstring {
    public static void main(String[] args) {
        String text = "bbababaaaa";
        SwapForLongestRepeatedCharacterSubstring swapForLongestRepeatedCharacterSubstring = new SwapForLongestRepeatedCharacterSubstring();
        int result = swapForLongestRepeatedCharacterSubstring.maxRepOpt1(text);
        System.out.println(result);
    }

    public int maxRepOpt1(String text) {
        int result = Integer.MIN_VALUE;
        char[] chars = text.toCharArray();
        int rest;
        int cal;
        char base;
        int indexj;
        for (int i = 0; i < text.length(); i++) {
            rest = 1;
            base = chars[i];
            cal = 1;
            for (indexj = i + 1; indexj < text.length(); indexj++) {
                if(chars[indexj]!= base && rest == 0)
                    break;
                if(rest >= 0){
                    cal++;
                    if(chars[indexj]!= base)
                        rest--;
                }
            }
            if(text.substring(0,i).indexOf(base) != -1 || text.substring(indexj).indexOf(base) != -1) {
                rest = rest==1?1:0;
                if( result < cal + rest)
                    result = cal + rest;
            } else {
                if( result < cal - 1 + rest)
                    result = cal - 1 + rest;
            }
        }

        return result;
    }


    /**
     * faster solution
     * @param text
     * @return
     */
    public int maxRepOpt1V1(String text) {
        int[] map = new int[26];
        for (char c : text.toCharArray())
            map[c - 'a']++;
        int max = 1, i = 0, n = text.length();
        while (i < n) {
            int j = i;
            char c = text.charAt(i);
            while (j < n && text.charAt(j) == c)
                j++;
            int k = j + 1;
            while (k < n && text.charAt(k) == c)
                k++;
            int val =(k - i - 1 == map[c - 'a']) ? k - i - 1 : k - i;
            max = Math.max(max,val);
            i = j;
        }
        return max;
    }
}
