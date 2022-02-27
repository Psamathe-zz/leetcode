package com.example.leetcode.challenge.test2022.february;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 */
public class CombinationSum {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        int min = Collections.min(Arrays.stream(candidates).boxed().collect(Collectors.toList()));
        if(target == 0 || min >target){
            return result;
        }

        for(int i =0 ;i < candidates.length; i++){
            if(target - candidates[i]>= min) {
                List<List<Integer>> resultTemp = combinationSum(candidates, target - candidates[i]);
                for (List<Integer> list : resultTemp) {
                    if (!list.contains(-1)) {
                        list.add(candidates[i]);
                        if (!result.contains(list.stream().sorted().collect(Collectors.toList())))
                            result.add(list.stream().sorted().collect(Collectors.toList()));
                    }
                }
            } else if(target - candidates[i] == 0) {
                List<Integer> t = new ArrayList<>();
                t.add(candidates[i]);
                result.add(t);

            }
        }
        return result;
    }
}
