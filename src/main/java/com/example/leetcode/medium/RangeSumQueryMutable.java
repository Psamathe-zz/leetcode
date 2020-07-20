package com.example.leetcode.medium;

/**Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 The update(i, val) function modifies nums by updating the element at index i to val.

 Example:

 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 Note:

 The array is only modifiable by the update function.
 You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 */
public class RangeSumQueryMutable {
    public static void main(String[] args) {

    }

    int[] nums;
    public RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        if(i < 0 || i >= this.nums.length){
            return;
        } else {
            this.nums[i] = val;
        }
    }

    public int sumRange(int i, int j) {
        if(i < 0 || j >= this.nums.length){
            return -1;
        } else {
            int res = 0;
            for (int k = i; k <= j ; k++) {
                res += nums[k];
            }
            return res;
        }
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4985506.html
     */


    /**
     * faster solution
     */
    int[] fenwick;
    public RangeSumQueryMutable(int[] nums,int x) {
        this.nums = nums;
        fenwick = new int[nums.length+1];
        for(int i=0; i<nums.length; i++){
            updateInternal(i, nums[i]);
        }
    }

    private void updateInternal(int i, int val) {
        for(int n=i+1; n<fenwick.length; n += (n&-n)){
            fenwick[n] += val;
        }
    }

    public void updateV1(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        updateInternal(i, diff);
    }

    public int sumRangeV1(int i, int j) {
        return getSum(j) - getSum(i-1);
    }

    private int getSum(int i){
        int sum=0;
        for(int n=i+1; n>0; n -= (n&-n)){
            sum += fenwick[n];
        }
        return sum;
    }
}
