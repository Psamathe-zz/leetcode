package com.example.leetcode.weeklycontest.test285;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k and a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.
 *
 * The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].
 *
 * Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of only one repeating character after the ith query is performed.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
 * Output: [3,3,4]
 * Explanation:
 * - 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
 * - 2nd query updates s = "bbbccc".
 *   The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
 * - 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
 * Thus, we return [3,3,4].
 * Example 2:
 *
 * Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
 * Output: [2,3]
 * Explanation:
 * - 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
 * - 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
 * Thus, we return [2,3].
 *
 */
public class LongestSubstringOfOneRepeatingCharacter {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/longest-substring-of-one-repeating-character/solution/cai-ji-ye-yao-xie-ti-jie-by-socrates1232-5kmz/
     * @param s
     * @param queryCharacters
     * @param queryIndices
     * @return
     */
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length(), qn = queryIndices.length;
        int[] ans = new int[qn];
        char[] str = s.toCharArray();
        // 用于表示区间，值为区间开头索引
        TreeSet<Integer> sep = new TreeSet<>();
        // 用于表示区间大小的集合，key为长度，value为长度的数量。
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n;) {
            int cur = i;
            sep.add(i);
            i++;
            while (i < n && str[i] == str[cur]) i++;
            // 累积长度，merge用法请自行查阅
            map.merge(i - cur, 1, Integer::sum);
        }
        for (int i = 0; i < qn; i++) {
            int t = queryIndices[i];
            char c = queryCharacters.charAt(i);
            if (c != str[t] && n != 1) {
                if (t > 0) {
                    // 二分的搜索当前t在那个区间，一个是搜索上界一个是下界，刚好是t所处区间
                    Integer h = sep.higher(t), l = sep.lower(t);
                    // 由于存的是区间的头，可能出现找不到上界的情况，所以手动添加一个上界
                    if (h == null) {
                        h = n;
                    }
                    // 分情况讨论，如果和前一位相等，说明要把原来的区间拆成两个区间
                    if (str[t] == str[t - 1]) {
                        // 从区间长度集合中删除一个该长度的区间
                        map.merge(h - l, -1, (a, b) -> a == 1 ? null : a + b);
                        // 将右侧新区间加入map
                        map.merge(h - t, 1, Integer::sum);
                        // 将左侧新区间加入map
                        map.merge(t - l, 1, Integer::sum);
                        // 新区间的头加入sep中
                        sep.add(t);
                    } else if (str[t - 1] == c) {
                        // 如果要改得字母和前一位相同，则相当于延长原来的区间
                        map.merge(h - l, 1, Integer::sum);
                        map.merge(h - t, -1, (a, b) -> a == 1 ? null : a + b);
                        map.merge(t - l, -1, (a, b) -> a == 1 ? null : a + b);
                        sep.remove(t);
                    }
                }
                // 再来修改左侧的区间
                if (t < n - 1) {
                    Integer h = sep.higher(t + 1), l = sep.lower(t + 1);
                    if (h == null) {
                        h = n;
                    }
                    if (str[t] == str[t + 1]) {
                        map.merge(h - l, -1, (a, b) -> a == 1 ? null : a + b);
                        map.merge(h - t - 1, 1, Integer::sum);
                        map.merge(t - l + 1, 1, Integer::sum);
                        sep.add(t + 1);
                    } else if (str[t + 1] == c) {
                        map.merge(h - l, 1, Integer::sum);
                        map.merge(h - t - 1, -1, (a, b) -> a == 1 ? null : a + b);
                        map.merge(t - l + 1, -1, (a, b) -> a == 1 ? null : a + b);
                        sep.remove(t + 1);
                    }
                }
            }
            str[t] = c;
            ans[i] = map.lastKey();
        }
        return ans;
    }
}
