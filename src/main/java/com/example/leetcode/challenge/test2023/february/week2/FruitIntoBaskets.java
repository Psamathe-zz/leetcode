package com.example.leetcode.challenge.test2023.february.week2;


/**
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 *
 *
 * Example 1:
 *
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * Example 2:
 *
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * Example 3:
 *
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 */
public class FruitIntoBaskets {
    public static void main(String[] args) {

    }

    public int totalFruit(int[] fruits) {
        int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
        for (int c :  fruits) {
            cur = c == a || c == b ? cur + 1 : count_b + 1;
            count_b = c == b ? count_b + 1 : 1;
            if (b != c) {
                a = b;
                b = c;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
