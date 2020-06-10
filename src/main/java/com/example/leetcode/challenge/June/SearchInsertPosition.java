package com.example.leetcode.challenge.June;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 7;
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        int result = searchInsertPosition.searchInsert(nums,target);
        System.out.println(result);
    }

    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        int mid = 0;
        boolean find = false;
        while (left <= right){
            mid = (left + right) / 2;
            if(nums[mid] == target){
                find = true;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(find){
            return mid;
        } else {
            if (nums[mid] < target) {
                return mid + 1;
            } else {
                return mid;
            }
        }
    }

    /**
     * less memory
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertV1(int[] nums, int target) {
        int s = 0, e = nums.length;
        while(s < e){
            int mid = (s + e) / 2;
            if(nums[mid] < target){
                s = mid + 1;
            }else{
                e = mid;
            }
        }
        return s;
    }
}
