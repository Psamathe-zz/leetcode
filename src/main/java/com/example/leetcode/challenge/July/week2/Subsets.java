package com.example.leetcode.challenge.July.week2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Subsets subsets = new Subsets();
        subsets.subsetsV1(nums);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4309345.html
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        Queue<List<Integer>> res = new LinkedList<>();
        nums = Arrays.stream(nums).sorted().distinct().toArray();
        int length = nums.length;
        int size;
        res.add(new ArrayList<>());
        for (int i = 0; i < length; i++) {
            size = res.size();
            for (int j = 0; j < size; ++j) {
                List<Integer> temp = res.poll();
                res.add(temp);
                List<Integer> temp1 = new ArrayList<>(temp);
                temp1.add(nums[i]);
                res.add(temp1);
            }
        }

        return res.stream().collect(Collectors.toList());
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    List<List<Integer>> ans;
    public List<List<Integer>> subsetsV1(int[] nums) {
        ans=new ArrayList<>();
        gensubset(0,nums,new ArrayList<>());
        return ans;
    }

    public void gensubset(int index, int[]nums, List<Integer> curr){
        ans.add(new ArrayList<>(curr));
        for(int i=index;i<nums.length;i++){
            curr.add(nums[i]);
            gensubset(i+1,nums,curr);
            curr.remove(curr.size()-1);
        }
    }

}
