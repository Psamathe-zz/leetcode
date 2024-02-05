package com.example.leetcode.weeklycontest.old.test242;


import java.util.Arrays;

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 * Example 2:
 *
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 */
public class JumpGameVII {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/jump-game-vii/solution/shuang-bai-java-qian-zhui-he-dp-by-tianb-7wud/
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[len + 1];
        Arrays.fill(dp,1);
        dp[1] = 0;
        int[] bdp = new int[len + 1];
        bdp[1] = 0;
        for (int i = 2; i < len + 1 ; i++) {
            if (chars[i - 1] == '0') {
                if (i - minJump >= 1) {
                    int r = i - minJump;
                    int l = Math.max(i - maxJump, 1);
                    dp[i] = bdp[r] - bdp[l - 1] < r - l + 1 ? 0 : 1;
                }
            }
            bdp[i] += bdp[i - 1] + dp[i];
        }
        return dp[len] == 0;
    }

    /**
     * https://leetcode-cn.com/problems/jump-game-vii/solution/dong-tai-gui-hua-by-alan-xor-j7ik/
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean canReachV1(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];//状态dp记录所有能到达且为0的位置
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (dp[i] == false) continue;

            for (int j = i + minJump; j <= i + maxJump && j < n && dp[j] == false; j++) {//dp[j] == false剪枝
                dp[j] = s.charAt(j) == '0';
            }
        }
        return dp[n - 1];
    }

}
