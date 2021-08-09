package com.example.leetcode.challenge.test2021.august.week1;


import java.util.*;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class SubsetsII {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer,Integer> count = new HashMap<>();
        for (int val: nums){
            count.put(val,count.getOrDefault(val,0) + 1);
        }
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> temp;
        res.add(new ArrayList<>());

        for (Map.Entry<Integer,Integer> entry : count.entrySet()){
            temp = new ArrayList<>();
            for (int i = 1; i <= entry.getValue(); i++) {
                for (List<Integer> list: res){
                    List<Integer> listTemp = new ArrayList<>(list);
                    listTemp.addAll(Collections.nCopies(i,entry.getKey()));
                    temp.add(listTemp);
                }
            }
            res.addAll(temp);
        }
        return res;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4310964.html
     * faster solution
     */
    List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDupV1(int[] nums) {
        res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        Arrays.sort(nums);
        subsets (nums,0,new ArrayList<Integer>());

        return res;
    }

    private void subsets(int[] nums, int index, List<Integer> list){
        res.add(new ArrayList<>(list));


        for (int i = index; i < nums.length; i ++){

            if (i > index && nums[i] == nums[i-1])
                continue;

            list.add(nums[i]);
            subsets(nums,i+1,list);
            list.remove(list.size()-1);
        }
    }

}
