package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * -1, 2, 1, -4
 * -2,1,0,-5
 *
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * [0,2,1,-3]
 * 1
 * Output:
 * 3
 * Expected:
 * 0
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int  target = 1;
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        int result = threeSumClosest.threeSumClosest(nums,target);
        System.out.println(result);
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        return help(nums, target, 3);
    }

    public int help(int[] nums, int target,int numbers){
        int result = Integer.MAX_VALUE;
        if(numbers == 1){
            for (int i = 0; i < nums.length; i++) {
                if(i == 0)
                    result = nums[i];
                if(Math.abs( nums[i] - target) < Math.abs( result - target))
                    result = nums[i];
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                int temp = nums[i] + help(help2(nums, i), target - nums[i], numbers - 1);
                if(i == 0)
                    result = temp;
                if (Math.abs(temp - target) < Math.abs(result - target))
                    result = temp;
            }
        }
        return result;
    }

    public int[] help2(int[] nums, int target){
        int[] result = new int[nums.length - 1];
        int index = 0;
        for(int i= 0;i<nums.length;i++){
            if(i!=target){
                result[index++] = nums[i];
            }
        }
        return result;
    }

    /**
     * faster solution
     */

    public int threeSumClosestV2(int[] nums, int target) {
        Arrays.sort(nums);
        int minRes = 0;
        int minDiff = Integer.MAX_VALUE;
        for(int i=0;i<nums.length-2;i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int res = twoSum(target-nums[i],nums,i+1,nums.length-1);
            int tempSum = res+nums[i];
            if(tempSum == target)
                return tempSum;
            int diff = Math.abs(tempSum-target);
            if(diff<minDiff){
                minDiff = diff;
                minRes = tempSum;
            }
        }
        return minRes;
    }

    private int twoSum(int target,int nums[],int start,int end){
        int minRes = 0;
        int minDiff = Integer.MAX_VALUE;
        while(start<end){
            int tempSum = nums[start]+nums[end];
            if(tempSum == target){
                return tempSum;
            }else{
                int diff = Math.abs(tempSum-target);
                if(diff<minDiff){
                    minDiff = diff;
                    minRes = tempSum;
                }
                if(tempSum>target){
                    end--;
                }else{
                    start++;
                }
            }
        }
        return minRes;
    }


    public int threeSumClosestV3(int[] nums, int target) {
        // sorting the array so we guarantee unique solutions
        Arrays.sort(nums);

        // initialize closest with the maximum possible value for a sum in a sorted array
        int closest = nums[nums.length -3] + nums[nums.length-2] + nums[nums.length-1];
        for (int i =0; i< nums.length; i++){
            closest = twoSum(nums, i, target, closest);
            if(closest == target) return closest;
        }
        return closest;
    }

    int twoSum(int[] nums, int i,  int target, int closest){
        int low =i+1, hi=nums.length-1;
        int result = closest;
        while (low < hi){
            int sum = nums[low] + nums[hi];
            if(sum + nums[i]== target)
                return target;
            if(sum + nums[i] < target)
                low++;
            else
                hi--;
            int delta = Math.abs(sum +nums[i] - target);
            result = delta < Math.abs(target-result)? sum + nums[i] : result;
        }
        return result;
    }

}
