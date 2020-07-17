package com.example.leetcode.medium;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        int[] A = new int[]{4,5,0,-2,-3,1};
        int K = 5;
        SubarraySumsDivisibleByK subarraySumsDivisibleByK = new SubarraySumsDivisibleByK();
        subarraySumsDivisibleByK.subarraysDivByKV1(A,K);
    }


    public int subarraysDivByK(int[] A, int K) {
        int length = A.length;
        int[] dp = new int[length];
        int sum;
        int count;
        int res = 0;
        for (int i = length - 1; i >= 0; i--) {
            sum = 0;
            count = 0;
            for (int j = i; j < length; j++) {
                if(sum % K == 0 && dp[j] != 0){
                    count = 1 + dp[j];
                    break;
                }
                sum += A[j];
                if(sum % K == 0)
                    count++;
            }
            dp[i] = count;
            res += dp[i];
        }
        return res;
    }


    /**
     * faster solution
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByKV1(int[] A, int K) {
        int[] map = new int[K];
        map[0] = 1;
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            while(sum < 0)
                sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map[sum];
            map[sum]++;
        }
        return count;
    }
}
