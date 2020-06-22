package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The set S originally contains numbers from 1 to n.
 * But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set,
 * which results in repetition of one number and loss of another number.
 *
 * Given an array nums representing the data status of this set after the error.
 * Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Note:
 * The given array size will in the range [2, 10000].
 * The given array's numbers won't have any order.
 *
 * Input
 * [3,2,2]
 * Output
 * [3,1,2,3]
 * Expected
 * [2,1]
 */
public class SetMismatch {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2};
        SetMismatch setMismatch = new SetMismatch();
        int[] result = setMismatch.findErrorNums(nums);
        System.out.println(result);
    }

    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int[] numTmp = new int[nums.length];
        List<Integer> listDup = new ArrayList<>();
        List<Integer> listLost = new ArrayList<>();
        for(int value : nums){
            numTmp[value-1]++;
        }
        for(int i = 1;i<=nums.length;i++){
            if(numTmp[i-1] > 1)
                listDup.add(i);
            if(numTmp[i-1] == 0)
                listLost.add(i);
        }
        listDup.addAll(listLost);
        return listDup.stream().mapToInt(e->e).toArray();
    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int[] findErrorNumsV2(int[] nums) {

        int a[]=new int[2];
        int []set=new int[nums.length+1];
        for(int i=1;i<nums.length+1;i++)
        {
            set[i]++;
        }
        int k=0;

        for(int i=0;i<nums.length;i++)
        {
            set[nums[i]]--;
            if(set[nums[i]]<0)
            {
                a[k]=nums[i];
                k++;
            }
        }
        for(int i=1;i<nums.length+1;i++)
        {
            if(set[i]>0)
            {
                a[k]=i;
                k++;
            }
        }


        return a;
    }

    public int[] findErrorNumsV3(int[] nums) {
        int[] res = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) res[0] = Math.abs(i);
            else nums[Math.abs(i) - 1] *= -1;
        }
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) res[1] = i+1;
        }
        return res;
    }

    /**
     * less memory
     * @param nums
     * @return
     */
    public int[] findErrorNumsV4(int[] nums) {
        int[] res = new int[2];
        for(int i = 0; i<nums.length; i++){
            int index = Math.abs(nums[i])-1;
            if(nums[index]<0){
                res[0] = index+1;
            }else{
                nums[index] = -nums[index];
            }
        }
        for(int i=0; i<nums.length; i++){
            if(nums[i]>0){
                res[1] = i+1;
            }
        }
        return res;
    }
}
