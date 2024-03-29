package com.example.leetcode.weeklycontest.old.test227;


/**
 * You are given two strings word1 and word2. You want to construct a string merge in the following way: while either word1 or word2 are non-empty, choose one of the following options:
 *
 * If word1 is non-empty, append the first character in word1 to merge and delete it from word1.
 * For example, if word1 = "abc" and merge = "dv", then after choosing this operation, word1 = "bc" and merge = "dva".
 * If word2 is non-empty, append the first character in word2 to merge and delete it from word2.
 * For example, if word2 = "abc" and merge = "", then after choosing this operation, word2 = "bc" and merge = "a".
 * Return the lexicographically largest merge you can construct.
 *
 * A string a is lexicographically larger than a string b (of the same length) if in the first position where a and b differ, a has a character strictly larger than the corresponding character in b. For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at the fourth character, and d is greater than c.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "cabaa", word2 = "bcaaa"
 * Output: "cbcabaaaaa"
 * Explanation: One way to get the lexicographically largest merge is:
 * - Take from word1: merge = "c", word1 = "abaa", word2 = "bcaaa"
 * - Take from word2: merge = "cb", word1 = "abaa", word2 = "caaa"
 * - Take from word2: merge = "cbc", word1 = "abaa", word2 = "aaa"
 * - Take from word1: merge = "cbca", word1 = "baa", word2 = "aaa"
 * - Take from word1: merge = "cbcab", word1 = "aa", word2 = "aaa"
 * - Append the remaining 5 a's from word1 and word2 at the end of merge.
 * Example 2:
 *
 * Input: word1 = "abcabc", word2 = "abdcaba"
 * Output: "abdcabcabcaba"
 */
public class LargestMerge {
    public static void main(String[] args) {
        LargestMerge largestMerge = new LargestMerge();
        largestMerge.largestMerge("cabaa","bcaaa");
    }

    /**
     * https://leetcode-cn.com/problems/largest-merge-of-two-strings/solution/java-ban-bian-li-liang-ge-zi-fu-chuan-zh-3f6r/
     * @param word1
     * @param word2
     * @return
     */
    public String largestMerge(String word1, String word2) {
        char[] csa = word1.toCharArray(), csb = word2.toCharArray();
        int lena = csa.length, lenb = csb.length;
        char[] ans = new char[lena + lenb];
        int a = 0, b = 0, idx = 0;
        while(a < lena || b < lenb) {

            if(a >= lena) {
                ans[idx++] = csb[b++];
            } else if(b >= lenb) {
                ans[idx++] = csa[a++];
            } else if(csa[a] > csb[b]) {
                ans[idx++] = csa[a++];
            } else if(csa[a] < csb[b]) {
                ans[idx++] = csb[b++];
            } else {
                // 相等
                if(look(word1.substring(a), word2.substring(b))) {
                    // a 比 b 大
                    ans[idx++] = csa[a++];
                } else {
                    // b 的字典序更大
                    ans[idx++] = csb[b++];
                }
            }
        }

        return new String(ans);
    }

    public boolean look(String a, String b){
        return a.compareTo(b) > 0;
    }
}
