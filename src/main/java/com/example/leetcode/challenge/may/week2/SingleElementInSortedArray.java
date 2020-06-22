package com.example.leetcode.challenge.may.week2;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once. Find this single element that appears only once.
 *Example 1:
 *
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 */
public class SingleElementInSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3,3,7,7,10,11,11};
        SingleElementInSortedArray singleElementInSortedArray = new SingleElementInSortedArray();
        int result = singleElementInSortedArray.singleNonDuplicateV3(nums);
        System.out.println(result);
    }

    public int singleNonDuplicate(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length;) {
            if(( i!=0 && nums[i] == nums[i-1] ) || ( i!=nums.length - 1 && nums[i] == nums[i+1])){
                i++;
            } else {
                result = nums[i];
                break;
            }
        }
        return result;
    }


    /**
     * less memory
     * @param nums
     * @return
     */
    public int singleNonDuplicateV2(int[] nums) {
        int res = 0;
        for(int num : nums) {
            res ^= num;
        }
        return res;
    }

    public int singleNonDuplicateV3(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid = left+(right-left)/2;
            if(mid%2==1)
                mid--;
            if(nums[mid]==nums[mid+1])
                left = mid+2;
            else
                right = mid;
        }
        return nums[left];
    }

}
