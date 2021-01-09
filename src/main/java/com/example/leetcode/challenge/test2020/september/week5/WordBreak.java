package com.example.leetcode.challenge.test2020.september.week5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4257740.html
     * @param s
     * @param wordDict
     * @return
     */
    int[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new int[s.length()];
        Arrays.fill(memo,-1);
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean res = check(s, wordSet, 0);
        return res;
    }

    boolean check(String s, Set<String> wordSet, int start) {
        if (start >= s.length())
            return true;
        if (memo[start] != -1)
            return memo[start] == 1;
        for (int i = start + 1; i <= s.length(); ++i) {
            if (wordSet.contains(s.substring(start, i)) && check(s, wordSet, i)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = 0;
        return false;
    }

    /**
     * 这道题其实还是一道经典的 DP 题目，也就是动态规划 Dynamic Programming。
     * 博主曾经说玩子数组或者子字符串且求极值的题，基本就是 DP 没差了，虽然这道题没有求极值，但是玩子字符串也符合 DP 的状态转移的特点。
     * 把一个人的温暖转移到另一个人的胸膛... 咳咳，跑错片场了，那是爱情转移～ 强行拉回，
     * DP 解法的两大难点，定义 dp 数组跟找出状态转移方程，
     * 先来看 dp 数组的定义，这里我们就用一个一维的 dp 数组，其中 dp[i] 表示范围 [0, i) 内的子串是否可以拆分，
     * 注意这里 dp 数组的长度比s串的长度大1，是因为我们要 handle 空串的情况，我们初始化 dp[0] 为 true，然后开始遍历。
     * 注意这里我们需要两个 for 循环来遍历，因为此时已经没有递归函数了，所以我们必须要遍历所有的子串，
     * 我们用j把 [0, i) 范围内的子串分为了两部分，[0, j) 和 [j, i)，
     * 其中范围 [0, j) 就是 dp[j]，范围 [j, i) 就是 s.substr(j, i-j)，
     * 其中 dp[j] 是之前的状态，我们已经算出来了，可以直接取，只需要在字典中查找 s.substr(j, i-j) 是否存在了，
     * 如果二者均为 true，将 dp[i] 赋为 true，并且 break 掉，此时就不需要再用j去分 [0, i) 范围了，
     * 因为 [0, i) 范围已经可以拆分了。最终我们返回 dp 数组的最后一个值，就是整个数组是否可以拆分的布尔值了，代码如下：
     * @param s
     * @param wordDict
     * @return
     */

    boolean wordBreakV2(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /**
     * faster solution
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakV3(String s, List<String> wordDict) {
        memo = new int[s.length()+1];
        memo[0] = 2;
        return helper(s, wordDict) == 2;
        // O(S*S) - time, O(S) - space
    }

    public int helper(String s, List<String> wordDict) {
        /*
            w(j) = (exists word in wordDic such that s(j) ends with it) && w(j-<word length>), j=0..S-1
        */
        if (s.length() == 0)
            return 2;

        if (memo[s.length()] == 0) {
            memo[s.length()] = 1;
            for(String w : wordDict) {
                if (s.endsWith(w) && helper(s.substring(0, s.length() - w.length()), wordDict) == 2) {
                    memo[s.length()] = 2;
                    break;
                }
            }
        }
        return memo[s.length()];
    }
}
