package com.example.leetcode.medium;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 *
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 *
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,4,2,2};
        FindTheDuplicateNumber findTheDuplicateNumber = new FindTheDuplicateNumber();
        int result = findTheDuplicateNumber.findDuplicateV1(nums);
        System.out.println(result);
    }

    public int findDuplicate(int[] nums) {
        int i;
        int j;
        boolean find = false;
        int length = nums.length;
        for ( i = 0; i < length && !find; i++) {
            for ( j = i + 1; j < length  && !find; j++) {
                if(nums[i] == nums[j]){
                    find = true;
                }
            }
        }
        return nums[i - 1];
    }


    /**
     * faster solution
     * @param nums
     * @return
     */
    public int findDuplicateV1(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        fast = nums[fast];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public int findDuplicateV2(int[] nums) {
        if(nums.length==0)
            return -1;
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=i+1){

                if(nums[nums[i]-1]==nums[i])
                    return nums[i];
                int temp=nums[i];
                nums[i]=nums[temp-1];
                nums[temp-1]=temp;
            }
        }

        return -1;
    }
}
