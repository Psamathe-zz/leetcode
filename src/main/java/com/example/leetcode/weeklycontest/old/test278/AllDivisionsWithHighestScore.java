package com.example.leetcode.weeklycontest.old.test278;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 0-indexed binary array nums of length n. nums can be divided at index i (where 0 <= i <= n) into two arrays (possibly empty) numsleft and numsright:
 *
 * numsleft has all the elements of nums between index 0 and i - 1 (inclusive), while numsright has all the elements of nums between index i and n - 1 (inclusive).
 * If i == 0, numsleft is empty, while numsright has all the elements of nums.
 * If i == n, numsleft has all the elements of nums, while numsright is empty.
 * The division score of an index i is the sum of the number of 0's in numsleft and the number of 1's in numsright.
 *
 * Return all distinct indices that have the highest possible division score. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,0,1,0]
 * Output: [2,4]
 * Explanation: Division at index
 * - 0: numsleft is []. numsright is [0,0,1,0]. The score is 0 + 1 = 1.
 * - 1: numsleft is [0]. numsright is [0,1,0]. The score is 1 + 1 = 2.
 * - 2: numsleft is [0,0]. numsright is [1,0]. The score is 2 + 1 = 3.
 * - 3: numsleft is [0,0,1]. numsright is [0]. The score is 2 + 0 = 2.
 * - 4: numsleft is [0,0,1,0]. numsright is []. The score is 3 + 0 = 3.
 * Indices 2 and 4 both have the highest possible division score 3.
 * Note the answer [4,2] would also be accepted.
 * Example 2:
 *
 * Input: nums = [0,0,0]
 * Output: [3]
 * Explanation: Division at index
 * - 0: numsleft is []. numsright is [0,0,0]. The score is 0 + 0 = 0.
 * - 1: numsleft is [0]. numsright is [0,0]. The score is 1 + 0 = 1.
 * - 2: numsleft is [0,0]. numsright is [0]. The score is 2 + 0 = 2.
 * - 3: numsleft is [0,0,0]. numsright is []. The score is 3 + 0 = 3.
 * Only index 3 has the highest possible division score 3.
 * Example 3:
 *
 * Input: nums = [1,1]
 * Output: [0]
 * Explanation: Division at index
 * - 0: numsleft is []. numsright is [1,1]. The score is 0 + 2 = 2.
 * - 1: numsleft is [1]. numsright is [1]. The score is 0 + 1 = 1.
 * - 2: numsleft is [1,1]. numsright is []. The score is 0 + 0 = 0.
 * Only index 0 has the highest possible division score 2.
 *
 */
public class AllDivisionsWithHighestScore {
    public static void main(String[] args) {
        AllDivisionsWithHighestScore allDivisionsWithHighestScore = new AllDivisionsWithHighestScore();
        allDivisionsWithHighestScore.maxScoreIndices(new int[]{0,0,1,0});
    }

    public List<Integer> maxScoreIndices(int[] nums) {
        int count1 = (int)Arrays.stream(nums).filter(e -> e == 1).count();
        List<Integer> res = new ArrayList<>();
        res.add(0);
        int count0 = 0;
        int max = count1;
        int index = 0;
        for (int val : nums) {
            if(val == 0)
                count0++;
            else
                count1--;
            if(count0 + count1 >= max) {
                if(count0 + count1 > max) {
                    res.clear();
                    max = count0 + count1;
                }
                res.add(index + 1);
            }
            index++;

        }
        return res;
    }
}
