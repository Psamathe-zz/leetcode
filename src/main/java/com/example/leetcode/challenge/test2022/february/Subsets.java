package com.example.leetcode.challenge.test2022.february;

import scala.reflect.api.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsets(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int val : nums) {
            map.compute(val, (k, v ) -> {
                if(v == null)
                    return 1;
                else
                    return v + 1;
            });
        }


        List<List<Integer>>  res = new ArrayList<>();
        res.add(new ArrayList<>());
        List<Integer> t;
        List<List<Integer>> t2;
        List<List<Integer>> t3;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            t = new ArrayList<>();
            t2 = new ArrayList<>();
            t3 = new ArrayList<>();
            for (int i = 0; i < entry.getValue(); i++) {
                t.add(entry.getKey());
                t2.add(t);
            }
            for (List<Integer> l1 : t2) {
                for (List<Integer> l2 : res) {
                    t = new ArrayList<>();
                    t.addAll(l2);
                    t.addAll(l1);
                    t3.add(t);
                }
            }
            res.addAll(t3);
        }
        return res;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsV1(int[] nums) {
        List<List<Integer>> paths = new ArrayList<>();
        paths.add(new ArrayList<>());
        for(int i = 0; i < nums.length; i++){
            int size = paths.size();
            for(int j = 0; j < size; j++){
                List<Integer> path = new ArrayList<>(paths.get(j));
                path.add(nums[i]);
                paths.add(path);
            }
        }
        return paths;
    }
}
