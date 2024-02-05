package com.example.leetcode.weeklycontest.old.test222;


/**
 * A split of an integer array is good if:
 *
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * Example 2:
 *
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * Example 3:
 *
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 *
 */
public class WaysSplitArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,5,0};
        WaysSplitArray waysSplitArray = new WaysSplitArray();
        waysSplitArray.waysToSplit(nums);
    }

    /**
     * https://leetcode-cn.com/problems/ways-to-split-array-into-three-subarrays/solution/javashuang-bai-chao-jian-ji-de-xie-fa-by-weqt/
     * @param nums
     * @return
     */
    public int waysToSplit(int[] nums) {
        int n=nums.length;
        int psum[]=new int[n+1];
        for (int i=0; i<n; ++i) {
            psum[i+1]=psum[i]+nums[i];
        }
        int res=0;
        int a=1, b=1;
        for (int i=0; i<n-2; ++i) {
            a=Math.max(i+1,a);
            while (a<n-1&&psum[a+1]-psum[i+1]<psum[i+1]) {
                a++;
            }
            if (a==n-1) {
                break;
            }
            b=Math.max(b,a);
            while (b<n-1&&psum[n]-psum[b+1]>=psum[b+1]-psum[i+1]) {
                b++;
            }
            res=(res+(b-a))%1000000007;
        }
        return res;
    }

}
