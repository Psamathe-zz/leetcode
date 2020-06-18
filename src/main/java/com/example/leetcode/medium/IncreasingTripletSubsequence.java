package com.example.leetcode.medium;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 *
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5]
 * Output: true
 * Example 2:
 *
 * Input: [5,4,3,2,1]
 * Output: false
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,6,4,5};
        IncreasingTripletSubsequence increasingTripletSubsequence = new IncreasingTripletSubsequence();
        boolean result = increasingTripletSubsequence.increasingTripletV1(nums);
        System.out.println(result);
    }

    public boolean increasingTriplet(int[] nums) {
        int length = nums.length;

        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                if(nums[j] > nums[i]) {
                    for (int k = j + 1; k < length; k++) {
                        if(nums[k] > nums[j])
                            return true;
                    }
                }
            }
        }
        return  false;
    }

    public boolean increasingTripletV1(int[] nums) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for(int n : nums){
            if(n <= min)
                min = n;
            else if(n < secondMin)
                secondMin = n;
            else if(n > secondMin)
                return true;

        }
        return false;
    }
}
