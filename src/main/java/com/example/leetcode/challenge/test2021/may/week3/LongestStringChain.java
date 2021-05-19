package com.example.leetcode.challenge.test2021.may.week3;


import java.util.*;

/**
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2. For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chain is "a","ba","bda","bdca".
 * Example 2:
 *
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 */
public class LongestStringChain {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/longest-string-chain/solution/leetcodebi-ji-java-py-si-ke-yi-dao-ti-1048-zui-cha/
     */
    int res = 0;

    public int longestStrChain(String[] words) {
        int min = 0, max = 16;//最小字符长度，最大字符长度
        //K为字符长度，Set为该字符长度的word集合
        Map<Integer, Set<String>> map = new HashMap<>();
        for (String word : words) {
            map.putIfAbsent(word.length(), new HashSet<>());
            map.get(word.length()).add(word);
            min = Math.min(min, word.length());
            max = Math.max(max, word.length());
        }
        for (int len = min; len <= max; len++) {
            Set<String> curSet = map.get(len);
            if (curSet == null) continue;//当set没有值时，无需遍历
            if ((max + 1 - len <= res)) continue;//最大长度+1-当前的长度<=res，res更加符合题意
            for (String cur : curSet) {
                findNext(map, len, cur);
            }
        }
        return res;
    }

    /**
     * @param map
     * @param len 当前字符的长度
     * @param levelStr 当前字符
     */
    private void findNext(Map<Integer, Set<String>> map, int len, String levelStr) {
        res = Math.max(res, levelStr.length() + 1 - len);//记录结果集
        Set<String> curSet = map.get(levelStr.length() + 1);//
        if (curSet == null) return;//退出条件
        Iterator<String> it = curSet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (isPredecessor(levelStr, next)) {
                findNext(map, len, next);
                it.remove();
            }
        }
    }

    /**
     * 判断a是否是b的前身 是返回true 如 "bda" 是"bdca"的前身
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isPredecessor(String a, String b) {
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        if ((m + 1) != n) return false;
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) i++;
            j++;
        }
        return i == m;
    }
}
