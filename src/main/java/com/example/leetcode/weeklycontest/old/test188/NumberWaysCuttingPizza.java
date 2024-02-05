package com.example.leetcode.weeklycontest.old.test188;


import java.util.PriorityQueue;

/**
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k.
 * You have to cut the pizza into k pieces using k-1 cuts.
 *
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces.
 * If you cut the pizza vertically, give the left part of the pizza to a person.
 * If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 *
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 * Example 2:
 *
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 * Example 3:
 *
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 */
public class NumberWaysCuttingPizza {

    public static void main(String[] args) {
        String[] pizza = new String[]{"A..","AAA","..."};
        int k = 3;
        NumberWaysCuttingPizza numberWaysCuttingPizza = new NumberWaysCuttingPizza();
        int result = numberWaysCuttingPizza.ways(pizza,k);
        System.out.println(result);
    }

    public int ways(String[] pizza, int k) {
        int result = 0;
        int u = pizza.length;
        int v = pizza[0].length();
        int[][] map = new int[u][v];

        boolean[] vertical = new boolean[u];
        boolean[] horizontal = new boolean[v];

        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                map[i][j] = pizza[i].charAt(j) == 'A' ? 1:0;
            }
        }

        help(map,0,u-1,0,v-1);

        return result;
    }

    int help(int[][] map, int high, int bottom,int left,int right){
        int result = 0;
        int iVertical = getVertical(map,high,bottom,left,right);
        int iHorizontal = getHorizontal(map,high,bottom,left,right);
        if(iVertical == 0 && iVertical == 0)
            return 0;
        else
        return result;
    }

    int getVertical(int[][] map, int high, int bottom,int left,int right){
        int result = 0;

        for(int i = high;i <= bottom;i++){
            boolean temp = false;
            for(int j = left; j <= right; j ++){
                if(map[i][j] == 1) {
                    temp = true;
                    break;
                }
            }
            if (temp)
                result++;
        }
        return result;
    }

    int getHorizontal(int[][] map, int high, int bottom,int left,int right){
        int result = 0;

        for(int i = left; i <= right; i ++){
            boolean temp = false;
            for(int j = high;j <= bottom;j++){
                if(map[j][i] == 1){
                    temp = true;
                    break;
                }
            }
            if (temp)
                result++;
        }
        return result;
    }

    public class Cut{
        PriorityQueue<Integer> verticalCut;
        PriorityQueue<Integer> horizontalCut;

        public Cut() {
            this.verticalCut = new PriorityQueue<>((e1,e2) -> e1 - e2);
            this.horizontalCut = new PriorityQueue<>((e1,e2) -> e1 - e2);
        }
    }


}
