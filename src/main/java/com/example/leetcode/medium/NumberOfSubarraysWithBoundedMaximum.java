package com.example.leetcode.medium;

/**
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 *
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 *
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 *
 * L, R  and A[i] will be an integer in the range [0, 10^9].
 * The length of A will be in the range of [1, 50000].
 *
 * [73,55,36,5,55,14,9,7,72,52]
 * 32
 * 69
 *
 * https://www.cnblogs.com/grandyang/p/9237967.html
 */
public class NumberOfSubarraysWithBoundedMaximum {
    public static void main(String[] args) {
        int[] A = new int[]{73,55,36,5,55,14,9,7,72,52};
        int L = 32;
        int R = 69;
        NumberOfSubarraysWithBoundedMaximum numberOfSubarraysWithBoundedMaximum = new NumberOfSubarraysWithBoundedMaximum();
        int result = numberOfSubarraysWithBoundedMaximum.numSubarrayBoundedMax(A,L,R);
        System.out.println(result);
    }

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int result = 0;
        int length = A.length;
        int max;
        int j;
        for (int i = 0; i < length; i++) {
            j = i;
            max = Integer.MIN_VALUE;
            while (j < length){
                max = Math.max(A[j],max);
                if(max > R ){
                    break;
                }
                j++;
                if(max >= L ) {
                    result++;
                }
            }
        }
        return result;
    }


    /**
     * faster solution
     * @param A
     * @param L
     * @param R
     * @return
     */
    public int numSubarrayBoundedMaxV1(int[] A, int L, int R) {
        int prev = 0;
        int res = 0;
        int n = A.length;

        int left = 0;
        for (int i = 0; i < n; i++) {
            int num = A[i];
            if (num >= L && num <= R) {
                res += i - left + 1;
                prev = i - left + 1;
            } else if (num < L) {
                res += prev;
            } else {
                prev = 0;
                left = i + 1;
            }
        }
        return res;
    }
}
