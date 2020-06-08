package com.example.leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 1,2,5,3,2,1
 * 4,3,1,2
 * 1　　2　　7　　3　　4　　1
 * 1　　2　　7　　3　　4　　1
 * 1　　3　　7　　2　　4　　1
 */
    public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        NextPermutation nextPermutation = new NextPermutation();
        nextPermutation.nextPermutation(nums);
        nums.toString();
    }

    public void nextPermutation(int[] nums) {
        int index = nums.length;
        int length = nums.length;
        if (index == 0 || length == 1) return;
        for (int i = 1; i < length; i++) {
            if (nums[i - 1] >= nums[i])
                continue;
            else{
                index = i;
            }
        }
        if (index == length) {
            for (int i = 0; i * 2 + 1 < length; i++) {
                int tmp = nums[i];
                nums[i] = nums[length -i -1];
                nums[length - i - 1] =tmp;
            }
        } else if (index + 1 == length) {
            int tmp = nums[index - 1];
            nums[index -1] =nums[index];
            nums[index] = tmp;
        } else {
            int base = nums[index - 1];
            int minValue = nums[index];
            int minIndex = index;
            for (int i = index + 1; i < length; i++) {
                if (nums[i] > base && nums[i] <= minValue) {
                    minValue = nums[i];
                    minIndex = i;
                }
            }
            int tmp = nums[index - 1];
            nums[index - 1] =nums[minIndex];
            nums[minIndex] = tmp;

            int newlength = (length - index) /2;
            for (int i = 0; i < newlength; i++) {
                tmp = nums[i + index];
                nums[i + index] = nums[length -i -1];
                nums[length -i -1] =tmp;
            }
        }
    }

    /**
     * faster solution
     * @param nums
     */
    public void nextPermutationV1(int[] nums) {
        // 1, 2, 7, 4, 3, 1
        //    ^
        // 1, 2, 7, 4, 3, 1
        //             ^
        // 1, 3, 7, 4, 2, 1
        // 1, 3, 1, 2, 4, 7
        if (nums == null || nums.length == 0) {
            return;
        }
        int firstSmall = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmall = i;
                break;
            }
        }
        if (firstSmall == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int firstLarge = -1;
        for (int i = nums.length - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }
        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
