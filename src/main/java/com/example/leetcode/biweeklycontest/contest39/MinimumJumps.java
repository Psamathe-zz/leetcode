package com.example.leetcode.biweeklycontest.contest39;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 * Example 2:
 *
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 * Example 3:
 *
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 */
public class MinimumJumps {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-jumps-to-reach-home/solution/java-di-gui-huan-cun-by-blues-19/
     */
    private int[] dp=new int[4001];
    private int front,back,pos;
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Arrays.fill(dp,Integer.MAX_VALUE);
        for(int i :forbidden){
            dp[i]=-1;
        }
        front=a;
        back=b;
        pos=x;
        jump(0,0,false);
        return dp[x]==Integer.MAX_VALUE?-1:dp[x];
    }
    public void jump(int cur,int step,boolean tag){
        if(cur<0||cur>4000||step>=dp[cur]) return;
        dp[cur]=step;
        if(tag){
            jump(cur-back,1+step,false);
        }
        jump(cur+front,1+step,true);
    }


    /**
     * other option
     * @param forbidden
     * @param a
     * @param b
     * @param x
     * @return
     */
    public int minimumJumpsV1(int[] forbidden, int a, int b, int x) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[4000][2];
        for (int f : forbidden) {
            visited[f][0] = true;
            visited[f][1] = true;
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int i = curr[0];
            int d = curr[1];
            boolean back = curr[2] == 1;
            if (i == x) {
                return d;
            }
            if (visited[i][curr[2]]) {
                continue;
            }
            visited[i][curr[2]] = true;
            if (i+a < 4000) {
                queue.add(new int[]{i+a, d+1, 0});
            }
            if (i >= b && !back) {
                queue.add(new int[]{i-b, d+1, 1});
            }
        }
        return -1;
    }
}
