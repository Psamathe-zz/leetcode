package com.example.leetcode.weeklycontest.test230;


import java.util.Arrays;

/**
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values in the arrays are between 1 and 6, inclusive.
 *
 * In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.
 *
 * Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2. Return -1​​​​​ if it is not possible to make the sum of the two arrays equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
 * - Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
 * - Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
 * Example 2:
 *
 * Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * Output: -1
 * Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
 * Example 3:
 *
 * Input: nums1 = [6,6], nums2 = [1]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
 * - Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
 * - Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 */
public class EqualSumArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{6,6};
        int[] nums2 = new int[]{1};
        EqualSumArrays equalSumArrays = new EqualSumArrays();
        int res = equalSumArrays.minOperations(nums1,nums2);
        System.out.println(res);
    }

    /**
     * https://leetcode-cn.com/problems/equal-sum-arrays-with-minimum-number-of-operations/solution/javatan-xin-suan-fa-xiang-xi-zhu-shi-by-wak7i/
     * @param nums1
     * @param nums2
     * @return
     */
    public int minOperations(int[] nums1, int[] nums2) {
        //存储两个数组中每个数字出现的次数
        int[] cnt1 = new int[6];
        int[] cnt2 = new int[6];
        //sum1代表nums1的所有数字之和，sum2代表nums2的所有数字之和。
        int sum1 = 0, sum2 = 0;

        //记录nums1中每个数字出现的次数，并记录sum1。
        for(int num1 : nums1){
            cnt1[num1 - 1] += 1;
            sum1 += num1;
        }

        //记录nums2中每个数字出现的次数，并记录sum2。
        for(int num2 : nums2){
            cnt2[num2 - 1] += 1;
            sum2 += num2;
        }

        if(sum1 == sum2){
            return 0;
        }else if(sum1 > sum2){
            return getCount(cnt1, cnt2, sum1, sum2); // sum1 > sum2 的情况
        }

        //sum1 < sum2 的情况
        return getCount(cnt2, cnt1, sum2, sum1);
    }

    //cnt1，sum1总是代表总和最大的数组的相关数据，cnt2，sum2总是代表总和最小的数组的相关数据
    public int getCount(int[] cnt1, int []cnt2, int sum1, int sum2){
        //需要操作的最小次数
        int count = 0;
        //主循环
        while(sum1 > sum2){
            //当前循环中两个数组的和之差
            int sub = sum1 - sum2;

            //贪心地选择可以操作的数组。
            int cur1 = 5, cur2 = 0;
            while(cnt1[cur1] == 0){
                cur1--;
            }
            while(cnt2[cur2] == 0){
                cur2++;
            }

            //两个数组都不可以再进行操作，所以无法使两个数组的和相等，返回-1。
            if(cur1 == 0 && cur2 == 5)
                return -1;

            if((5-cur1) <= cur2){ //本次循环最优操作为：操作总和最大的数组
                //将需要操作的数的出现次数减1。
                cnt1[cur1]--;
                //将操作得到的数的出现次数加1。
                if(sub - cur1 > 0){//当两个数组之差大于能做的最大操作时
                    cnt1[0]++;
                    sum1 = sum1 - cur1;
                }else{
                    cnt1[cur1-sub]++;
                    sum1 = sum1 - sub;
                }
            }else{ //本次循环最优操作为：操作总和最小的数组
                //将需要操作的数的出现次数减1。
                cnt2[cur2]--;
                //将操作得到的数的出现次数加1。
                if(sub > (5-cur2)){
                    cnt2[5]++;
                    sum2 = sum2 + (5-cur2);
                }else{
                    cnt2[cur2 + sub]++;
                    sum2 = sum2 + sub;
                }
            }
            //操作次数加1
            count++;
        }
        return count;
    }
}
