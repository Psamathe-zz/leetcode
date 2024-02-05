package com.example.leetcode.weeklycontest.old.test236;


/**
 * There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n. A frog starts at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.
 *
 * You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes an obstacle on the lane obstacles[i] at point i. If obstacles[i] == 0, there are no obstacles at point i. There will be at most one obstacle in the 3 lanes at each point.
 *
 * For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
 * The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane at point i + 1. To avoid obstacles, the frog can also perform a side jump to jump to another lane (even if they are not adjacent) at the same point if there is no obstacle on the new lane.
 *
 * For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
 * Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.
 *
 * Note: There will be no obstacles on points 0 and n.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: obstacles = [0,1,2,3,0]
 * Output: 2
 * Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps (red arrows).
 * Note that the frog can jump over obstacles only when making side jumps (as shown at point 2).
 * Example 2:
 *
 *
 * Input: obstacles = [0,1,1,3,3,0]
 * Output: 0
 * Explanation: There are no obstacles on lane 2. No side jumps are required.
 * Example 3:
 *
 *
 * Input: obstacles = [0,2,1,0,3,0]
 * Output: 2
 * Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps.
 *0,0,3,1,0,1,0,2,3,1,0
 *
 * Constraints:
 *
 * obstacles.length == n + 1
 * 1 <= n <= 5 * 105
 * 0 <= obstacles[i] <= 3
 * obstacles[0] == obstacles[n] == 0
 */
public class MinimumSidewayJumps {
    public static void main(String[] args) {
        int[] obstacles = new int[]{0,1,1,3,3,0};
        MinimumSidewayJumps minimumSidewayJumps = new MinimumSidewayJumps();
        int res = minimumSidewayJumps.minSideJumps(obstacles);
        System.out.println(res);
    }

    public int minSideJumps(int[] obstacles) {
        int MAX = 160000;
        int length = obstacles.length;
        int[][] dp = new int[length][3];
        dp[0][0] = 1;
        dp[0][2] = 1;
        int pos;
        for (int i = 1; i < length; i++) {
            pos = obstacles[i] - 1;
            if(pos == 0){
                dp[i][0] = MAX;
                dp[i][1] = Math.min(Math.min(dp[i-1][1],dp[i-1][2]+1),dp[i-1][0]+ (dp[i-1][1]>=MAX?2:1));
                dp[i][2] = Math.min(Math.min(dp[i-1][2],dp[i-1][1]+1),dp[i-1][0]+ (dp[i-1][2]>=MAX?2:1));
            } else if(pos == 1){
                dp[i][1] = MAX;
                dp[i][0] = Math.min(Math.min(dp[i-1][0],dp[i-1][2]+1),dp[i-1][1]+ (dp[i-1][0]>=MAX?2:1));
                dp[i][2] = Math.min(Math.min(dp[i-1][2],dp[i-1][0]+1),dp[i-1][1]+ (dp[i-1][2]>=MAX?2:1));
            } else if(pos == 2){
                dp[i][2] = MAX;
                dp[i][0] = Math.min(Math.min(dp[i-1][0],dp[i-1][1]+1),dp[i-1][2]+ (dp[i-1][0]>=MAX?2:1));
                dp[i][1] = Math.min(Math.min(dp[i-1][1],dp[i-1][0]+1),dp[i-1][2]+ (dp[i-1][1]>=MAX?2:1));
            } else {
                dp[i][0] = Math.min(Math.min(dp[i-1][0],dp[i-1][1]+1),dp[i-1][2]+1);
                dp[i][1] = Math.min(Math.min(dp[i-1][1],dp[i-1][0]+1),dp[i-1][2]+1);
                dp[i][2] = Math.min(Math.min(dp[i-1][2],dp[i-1][0]+1),dp[i-1][1]+1);
            }
        }
        return Math.min(Math.min(dp[length-1][0],dp[length-1][1]),dp[length-1][2]);
    }

    /**
     * easier solution
     * @param obstacles
     * @return
     */
    public int minSideJumpsV0(int[] obstacles) {
        int n = obstacles.length, max = Integer.MAX_VALUE / 2;
        var dp = new int[n][3];
        dp[0][0] = 1;
        dp[0][2] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] - 1 == j) {
                    dp[i][j] = max;
                } else {
                    dp[i][j] = dp[i - 1][j];
                    for (int jj = 0; jj < 3; jj++) {
                        if (j != jj && obstacles[i] - 1 != jj)
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][jj] + 1);
                    }
                }
            }
        }
        int ans = max;
        for (int j = 0; j < 3; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }
}
