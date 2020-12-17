package com.example.leetcode.biweeklycontest.contest41;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones in a pile. On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently.
 *
 * You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone.
 *
 * The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play optimally.
 *
 * Determine the result of the game, and:
 *
 * If Alice wins, return 1.
 * If Bob wins, return -1.
 * If the game results in a draw, return 0.
 *
 *
 * Example 1:
 *
 * Input: aliceValues = [1,3], bobValues = [2,1]
 * Output: 1
 * Explanation:
 * If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
 * Bob can only choose stone 0, and will only receive 2 points.
 * Alice wins.
 * Example 2:
 *
 * Input: aliceValues = [1,2], bobValues = [3,1]
 * Output: 0
 * Explanation:
 * If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
 * Draw.
 * Example 3:
 *
 * Input: aliceValues = [2,4,3], bobValues = [1,6,7]
 * Output: -1
 * Explanation:
 * Regardless of how Alice plays, Bob will be able to have more points than Alice.
 * For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob's 7.
 * Bob wins.
 *
 *
 * Constraints:
 *
 * n == aliceValues.length == bobValues.length
 * 1 <= n <= 105
 * 1 <= aliceValues[i], bobValues[i] <= 100
 */
public class StoneGameVI {
    public static void main(String[] args) {
        int[] a = new int[]{2,4,3};
        int[] b = new int[]{1,6,7};
        StoneGameVI stoneGameVI = new StoneGameVI();
        stoneGameVI.stoneGameVIV1(a,b);
    }

    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/1522619/
     * @param aliceValues
     * @param bobValues
     * @return
     */
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int length = aliceValues.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(i,aliceValues[i] + bobValues[i]);
        }

        int turn = 1;
        int s1 = 0 , s2 = 0;
        List<Map.Entry<Integer,Integer>> list = map.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        for(Map.Entry<Integer,Integer> entry : list){
            int t = entry.getKey();
            if(turn == 1)
                s1 += aliceValues[t];
            else
                s2 += bobValues[t];
            turn ^= 1;
        }
        if(s1 > s2)
            return 1;
        else if(s1 < s2)
            return -1;
        else return 0;
    }


    /**
     * faster solution
     * @param aliceValues
     * @param bobValues
     * @return
     */
    public int stoneGameVIV1(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] stones = new int[n][];
        for (int i = 0; i < n; i++)
            stones[i] = new int[]{aliceValues[i], bobValues[i]};
        Arrays.sort(stones, (s1, s2) -> Integer.compare(s1[0] + s1[1], s2[0] + s2[1]));
        int aSum = 0;
        int bSum = 0;
        for (int i = n-1; i >= 0; i -= 2)
            aSum += stones[i][0];
        for (int i = n-2; i >= 0; i -= 2)
            bSum += stones[i][1];
        return aSum > bSum ? 1 : aSum == bSum ? 0 : -1;
    }
}
