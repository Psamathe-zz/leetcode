package com.example.leetcode.challenge.april.week1;

import java.util.Arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 * 分析
 * 最大连续子序列和，非常经典的题。
 * 当我们从头到尾遍历这个数组的时候，对于数组里的一个整数，它有几种选择呢？它只有两种选择： 1、加入之前的SubArray；2. 自己另起一个SubArray。那什么时候会出现这两种情况呢？
 * 如果之前SubArray的总体和大于0的话，我们认为其对后续结果是有贡献的。这种情况下我们选择加入之前的SubArray
 * 如果之前SubArray的总体和为0或者小于0的话，我们认为其对后续结果是没有贡献，甚至是有害的（小于0时）。这种情况下我们选择以这个数字开始，另起一个SubArray。
 * 设状态为f[j]，表示以S[j]结尾的最大连续子序列和，则状态转移方程如下：
 * f[j] = \max\left\{f[j-1]+S[j],S[j]\right\}, 1 \leq j \leq nf[j]=max{f[j−1]+S[j],S[j]},1≤j≤n
 * target = \max\left\{f[j]\right\}, 1 \leq j \leq ntarget=max{f[j]},1≤j≤n
 * 解释如下：
 * 情况一，S[j]不独立，与前面的某些数组成一个连续子序列，则最大连续子序列和为f[j-1]+S[j]。
 * 情况二，S[j]独立划分成为一段，即连续子序列仅包含一个数S[j]，则最大连续子序列和为S[j]。
 * 其他思路：
 * 思路2：直接在i到j之间暴力枚举，复杂度是O(n^3)
 * 思路3：处理后枚举，连续子序列的和等于两个前缀和之差，复杂度O(n^2)。
 * 思路4：分治法，把序列分为两段，分别求最大连续子序列和，然后归并，复杂度O(nlog n)
 * 思路5：把思路2O(n^2)的代码稍作处理，得到O(n)的算法
 * 思路6：当成M=1的最大M子段和
 * https://cloud.tencent.com/developer/article/1403056
 * https://www.cnblogs.com/xiaochuan94/p/9864536.html
 * https://soulmachine.gitbooks.io/algorithm-essentials/java/dp/maximum-subarray.html
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        if(nums.length==0||nums==null)
            return 0;
        if(length==1)
            return nums[0];
        int result = nums[0];

        int temp;
        for(int i = 0; i< length;i++){
            temp = 0;
            for(int j = i  ; j < length ; j ++){
                temp += nums[j];
                if(temp > result ){
                    result = temp;
                }
            }
        }
        return result;
    }

    public int maxSubArrayV2(int[] nums) {
        int maxLocal = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            //另起一断
            maxLocal = Math.max(nums[i], nums[i] + maxLocal);
            global = Math.max(global, maxLocal);
        }
        return global;
    }

    /**
     * 分治法
     * @param nums
     * @return
     */

    public int maxSubArrayV3(int[] nums) {
        return mcss(nums, 0, nums.length);
    }
    // 思路5，求最大连续子序列和
    private static int mcss(int[] nums, int begin, int end) {
        final int n = end - begin;
        int[] sum = new int[n + 1];  // 前n项和

        int result = Integer.MIN_VALUE;
        int cur_min = sum[0];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[begin  + i - 1];
        }
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, sum[i] - cur_min);
            cur_min = Math.min(cur_min, sum[i]);
        }
        return result;
    }

    /**
    * 内存最优
     *
     */

    public int maxSubArrayV4(int[] nums) {
        int n = nums.length;
        int[] sums = new int[n];
        sums[0] = nums[0];
        int max = nums[0];
        int maxSum = nums[0];
        int minNegSum = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i-1] + nums[i];
        }

        for (int i = 1; i < n; i++) {
            if (minNegSum <= 0 && sums[i] - minNegSum > max) {
                max = sums[i] - minNegSum;
            }

            if (sums[i] < minNegSum) {
                minNegSum = sums[i];
            }

        }

        return Math.max(max, Arrays.stream(sums).max().getAsInt());

    }

    /**
     * 最快
     * @param nums
     * @return
     */
    public int maxSubArrayV5(int[] nums) {
        int left = 0;
        int right = 1;
        int max = nums[left];
        int sum = nums[left];

        if(nums.length == 1)
            return nums[0];

        while(right < nums.length && left < nums.length){
            if((sum + nums[right]) < nums[right]) {
                left = right;
                sum = nums[right];
                if(nums[right] > max){
                    max = nums[right];
                }
            }else{
                sum += nums[right];
                if(sum > max){
                    max = sum;
                }
            }
            right ++;
        }

        return max;

    }


    public int maxSubArrayV6(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > 0) {
                nums[i] += nums[i-1];
            }
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] nums1 = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        int result = maximumSubarray.maxSubArray(nums1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(result);
    }

}
