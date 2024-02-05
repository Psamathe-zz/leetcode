package com.example.leetcode.weeklycontest.old.test253;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * vYou are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:
 *
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 *
 * Return the minimum possible total number of stones remaining after applying the k operations.
 *
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 * Example 2:
 *
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 4. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 */
public class RemoveStonesMinimizeTotal {
    public static void main(String[] args) {
        RemoveStonesMinimizeTotal removeStonesMinimizeTotal = new RemoveStonesMinimizeTotal();
        int res = removeStonesMinimizeTotal.minStoneSum(new int[]{5,4,9},2);
        System.out.println(res);
    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>( Comparator.reverseOrder());
        for(int pile : piles){
            priorityQueue.add(pile);
        }

        while (k > 0){
            int pile = priorityQueue.poll();
            priorityQueue.add(pile - (int)Math.floor(pile/2));
            k--;
        }
        return priorityQueue.stream().mapToInt(e->e).sum();
    }
}
