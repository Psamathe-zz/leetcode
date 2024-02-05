package com.example.leetcode.weeklycontest.old.test187;

/**
 * Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k places away from each other, otherwise return False.
 *
 * Example 1:
 *
 * Input: nums = [1,0,0,0,1,0,0,1], k = 2
 * Output: true
 * Explanation: Each of the 1s are at least 2 places away from each other.
 * Example 2:
 *
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: false
 * Explanation: The second 1 and third 1 are only one apart from each other.
 * Example 3:
 *
 * Input: nums = [1,1,1,1,1], k = 0
 * Output: true
 * Example 4:
 *
 * Input: nums = [0,1,0,1], k = 1
 * Output: true
 */
public class CheckIfAll1 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,0,0,1,0,1};
        int k = 2;
        CheckIfAll1 checkIfAll1 = new CheckIfAll1();
        boolean result = checkIfAll1.kLengthApart(nums,k);
        System.out.println(result);
    }

    public boolean kLengthApart(int[] nums, int k) {
        int temp = k;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] == 1){
                if(temp>=k)
                    temp=0;
                else
                    return false;
            } else {
                temp++;
            }
        }
        return true;
    }

    /**
     * faster solution
     * @param nums
     * @param k
     * @return
     */
    public boolean kLengthApartV2(int[] nums, int k) {
        int prev = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (prev != -1 && i - prev - 1 < k)
                    return false;
                prev = i;
            }
        }
        return true;
    }
}
