package com.example.leetcode.challenge.test2020.april.week2;

import java.util.HashMap;

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
 * 000000000000001111
 */
public class ContiguousArray {
    public static void main(String[] args) {

        int[] input = new int[]{0,1};
        ContiguousArray contiguousArray = new ContiguousArray();
        int result = contiguousArray.findMaxLength(input);
        System.out.println(result);
    }

    public int findMaxLength(int[] nums) {

        HashMap<Integer, Integer> diffToEndIndexMap = new HashMap<>();
        int maxLen = 0;
        int diff = 0;
        diffToEndIndexMap.put(0, -1);
        for(int i=0; i<nums.length; i++){
            //Update diff
            if(nums[i]==1){
                diff++;
            }
            else{
                diff--;
            }
            //Check if we encountered currentdiff previously
            if(diffToEndIndexMap.containsKey(diff)){            //Current diff was encountered previously
                int len = i - diffToEndIndexMap.get(diff);      //Calculate length of the in-between subarray
                maxLen = Math.max(maxLen, len);                 //Check and update max length
            }
            else{                                               //Current diff was not encountered previously
                diffToEndIndexMap.put(diff, i);                 //Store current diff and current index
            }
        }
        return maxLen;
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int findMaxLengthV2(int[] nums) {

        int n = nums.length;
        int[] recorder = new int[2 * nums.length + 1];

        for( int i = 0; i < recorder.length; i++)
            recorder[i] = -2;

        int sum = n;
        int max = 0;
        recorder[n] = -1;

        for ( int i = 0; i < nums.length; i++){
            sum += (nums[i] * 2 - 1);
            if (recorder[sum] == -2)
                recorder[sum] = i;
            else
                max = Math.max(max, i - recorder[sum]);
        }
        return max;
    }
}
