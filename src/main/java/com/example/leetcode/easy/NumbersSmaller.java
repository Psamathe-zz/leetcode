package com.example.leetcode.easy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 *
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 *
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class NumbersSmaller {
    public static void main(String[] args) {

    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int val:nums){
            map.put(val,map.getOrDefault(val,0) + 1);
        }
        int temp = 0;
        int t;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            t = entry.getValue();
            entry.setValue(temp);
            temp += t;
        }
        for (int i = 0; i < length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrentV1(int[] nums) {

        int[] db = new int[101];
        for(int num : nums)
            db[num]++;

        for(int i =1;i<db.length;i++){
            db[i] +=db[i-1];
        }


        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i] = (nums[i] !=0) ?  db[nums[i]-1] : 0;
        }

        return res;

    }
}
