package com.example.leetcode.challenge.test2020.June.week2;

/**
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {
    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
        nums.toString();
    }

    public void sortColors(int[] nums) {
        int length = nums.length;
        if(length == 0)
            return;
        int[] count = new int[3];
        for (int value : nums){
            count[value]++;
        }
        int i;
        for (i = 0; i < count[0]; i++) {
            nums[i] = 0;
        }
        for (i = count[0]; i < count[0] + count[1]; i++) {
            nums[i] = 1;
        }
        for (i = count[0] + count[1]; i < length; i++) {
            nums[i] = 2;
        }

    }


    /**
     * less memory
     * @param nums
     */
    public void sortColorsV1(int[] nums) {

        if(nums.length < 2)
        {
            return;
        }

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;


        while(mid <= high)
        {
            if(nums[mid] == 0)
            {
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;
                mid++;
            }
            else if(nums[mid] == 1)
            {
                mid++;
            }
            else if(nums[mid] == 2)
            {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}
