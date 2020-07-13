package com.example.leetcode.medium;

/**
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 *
 *
 * Note:
 *
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 */
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        int[] A = new int[]{1,15,7,9,2,5,10};
        int K = 3;
        PartitionArrayForMaximumSum partitionArrayForMaximumSum = new PartitionArrayForMaximumSum();
        partitionArrayForMaximumSum.maxSumAfterPartitioning(A,K);
    }

    /**
     * https://blog.csdn.net/weixin_45744426/article/details/102890207
     * https://blog.csdn.net/u013383813/article/details/90146701
     *
     * dp[i]=max
     * dp[i−1]+A[i−1]∗1
     * dp[i−2]+max{A[i−2],A[i−1]}∗2
     * dp[i−3]+max{[A[i−3],A[i−2],A[i−1]}∗3
     * ​
     * @param A
     * @param K
     * @return
     */
    public int maxSumAfterPartitioning(int[] A, int K) {
        int length = A.length;
        int[] dp = new int[length + 1];
        int temp;
        int value;
        for (int i = 1; i <= length; i++) {
            value = Integer.MIN_VALUE;
            for (int j = 1; j <= Math.min(K,i); j++) {
                temp = Integer.MIN_VALUE;
                for (int k = i-1; k >= i-j; k--) {
                    temp = Math.max(temp,A[k]);
                }
                value = Math.max(value,dp[i-j] + temp * j);
            }
            dp[i] = value;
        }
        return dp[length];
    }

    /**
     *             int tMax = 0;
     *             for(int j = i - 1; i - j <= K && j>=0;j--){
     *                 tMax = Math.max(tMax,A[j]);
     *                 dp[i] = Math.max(dp[i],dp[j] + tMax *(i-j));
     *             }
     *
     * dp[i] = Max( 从当前位置i 往前 取一个子数组A[j:i] ，将该子数组中的所有元素都 置换为 子数组中最大值后，求该子数组的和tMax *(i-j)， tMax *(i-j) + dp[j])
     * 其中 i - j<= K && j >= 0
     *
     * @param A
     * @param K
     * @return
     */
    public int maxSumAfterPartitioningV1(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            int curMax = 0;
            for(int j = 1; j <= K; j++) {
                if((i-j)>=0){
                    curMax = Math.max(curMax, A[i-j]);
                    dp[i] = Math.max(dp[i], dp[i-j] + curMax*j);
                }
            }
        }
        return dp[n];
    }


    /**
     * faster solution
     * @param A
     * @param K
     * @return
     */
    public int maxSumAfterPartitioningV2(int[] A, int K) {
        // Subproblem here is DP[i] which means max sum of partitioned array at i
        // Now since we can partition upto K elements we will start with 0-K partition
        // then for every i > k we will see where we can parition it to increase the max
        int[] dp = new int[A.length + 1];
        int max = A[0];
        int maxSum = A[0];

        for(int i = 0; i < K; i++){
            max = Math.max(max, A[i]);
            dp[i + 1] = max * (i + 1);
            maxSum = Math.max(maxSum, dp[i + 1]);
        }


        for(int i = K; i < A.length; i++){
            int j = 0;
            max = A[i];
            for(j = 0; j < K; j++){
                max = Math.max(A[i - j], max);
                dp[i + 1] = Math.max(max * (j + 1) + dp[i - j], dp[i + 1]);
            }
            maxSum = Math.max(maxSum, dp[i + 1]);
        }

        return maxSum;
    }
}
