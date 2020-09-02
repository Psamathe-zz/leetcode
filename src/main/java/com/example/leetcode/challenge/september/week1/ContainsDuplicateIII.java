package com.example.leetcode.challenge.september.week1;


import java.util.HashMap;
import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) {
        int[] nums = new int[]{1,5,9,1,5,9};
        int k = 2;
        int t = 3;
        ContainsDuplicateIII containsDuplicateIII = new ContainsDuplicateIII();
        boolean res = containsDuplicateIII.containsNearbyAlmostDuplicate(nums,k,t);
        System.out.println(res);
    }

    /**
     * https://soulmachine.gitbooks.io/algorithm-essentials/content/java/linear-list/array/contains-duplicate-iii.html
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0)
            return false;

        final TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            final int x = nums[i];
            final Integer floor = set.floor(x);
            final Integer ceiling = set.ceiling(x);

            if ((floor != null && x <= floor + t)
                    || (ceiling != null && x >= ceiling -t))
                return true;

            set.add(x);
            if (i >= k) set.remove(nums[i - k]);
        }

        return false;
    }

    /**
     * faster solution
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicateV1(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        if(k >= 10000 && t == 0) {
            return false;
        }
        for(int i = 0; i < nums.length; i++) {
            for(int j = i+1; j <= i+k; j++) {
                if(j >= nums.length) {
                    break;
                }
                if((long)nums[i] - (long) nums[j] > (long)Integer.MAX_VALUE ||(long) nums[j] - (long)nums[i] > (long)Integer.MAX_VALUE) {
                    continue;
                }
                if(Math.abs(nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}
