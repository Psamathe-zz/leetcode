package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 * Given nums = [1,1,1,2,2,3],
 *
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 *
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 *
 * Given nums = [0,0,1,1,1,1,2,3,3],
 *
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 *
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 *
 * Confused why the returned value is an integer but your answer is an array?
 *
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 *
 * Internally you can think of this:
 *
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 *
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * 0,0,1,1,1,1,2,3,3,3
 * 0,0,1,1,3,3,2,3,1,1
 * 0,0,1,1,2,3,3,3,1,1
 * [0,0,0,0,3,4]
 * Output
 * [0,0,4]
 * Expected
 * [0,0,3,4]
 */
public class RemoveDuplicatesfromSortedArrayII {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,0,3};
        RemoveDuplicatesfromSortedArrayII removeDuplicatesfromSortedArrayII = new RemoveDuplicatesfromSortedArrayII();
        int result = removeDuplicatesfromSortedArrayII.removeDuplicates(nums);
        System.out.println(result);
    }

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length <= 2)
            return length;
        int current = nums[0];
        int count = 1;
        int index = 1;
        while(index < length){
            if(current < nums[index]){
                current = nums[index];
                count = 1;
            } else if(current == nums[index]){
                count++;
            } else{
                break;
            }
            if(count <= 2){
                index++;
            } else {
                int tempIndex = index;
                int backIndex = length - 1;
                while(tempIndex < length && nums[tempIndex] == current && tempIndex < backIndex){
                    while ( nums[backIndex] <= current && backIndex> tempIndex){
                        backIndex--;
                    }
                    if(backIndex <= tempIndex)
                        break;
                    else {
                        int temp = nums[backIndex];
                        nums[backIndex] = nums[tempIndex];
                        nums[tempIndex] = temp;
                    }
                    tempIndex++;
                }
                if(backIndex > index)
                    Arrays.sort(nums,index,backIndex);
                else
                    return index;
            }
        }
        return index;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int removeDuplicatesV1(int[] nums) {
        int left = 0;
        int right = 0;
        int prev = Integer.MIN_VALUE;
        int count = 0;
        while(right<nums.length){
            if (nums[right] == prev && count<2){
                nums[left] = nums[right];
                left++;
                count++;
            }else if (nums[right]>prev){
                prev = nums[right];
                nums[left] = nums[right];
                left++;
                count = 1;
            }
            right++;
        }
        return left;
    }
}
