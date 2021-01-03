package com.example.leetcode.challenge.Test2020.may.week4;



import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 * Input:
 * [0,0,1,0,0,0,1,1]
 * Output:
 * 4
 * Expected:
 * 6
 */
public class ContiguousArray {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,0,0,0,1,1};
        ContiguousArray contiguousArray = new ContiguousArray();
        int result = contiguousArray.findMaxLength(nums);
        System.out.println(result);
    }

    public int findMaxLength(int[] nums) {
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0,-1);
        for(int i = 0; i < nums.length;i++){
            if(nums[i] == 0)
                sum -= 1;
            else
                sum += 1;
            if(map.containsKey(sum)){
                result = Math.max(result,i-map.get(sum));
            } else
                map.put(sum,i);
        }
        return result;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int findMaxLengthV1(int[] nums) {
        if(nums.length == 0 || nums.length == 1) return 0;
        int maxLength = 0;
        int[] arr = new int[2*nums.length+1];
        for( int i = 0; i < arr.length; i++)
            arr[i] = -2;
        arr[nums.length]=-1;
        int counter = nums.length;
        for(int i = 0; i < nums.length; i++){
            counter+=nums[i]*2-1;
            if(arr[counter] == -2){
                arr[counter] = i;
            }else{
                maxLength = Math.max(maxLength, i-arr[counter]);
            }
        }
        return maxLength;
    }
}
