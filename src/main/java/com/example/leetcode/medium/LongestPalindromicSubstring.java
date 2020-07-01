package com.example.leetcode.medium;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babadc"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * babadc
 * cdababcdabab
 * babadc
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babadc";
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String result = longestPalindromicSubstring.longestPalindromeV3(s);
        System.out.println(result);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4464476.html
     * @param s
     * @return
     */
    int start;
    int maxLen;
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        int n = s.length();
        maxLen = 0;
        start = 0;
        for (int i = 0; i < n - 1; ++i) {
            searchPalindrome(s, i, i);
            searchPalindrome(s, i, i + 1);
        }
        return s.substring(start, start + maxLen);
    }
    void searchPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        if (maxLen < right - left - 1) {
            start = left + 1;
            maxLen = right - left - 1;
        }
    }


    public String longestPalindromeV2(String s) {
        if (s.length() < 2)
            return s;
        int n = s.length(), maxLen = 0, start = 0;
        for (int i = 0; i < n;) {
            if (n - i <= maxLen / 2) break;
            int left = i, right = i;
            while (right < n - 1 && s.charAt(right + 1) == s.charAt(right))
                ++right;
            i = right + 1;
            while (right < n - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                ++right; --left;
            }
            if (maxLen < right - left + 1) {
                maxLen = right - left + 1;
                start = left;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * DP
     * 此题还可以用动态规划 Dynamic Programming 来解，根 Palindrome Partitioning II 的解法很类似，我们维护一个二维数组 dp，
     * 其中 dp[i][j] 表示字符串区间 [i, j] 是否为回文串，当 i = j 时，只有一个字符，肯定是回文串，
     * 如果 i = j + 1，说明是相邻字符，此时需要判断 s[i] 是否等于 s[j]，
     * 如果i和j不相邻，即 i - j >= 2 时，除了判断 s[i] 和 s[j] 相等之外，dp[i + 1][j - 1] 若为真，就是回文串，通过以上分析，可以写出递推式如下：
     *
     * dp[i, j] = 1                                               if i == j
     *
     *            = s[i] == s[j]                                if j = i + 1
     *
     *            = s[i] == s[j] && dp[i + 1][j - 1]    if j > i + 1
     *
     * 这里有个有趣的现象就是如果我把下面的代码中的二维数组由 int 改为 vector<vector<int>> 后，就会超时，
     * 这说明 int 型的二维数组访问执行速度完爆 std 的 vector 啊，所以以后尽可能的还是用最原始的数据类型吧。
     * @param s
     * @return
     */
    public String longestPalindromeV3(String s) {
        if (s.isEmpty())
            return "";
        int n = s.length();
        int[][] dp = new int[n][n];
        int left = 0, len = 1;
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
            for (int j = 0; j < i; ++j) {
                dp[j][i] = (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1] == 1)) ? 1 : 0;
                if (dp[j][i] == 1 && len < i - j + 1) {
                    len = i - j + 1;
                    left = j;
                }
            }
        }
        return s.substring(left, left + len);
    }

    /**
     * 最后要来的就是大名鼎鼎的马拉车算法 Manacher's Algorithm，这个算法的神奇之处在于将时间复杂度提升到了 O(n) 这种逆天的地步，而算法本身也设计的很巧妙，很值得我们掌握，
     * 参见我另一篇专门介绍马拉车算法的博客 Manacher's Algorithm 马拉车算法，代码实现如下：
     * https://www.cnblogs.com/grandyang/p/4475985.html
     * @param s
     * @return
     */
    public String longestPalindromeV4(String s) {
        return "";
    }


    public String longestPalindromeOld(String s) {
        if(s.length() == 0)
            return "";
        String s1;
        String s2;
        String result = s.substring(0,1);
        String st = new StringBuilder(s).reverse().toString();
        int length = 1;
        for(int i = 0; i < s.length() - length;i++){
            for (int j = s.length(); j > i + length - 1 ; j--) {
                 s1 = s.substring(i,j);
                 s2 = st.substring(s.length() - j,s.length()-i);
                if(s1.equals(s2) && s1.length()>result.length()) {
                    result = s1;
                    length = result.length();
                    break;
                }
            }

        }
        return result;
    }
}
