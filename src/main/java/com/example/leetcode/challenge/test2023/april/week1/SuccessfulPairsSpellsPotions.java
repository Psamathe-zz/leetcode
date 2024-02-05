package com.example.leetcode.challenge.test2023.april.week1;

import java.util.*;

/**
 * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 *
 * You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
 *
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
 *
 *
 *
 * Example 1:
 *
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Explanation:
 * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 * Example 2:
 *
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * Explanation:
 * - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
 * - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
 * Thus, [2,0,2] is returned.
 */
public class SuccessfulPairsSpellsPotions {
    public static void main(String[] args) {
        SuccessfulPairsSpellsPotions successfulPairsSpellsPotions = new SuccessfulPairsSpellsPotions();
        successfulPairsSpellsPotions.successfulPairs(new int[]{3,1,2}, new int[]{8,5,8}, 16l);
    }

    /**
     * https://leetcode.cn/problems/successful-pairs-of-spells-and-potions/solution/java-er-fen-fa-by-qing-bi-ning-shuang-qf2o/
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // 二分查找第一步排序
        int[] ans = new int[spells.length];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int power = spells[i];
            // 固定左右边界
            int left = 0;
            int right = potions.length - 1;

            // 用的是左右闭合的，所以额外判断一下
            while (left >= 0 && right <= potions.length && left <= right) {
                int mid = left + (right - left) / 2;
                if (1l * power * potions[mid] < success) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans[i] = potions.length - left;
        }
        return ans;
    }
}
