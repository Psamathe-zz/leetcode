package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        CombinationSumIII combinationSumIII = new CombinationSumIII();
        List<List<Integer>> result = combinationSumIII.combinationSum3(k, n);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {

        return help(k,n,1);
    }

    public List<List<Integer>> help(int k, int n, int min){
        List<List<Integer>> result = new ArrayList<>();
        if(k==1){
            if(n >= min && n <= 9 ) {
                List<Integer> temp = new ArrayList<>();
                temp.add(n);
                result.add(temp);
            }
            return result;
        }
        for (int i = min; i <= 9 ; i++) {
            List<List<Integer>> temp = help(k - 1 ,n - i, i + 1);
            for (List<Integer> list : temp){
                list.add(i);
            }
            result.addAll(temp);
        }
        return result;
    }


    /**
     * less memory
     */
    List<List<Integer>> res = new ArrayList();
    public List<List<Integer>> combinationSum3V2(int k, int n) {
        if(n == 0 || k == 0)
            return res;
        helper(new ArrayList(),1,k,n);
        return res;
    }

    public void helper(List<Integer> list, int low,int k,int sum){
        if(sum < 0 || list.size() > k)
            return;
        if(list.size()==k && sum == 0){
            res.add(new ArrayList(list));
            return;
        }
        for(int i=low;i<=9;i++){
            list.add(i);
            helper(list,i+1,k,sum-i);
            list.remove(list.size()-1);
        }
    }
}
