package com.example.leetcode.weeklycontest.old.test238;


import java.util.Arrays;

/**
 * The frequency of an element is the number of times it occurs in an array.
 *
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
 *
 * Return the maximum possible frequency of an element after performing at most k operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 * Example 2:
 *
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
 * Example 3:
 *
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 *
 * [9930,9923,9983,9997,9934,9952,9945,9914,9985,9982,9970,9932,9985,9902,9975,9990,9922,9990,9994,9937,9996,9964,9943,9963,9911,9925,9935,9945,9933,9916,9930,9938,10000,9916,9911,9959,9957,9907,9913,9916,9993,9930,9975,9924,9988,9923,9910,9925,9977,9981,9927,9930,9927,9925,9923,9904,9928,9928,9986,9903,9985,9954,9938,9911,9952,9974,9926,9920,9972,9983,9973,9917,9995,9973,9977,9947,9936,9975,9954,9932,9964,9972,9935,9946,9966]
 * 3056
 *
 * [9953,9960,9908,9957,9919,9967,9941,9985,9925,9933,9989,9999,9928,9990,9973,9930,9982,9911,9986,9931,9925,9943,9937,9956,9968,9988,9929,9997,9945,9931,9922,9948,9916,9948,9998,9967,9945,9906,9914,9947,9997,9945,9923,9969,9903,9947,9938,9972,9969,9953,9926,9949,9997,9971,9913,9948,9910,9964,9900,9983,9945,9900,9951,9928,9984,9960,9903,9903,9983,9920,9909,9927,9987,9994,9987,9965,9941,9921,9914,9936,9979,9917,9965,9906,9942,9904,9920,9907,9922,9983,9970,9963,9941,9902,9968,9992,9994,9954,9904,9974,9914,9903,9934,10000,9991,9991,9986,9965,9980,9907,9911,9918,9993,9981,9986,9986,9944,9973,9918,9931,9974,9976,9958,9987,9942,9995,9970,9963,9901,9979,9995,9936,9959,9965,9905,9979,9927,9989,9926,9984,9956,9936,9931,9954,9901,9949,9943,9945,9966,9973,9931,9970,9916,9981,9995,9981,9968,9942,9960,10000,9935,9957,9931,9964,9939,9979,9924,9973,9960,9972,9915,9981,9993,9961,9963,9970,9917,9955,9993,9930,9972,9940,9921,9978,9915,9988,9904,9989,9911,9958,9914,9901,9913,9916,9909,9926,9928,9926,9920,9958,9931,9906,9973,9960,9909,9948,9983,9948,9936,9953,9974,9940]
 * 410
 *
 */
public class FrequencyMostFrequent {
    public static void main(String[] args) {
        FrequencyMostFrequent frequencyMostFrequent = new FrequencyMostFrequent();
        frequencyMostFrequent.maxFrequency(new int[]{3,9,6},2);
    }

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r){
            total += (long)(nums[r] - nums[r - 1]) * (r - l);
            while (total > k){
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
