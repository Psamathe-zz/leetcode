package com.example.leetcode.hard;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        firstMissingPositive.firstMissingPositive(nums);
        System.out.println("d");
    }

    public int firstMissingPositive(int[] nums) {
        int result = 0;
        int[] temp = new int[nums.length + 1];

        for(int value : nums){
            if(value <= nums.length && value >= 0)
                temp[value]++;
        }
        for (int i = 1; i <= nums.length; i++) {
            if(temp[i] == 0) {
                result = i;
                break;
            }
        }
        return result == 0?nums.length + 1:result;
    }

    /**
     * less memory
     * @param A
     * @return
     */
    public static int firstMissingPositive2(int[] A){
        for(int i = 0; i < A.length; i++){
            if(A[i] > 0 && A[i] <= A.length){
                if(A[i] != i + 1 && A[A[i] - 1] != A[i]){
                    int tmp = A[A[i] - 1];
                    A[A[i] - 1] = A[i];
                    A[i] = tmp;
                    i--;
                }
            }
        }

        for(int i = 0; i < A.length; i++){
            if(A[i] != i+1){
                return i+1;
            }
        }
        return A.length + 1;
    }
}
