package com.example.leetcode.challenge.test2020.november.week2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * */
public class PermutationsII {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4359825.html
     * @param nums
     * @return
     */

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        permute(nums, 0);
        return res;
    }

    void permute(int[] nums, int start) {
        if (start >= nums.length) res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        for (int i = start; i < nums.length; ++i) {
            int j = i - 1;
            while (j >= start && nums[j] != nums[i]) --j;
            if (j != start - 1) continue;
            int t = nums[i];
            nums[i] = nums[start];
            nums[start] = t;
            permute(nums, start + 1);
            t = nums[i];
            nums[i] = nums[start];
            nums[start] = t;
        }
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUniqueV1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums != null && nums.length > 0) {
            Arrays.sort(nums);
            boolean[] used = new boolean[nums.length];

            permuteUnique(nums, used, new ArrayList<>(), result);
        }

        return result;
    }

    private void permuteUnique(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && !used[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }

            used[i] = true;
            list.add(nums[i]);
            permuteUnique(nums, used, list, result);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}
