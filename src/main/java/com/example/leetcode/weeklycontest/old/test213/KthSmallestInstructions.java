package com.example.leetcode.weeklycontest.old.test213;


/**
 * Bob is standing at cell (0, 0), and he wants to reach destination: (row, column). He can only travel right and down. You are going to help Bob by providing instructions for him to reach destination.
 *
 * The instructions are represented as a string, where each character is either:
 *
 * 'H', meaning move horizontally (go right), or
 * 'V', meaning move vertically (go down).
 * Multiple instructions will lead Bob to destination. For example, if destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.
 *
 * However, Bob is very picky. Bob has a lucky number k, and he wants the kth lexicographically smallest instructions that will lead him to destination. k is 1-indexed.
 *
 * Given an integer array destination and an integer k, return the kth lexicographically smallest instructions that will take Bob to destination.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: destination = [2,3], k = 1
 * Output: "HHHVV"
 * Explanation: All the instructions that reach (2, 3) in lexicographic order are as follows:
 * ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
 * Example 2:
 *
 *
 *
 * Input: destination = [2,3], k = 2
 * Output: "HHVHV"
 * Example 3:
 *
 *
 *
 * Input: destination = [2,3], k = 3
 * Output: "HHVVH"
 *
 *
 * Constraints:
 *
 * destination.length == 2
 * 1 <= row, column <= 15
 * 1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b​​​​​.
 */
public class KthSmallestInstructions {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode-cn.com/problems/kth-smallest-instructions/solution/dong-tai-gui-hua-by-maxatom-2/
     * @param d
     * @param k
     * @return
     */
    public String kthSmallestPath(int[] d, int k) {
        dp=new int[d[0]+1][d[1]+1];
        dfs(d, k, new int[]{0,0}, new StringBuilder());
        return res;
    }
    int cnt=0;
    String res=null;
    int[][] dp;
    int dfs(int[] d, int k, int[] c,StringBuilder sb){
        if(c[0]<0  || c[0]>d[0] || c[1]<0 || c[1]>d[1]){ //越界
            return 0;
        }
        if(c[0]==d[0] && c[1]==d[1]){
            if(cnt==k-1) {
                res=sb.toString();
            }else{
                cnt++;
            }
            return 1;
        }

        if(dp[c[0]][c[1]]>0 && dp[c[0]][c[1]]+cnt<k){
            cnt += dp[c[0]][c[1]];
            return dp[c[0]][c[1]];
        }
        int pathc=0;
        pathc += dfs(d, k, new int[]{c[0], c[1]+1}, sb.append('H'));
        sb.deleteCharAt(sb.length()-1);
        if(res==null){
            pathc+=dfs(d, k, new int[]{c[0]+1, c[1]}, sb.append('V'));
            sb.deleteCharAt(sb.length()-1);
        }
        return dp[c[0]][c[1]]=pathc;
    }


    /**
     * faster solution
     * @param destination
     * @param k
     * @return
     */
    public String kthSmallestPathV1(int[] destination, int k) {
        int x = destination[0], y = destination[1];
        int[][] comb = new int[x+y+1][x+y+1];
        for (int i = 1; i < x+y+1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    comb[i][j] = 1;
                } else {
                    comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        return helper(sb, x, y, k, comb);
    }

    private String helper(StringBuilder cur, int x, int y, int k, int[][] comb) {
        if (x+y == 0) {
            return cur.toString();
        } else if (x == 0) {
            return helper(cur.append('H'), x, y-1, k, comb);
        } else if (y == 0) {
            return helper(cur.append('V'), x-1, y, k, comb);
        } else {
            if (k > comb[x+y-1][y-1]) {
                return helper(cur.append('V'), x-1, y, k-comb[x+y-1][y-1], comb);
            } else{
                return helper(cur.append('H'), x, y-1, k, comb);
            }
        }
    }

}
