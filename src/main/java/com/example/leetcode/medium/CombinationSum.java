package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = new int[]{2};
        int target = 1;
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> result = combinationSum.combinationSum(candidates,target);
        for(List<Integer> list : result){
            for(Integer value : list){
                System.out.println(value);
            }
        }
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
     */

    List<List<Integer>> ans = null;

    public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
        ans = new ArrayList<>();
        targetSum(0, candidates, target, new ArrayList<Integer>());
        return ans;

    }

    public void targetSum(int idx, int[] coins, int target, ArrayList<Integer> asf){
        if(target==0){
            ans.add(new ArrayList<Integer>(asf));
            return;
        }

        for(int i=idx; i<coins.length; i++){
            if(coins[i]<=target){
                asf.add(coins[i]);
                targetSum(i, coins, target-coins[i], asf);
                asf.remove(asf.size()-1);
            }
        }
    }


    /**
     * less memory
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumV3(int[] candidates, int target) {
        List<List<Integer>> listAll=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<Integer>();
        //排序
        Arrays.sort(candidates);
        find(listAll,list,candidates,target,0);
        return listAll;
    }
    public void find(List<List<Integer>> listAll,List<Integer> tmp,int[] candidates, int target,int num){
        //递归的终点
        if(target==0){
            listAll.add(tmp);
            return;
        }
        if(target<candidates[0]) return;
        for(int i=num;i<candidates.length&&candidates[i]<=target;i++){
            //深拷贝
            List<Integer> list=new ArrayList<>(tmp);
            list.add(candidates[i]);
            //递归运算，将i传递至下一次运算是为了避免结果重复。
            find(listAll,list,candidates,target-candidates[i],i);
        }
    }

}
