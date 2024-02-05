package com.example.leetcode.weeklycontest.old.test333;

/**
 * We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
 *
 * lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
 * Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp. If there is no such string, return an empty string.
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "aabd" is lexicographically smaller than "aaca" because the first position they differ is at the third letter, and 'b' comes before 'c'.
 *
 *
 *
 * Example 1:
 *
 * Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
 * Output: "abab"
 * Explanation: lcp corresponds to any 4 letter string with two alternating letters. The lexicographically smallest of them is "abab".
 * Example 2:
 *
 * Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
 * Output: "aaaa"
 * Explanation: lcp corresponds to any 4 letter string with a single distinct letter. The lexicographically smallest of them is "aaaa".
 * Example 3:
 *
 * Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
 * Output: ""
 * Explanation: lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of only a single letter; Thus, no answer exists.
 *
 */
public class FindStringWithLCP {
    public static void main(String[] args) {

    }

    /**
     *

     作者：endlesscheng
     链接：https://leetcode.cn/problems/find-the-string-with-lcp/solution/tan-xin-gou-zao-yan-zheng-o1e-wai-kong-j-82ik/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param lcp
     * @return
     */
    public String findTheString(int[][] lcp) {
        int i = 0, n = lcp.length;
        var s = new char[n];
        for (char c = 'a'; c <= 'z'; ++c) {
            while (i < n && s[i] > 0) ++i;
            if (i == n) break; // 构造完毕
            for (int j = i; j < n; ++j)
                if (lcp[i][j] > 0) s[j] = c;
        }
        while (i < n) if (s[i++] == 0) return ""; // 没有构造完

        // 直接在原数组上验证
        for (i = n - 1; i >= 0; --i)
            for (int j = n - 1; j >= 0; --j) {
                int actualLCP = s[i] != s[j] ? 0 : i == n - 1 || j == n - 1 ? 1 : lcp[i + 1][j + 1] + 1;
                if (lcp[i][j] != actualLCP) return "";
            }
        return new String(s);
    }
}
