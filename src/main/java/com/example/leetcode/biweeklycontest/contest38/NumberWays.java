package com.example.leetcode.biweeklycontest.contest38;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a list of strings of the same length words and a string target.
 *
 * Your task is to form target using the given words under the following rules:
 *
 * target should be formed from left to right.
 * To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
 * Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
 * Repeat the process until you form the string target.
 * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
 *
 * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["acca","bbbb","caca"], target = "aba"
 * Output: 6
 * Explanation: There are 6 ways to form target.
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
 * Example 2:
 *
 * Input: words = ["abba","baab"], target = "bab"
 * Output: 4
 * Explanation: There are 4 ways to form target.
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
 * "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
 * "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 * Example 3:
 *
 * Input: words = ["abcd"], target = "abcd"
 * Output: 1
 * Example 4:
 *
 * Input: words = ["abab","baba","abba","baab"], target = "abba"
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * All strings in words have the same length.
 * 1 <= target.length <= 1000
 * words[i] and target contain only lowercase English letters.
 */
public class NumberWays {
    public static void main(String[] args) {

    }

    public int numWays(String[] words, String target) {
        int mod = 1000000007;
        List<Integer>[] map = new List[26];
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            List<Integer> list = map[ch - 'a'];
            if (list == null) {
                list = new ArrayList<>(target.length());
            }
            map[ch - 'a'] = list;
            list.add(i);
        }
        int[][] cntArr = new int[words[0].length()][26];
        for (String word : words) {
            for (int i = 0; i < words[0].length(); i++) {
                cntArr[i][word.charAt(i) - 'a']++;
            }
        }

        long[] dp = new long[target.length()];
        for (int i = 0; i < words[0].length(); i++) {
            long[] ndp = new long[target.length()];
            for (int j = 0; j < 26; j++) {
                int cnt = cntArr[i][j];
                List<Integer> list = map[j];
                if (list == null) continue;
                for (Integer k : list) {
                    if (k == 0) ndp[k] += cnt;
                    else ndp[k] += cnt * dp[k - 1];
                }
            }
            for (int j = 0; j < ndp.length; j++) {
                ndp[j] += dp[j];
                if (ndp[j] >= mod) {
                    ndp[j] %= mod;
                }
            }
            dp = ndp;
        }

        return (int) dp[target.length() - 1];

    }

    final int MOD = 1000000007;

    public int numWaysV1(String[] words, String target) {
        int n = words.length;
        int m = words[0].length();
        int t = target.length();
        long[][] dp = new long[t+1][m+1];
        for (int i = 0; i <= m; i++) {
            dp[t][i] = 1;
        }
        int[][] counts = new int[26][m];
        for (int i = 0; i < n; i++) {
            for (int e = 0; e < m; e++) {
                counts[words[i].charAt(e)-'a'][e]++;
            }
        }
        for (int i = t-1; i >= 0; i--) {
            int c = target.charAt(i)-'a';
            for (int x = m-1; x >= 0; x--) {
                dp[i][x] = dp[i][x+1];
                dp[i][x] += dp[i+1][x+1] * counts[c][x];
                dp[i][x] %= MOD;
            }
        }
        return (int)dp[0][0];
    }
}
