package com.example.leetcode.challenge.test2021.may.week4;


import java.util.Arrays;

/**
 * Given an array of strings words, return the smallest string that contains each string in words as a substring. If there are multiple valid strings of the smallest length, return any of them.
 *
 * You may assume that no string in words is a substring of another string in words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * Example 2:
 *
 * Input: words = ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 12
 * 1 <= words[i].length <= 20
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class FindShortestSuperstring {
    public static void main(String[] args) {
        FindShortestSuperstring findShortestSuperstring = new FindShortestSuperstring();
        findShortestSuperstring.shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"});
    }

    /**
     * https://www.cnblogs.com/grandyang/p/12850214.html
     * @param words
     * @return
     */
    public String shortestSuperstring(String[] words) {
        int length = words.length;
        String[][] dp = new String[1<<length][length];
        for (int i = 0; i < 1<<length; i++) {
            for (int j = 0; j < length; j++) {
                dp[i][j] = "";
            }
        }
        int[][] overlap = new int[length][length];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (i == j)
                    continue;
                for (int k = Math.min(words[i].length(), words[j].length()); k > 0; --k) {
                    if (words[i].substring(words[i].length() - k).equals(words[j].substring(0, k))) {
                        overlap[i][j] = k;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < length; ++i)
            dp[1 << i][i] += words[i];
        for (int mask = 1; mask < (1 << length); ++mask) {
            for (int j = 0; j < length; ++j) {
                if ((mask & (1 << j)) == 0) continue;
                for (int i = 0; i < length; ++i) {
                    if (i == j || (mask & (1 << i)) == 0) continue;
                    String t = dp[mask ^ (1 << j)][i] + words[j].substring(overlap[i][j]);
                    if (dp[mask][j].isEmpty() || t.length() < dp[mask][j].length()) {
                        dp[mask][j] = t;
                    }
                }
            }
        }
        int last = (1 << length) - 1;
        String res = dp[last][0];
        for (int i = 1; i < length; ++i) {
            if (dp[last][i].length() < res.length()) {
                res = dp[last][i];
            }
        }
        return res;
    }


    /**
     * faster solution
     * @param words
     * @return
     */
    public String shortestSuperstringV1(String[] words) {
        int n = words.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[i][j] = calculateCost(words[i], words[j]);
                graph[j][i] = calculateCost(words[j], words[i]);
            }
        }
        // i: 2^n status;
        // j: the last word index
        int[][] dp = new int[1<< n][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE/2);
        }
        int[][] parent = new int[1 << n][n];
        for (int[] arr : parent) {
            Arrays.fill(arr, -1);
        }

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = words[i].length();
        }

        for (int s = 1; s < (1 << n); s++)
            for (int i = 0; i < n; i++) {
                if ((s & (1 << i)) == 0) {
                    //表示这个状态不存在 要保证第i位已经访问过了 才可以把i作为结尾
                    continue;
                }
                int prev = s - (1 << i); //上一个状态就是把第i位滞空
                for (int j = 0; j < n; j++) {
                    if (dp[prev][j] + graph[j][i] < dp[s][i]) {
                        dp[s][i] = dp[prev][j] + graph[j][i];
                        parent[s][i] = j;
                    }
                }
            }
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[(1 << n) - 1][i] < min) {
                min = dp[(1 << n) - 1][i];
                minIndex = i;
            }
        }
        int curr = minIndex;
        int s = (1 << n) - 1;
        StringBuilder sb = new StringBuilder();
        while (s > 0 && curr >= 0) {
            int prev = parent[s][curr];
            if (prev < 0) {
                sb.insert(0, words[curr]);
            } else {
                sb.insert(0, words[curr].substring(words[curr].length() - graph[prev][curr]));
            }
            s &= ~(1 << curr); // curr位置为零
            curr = prev;
        }
        return sb.toString();
    }


    private int calculateCost(String a, String b) {
        int n1 = a.length();
        int n2 = b.length();
        int result = n2;
        for (int k = 1; k <= Math.min(n1, n2); k++) {
            if (a.substring(n1 - k).equals(b.substring(0, k))) {
                result = n2 - k;
            }
        }
        return result;
    }

}
