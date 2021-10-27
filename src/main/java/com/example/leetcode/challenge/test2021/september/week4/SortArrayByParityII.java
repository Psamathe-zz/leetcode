package com.example.leetcode.challenge.test2021.september.week4;

/**
 * Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
 * <p>
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
 * <p>
 * Return any answer array that satisfies this condition.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 * Example 2:
 * <p>
 * Input: nums = [2,3]
 * Output: [2,3]
 * [3,1,4,2]
 * [888,505,627,846]
 * [2,0,3,4,1,3]
 */
public class SortArrayByParityII {
    public static void main(String[] args) {
        SortArrayByParityII sortArrayByParityII = new SortArrayByParityII();
        sortArrayByParityII.sortArrayByParityII(new int[]{888,505,627,846});
    }

    public int[] sortArrayByParityII(int[] nums) {
        int length = nums.length;
        int oddIndex = 1;
        int evenIndex = 0;
        int temp;
        while (oddIndex < length && evenIndex < length){
            while (oddIndex < length && nums[oddIndex] % 2 == 1 ){
                oddIndex += 2;
            }
            while (evenIndex < length && nums[evenIndex] % 2 == 0) {
                evenIndex += 2;
            }
            if(oddIndex < length && evenIndex < length) {
                temp = nums[oddIndex];
                nums[oddIndex] = nums[evenIndex];
                nums[evenIndex] = temp;
            }
        }

        return nums;
    }
}
