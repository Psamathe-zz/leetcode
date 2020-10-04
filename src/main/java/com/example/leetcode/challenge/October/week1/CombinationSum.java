package com.example.leetcode.challenge.October.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
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
 * Example 4:
 *
 * Input: candidates = [1], target = 1
 * Output: [[1]]
 * Example 5:
 *
 * Input: candidates = [1], target = 2
 * Output: [[1,1]]
 */
public class CombinationSum {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if(target == 0 || Collections.min(Arrays.stream(candidates).boxed().collect(Collectors.toList()))>target){
            return result;
        }

        for(int i =0 ;i < candidates.length; i++){
            if(target - candidates[i]>=Collections.min(Arrays.stream(candidates).boxed().collect(Collectors.toList()))) {
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


    /**
     * faster solution
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumV1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void combinationSum(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start) {
        if(target == 0) {
            result.add(new ArrayList<>(cur));
        } else {
            for(int i = start; i < candidates.length; i++) {
                if(candidates[i] <= target) {
                    cur.add(candidates[i]);
                    combinationSum(result, cur, candidates, target - candidates[i], i);
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}
