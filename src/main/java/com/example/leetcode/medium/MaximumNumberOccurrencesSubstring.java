package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * Example 3:
 *
 * Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * Output: 3
 * Example 4:
 *
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s only contains lowercase English letters.
 *
 *
 * 原因是，如果一个大于 minSize 长度的字串若是满足条件，那么该子串其中必定有至少一个长度为 minSize 的字串满足条件。
 * 因此一个大于 minSize 长度的字串出现了 n 次，那么该子串其中必定有一个长度为 minSize 的子串出现了 n 次。
 * https://lucifer.ren/blog/2020/01/10/1297.maximum-number-of-occurrences-of-a-substring/
 */
public class MaximumNumberOccurrencesSubstring {

    public static void main(String[] args) {
        String s = "aabcabcab";
        int maxLetters = 2;
        int minSize = 2;
        int maxSize = 3;
        MaximumNumberOccurrencesSubstring maximumNumberOccurrencesSubstring = new MaximumNumberOccurrencesSubstring();
        int result = maximumNumberOccurrencesSubstring.maxFreq(s,maxLetters,minSize,maxSize);
        System.out.println(result);
    }

    /**
     * 如果 n > 0，则模式将被最多应用 n - 1 次，数组的长度将不会大于 n，而且数组的最后一项将包含所有超出最后匹配的定界符的输入。
     *
     * 如果 n < 0，那么模式将被应用尽可能多的次数，而且数组可以是任何长度。
     *
     * 如果 n = 0，那么模式将被应用尽可能多的次数，数组可以是任何长度，并且结尾空字符串将被丢弃。
     * ————————————————
     * 版权声明：本文为CSDN博主「攻城狮Kevin」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/wx1528159409/java/article/details/92796234
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> counter = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length() - minSize + 1; i++) {
            String substr = s.substring(i, i + minSize);
            if (maxLetters(substr)<=maxLetters) {
                int newVal = counter.getOrDefault(substr, 0) + 1;
                counter.put(substr, newVal);
                res = Math.max(res, newVal);
            }
        }
        return res;
    }

    public int maxLetters(String input){
        return Arrays.stream(input.split("")).distinct().collect(Collectors.toList()).size();
    }


}
