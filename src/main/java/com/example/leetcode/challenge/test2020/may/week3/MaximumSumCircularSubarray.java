package com.example.leetcode.challenge.test2020.may.week3;

/**
 * https://zh.wikipedia.org/wiki/%E6%9C%80%E5%A4%A7%E5%AD%90%E6%95%B0%E5%88%97%E9%97%AE%E9%A2%98
 *
 * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
 *
 * Here, a circular array means the end of the array connects to the beginning of the array.
 * (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
 *
 * Also, a subarray may only include each element of the fixed buffer A at most once.
 * (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 * Example 2:
 *
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 * Example 3:
 *
 * Input: [3,-1,2,-1]
 * Output: 4
 * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
 * Example 4:
 *
 * Input: [3,-2,2,-3]
 * Output: 3
 * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
 * 3,-2,2,-3,3,-2,2,-3
 * Example 5:
 *
 * Input: [-2,-3,-1]
 * Output: -1
 * Explanation: Subarray [-1] has maximum sum -1
 */
public class MaximumSumCircularSubarray {
    public static void main(String[] args) {
        int[] A = new int[]{5,-3,5};
        MaximumSumCircularSubarray maximumSumCircularSubarray = new MaximumSumCircularSubarray();
        int result = maximumSumCircularSubarray.maxSubarraySumCircularV2(A);
        System.out.println(result);
    }

    public int maxSubarraySumCircular(int[] A) {
        int curmax = -30000*30000;
        int submax = -30000*30000;
        int curmin = 30000*30000;
        int submin = 30000*30000;
        int total = 0;

        for(Integer value : A){
            curmax = Math.max(value,curmax + value);
            submax = Math.max(submax,curmax);
            curmin = Math.min(value,curmin + value);
            submin = Math.min(submin,curmin);
            total += value;
        }

        if (submax < 0)
            return submax;
        else
            return Math.max(submax, total-submin);

    }


    /**
     * faster solution
     * @param A
     * @return
     */
    public int maxSubarraySumCircularV2(int[] A) {
        int temp1 = kadane(A);
        //System.out.println(temp1);
        int sum=0;
        for(int i=0; i<A.length; i++){
            sum += A[i];
            A[i] = -A[i];
        }

        int temp2 =kadane(A);
        sum = sum + kadane(A);
        //System.out.println(sum);
        if(sum==0){
            return temp1;
        }

        return Math.max(temp1, sum);
    }

    public int kadane(int[] a){
        int max_so_far=a[0], max_ending=a[0];
        for(int i=1; i<a.length; i++){
            max_ending = Math.max(a[i], max_ending + a[i]);
            max_so_far = Math.max(max_so_far, max_ending);
        }
        return max_so_far;
    }

    public int maxSubarraySumCircularOld(int[] A) {
        int result = Integer.MIN_VALUE;
        int[] temp = new int[A.length * 2];
        for(int i= 0; i<A.length;i++){
            temp[i] = A[i];
            temp[i + A.length] = A[i];
        }

        for (int i = 0; i < A.length; i++) {
            int t = 0;
            for (int j = i; j < i + A.length; j++) {
                t += temp[j];
                if(t > result)
                    result = t;
            }
        }

        return result;
    }

}
