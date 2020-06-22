package com.example.leetcode.medium;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
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
        sortColors.sortColorsV2(nums);
        System.out.println(nums);
    }

    public void sortColors(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[j] < nums[i]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public void sortColorsV2(int[] nums) {
        int red = 0;
        int white = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                red++;
            } else if (nums[i] == 1) {
                white++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i<red){
                nums[i] = 0;
            } else if (red<=i && i<white + red) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }


    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int BLUE = 2;

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColorsV3(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int red = 0, blue = nums.length - 1;
        for (int i = 0; i <= blue; ) {
            if (nums[i] == RED) swap(nums, red++, i++);
            else if (nums[i] == WHITE) i++;
            else swap(nums, blue--, i);
        }
    }
}
