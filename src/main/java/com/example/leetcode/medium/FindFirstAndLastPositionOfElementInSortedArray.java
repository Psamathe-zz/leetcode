package com.example.leetcode.medium;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4409379.html
     */
    public int[] searchRange(int[] nums, int target) {
        int idx = search(nums, 0, nums.length - 1, target);
        if (idx == -1)
            return new int[]{-1, -1};
        int left = idx, right = idx;
        while (left > 0 && nums[left - 1] == nums[idx])
            --left;
        while (right < nums.length - 1 && nums[right + 1] == nums[idx])
            ++right;
        return new int[]{left, right};
    }

    int search(int[] nums, int left, int right, int target) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] < target)
            return search(nums, mid + 1, right, target);
        else
            return search(nums, left, mid - 1, target);
    }


    /**
     * 使用两次二分查找法，第一次找到左边界，第二次调用找到右边界
     * @param nums
     * @param target
     * @return
     */
    public int[]  searchRangeV1(int[]  nums, int target) {
        int[] res = new int[2];
        Arrays.fill(res,-1);
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (right == nums.length || nums[right] != target) return res;
        res[0] = right;
        right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        res[1] = right - 1;
        return res;
    }
}
