package com.example.leetcode.medium;

/**
 * Given an array of integers nums, sort the array in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 */
public class SortanArray {
    public static void main(String[] args) {
        int[] nums = new int[]{5,1,1,2,0,0};
        SortanArray sortanArray = new SortanArray();
        int[] result = sortanArray.sortArrayPull(nums);
        System.out.println(result);
    }

    /**
     * 桶pai
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        int[] temp = new int[1000001];
        int index = 0;
        int t;
        for(int value : nums){
            temp[value + 50000] ++;
        }
        for (int i = 0; i < 1000001; i++) {
            t = temp[i];
            while (t > 0){
                nums[index] = i - 50000;
                index++;
                t--;
            }
        }

        return nums;
    }

    public int[] sortArrayPull(int[] nums) {
        int temp;
        int length = nums.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if(nums[j] > nums[j + 1]){
                    temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        return nums;
    }

    public int[] sortArrayVold(int[] nums) {
        int length = nums.length;
        int temp;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if(nums[j] < nums[i] ){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
