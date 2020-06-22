package com.example.leetcode.hard;

import java.util.Arrays;

/**
 * Given an array of integers A, consider all non-empty subsequences of A.
 *
 * For any sequence S, let the width of S be the difference between the maximum and minimum element of S.
 *
 * Return the sum of the widths of all subsequences of A.
 *
 * As the answer may be very large, return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,3]
 * Output: 6
 * Explanation:
 * Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
 * The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 * The sum of these widths is 6.
 *
 * https://www.cnblogs.com/grandyang/p/11020261.html
 *
 * 在解题之前，我们首先要知道的是一个长度为n的数组，共有多少个子序列，如果算上空集的话，共有 2^n 个。
 * 那么在给数组排序之后，对于其中任意一个数字 A[i]，其前面共有i个数是小于等于 A[i] 的，这i个数字共有 2^i 个子序列，
 * 它们加上 A[i] 都可以组成一个新的非空子序列，并且 A[i] 是这里面最大的数字，那么在宽度计算的时候，就要加上 A[i] x (2^i)
 *
 * A[i] 后面还有 n-1-i 个数字是大于等于它的，后面可以形成 2^(n-1-i) 个子序列，
 * 每个加上 A[i] 就都是一个新的非空子序列，同时 A[i] 是这些子序列中最小的一个，那么结果中就要减去 A[i] x (2 ^ (n-1-i))。
 * *
 * 对于每个数字都这么计算一下，就是最终要求的所有子序列的宽度之和了。可能你会怀疑虽然加上了 A[i] 前面 2^i 个子序列的最大值，
 * 那些子序列的最小值减去了么？其实是减去了的，虽然不是在遍历 A[i] 的时候减去，在遍历之前的数字时已经将所有该数字是子序列最小值的情况减去了，
 * 同理，A[i] 后面的那些 2^(n-1-i) 个子序列的最大值也是在遍历到的时候才加上的，所以不会漏掉任何一个数字。
 *
 * 。最后，由于这种机制下的 2^i 和 2^(n-1-i) 不方便同时计算，这里又用了一个 trick，就是将 A[i] x (2^(n-1-i)) 转换为了 A[n-1-i] x 2^i，其实二者最终的累加和是相等的：
 * sum(A[i] * 2^(n-1-i)) = A[0]*2^(n-1) + A[1]*2^(n-2) + A[2]*2^(n-3) + ... + A[n-1]*2^0
 * sum(A[n-1-i] * 2^i) = A[n-1]*2^0 + A[n-2]*2^1 + ... + A[1]*2^(n-2) + A[0]*2^(n-1)
 *
 * A[i] x (2^i) - A[i] x (2 ^ (n-1-i))  => A[i] x (2^i) - A[n-1-i] * 2^i
 */
public class SumSubsequenceWidths {

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,3};
        SumSubsequenceWidths sumSubsequenceWidths = new SumSubsequenceWidths();
        int result = sumSubsequenceWidths.sumSubseqWidths(nums);
        System.out.println(result);
    }
    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        long res = 0;
        long n = A.length;
        long M = (long)Math.pow(10,9) + 7;
        long c = 1;
        for (int i = 0; i < n; ++i) {
            res = (res + A[i] * c - A[(int)(n - i - 1)] * c) % M;
            c = (c << 1) % M;
        }
        return (int)res;

    }



    // counting sort can be further improved by finding the min to save space of int[]arr.
    public int sumSubseqWidthsV2(int[] A) {

        int max=-1;
        for(int i:A)max=Math.max(max,i);
        int[]arr=new int[max+1];
        for(int i:A)arr[i]++;

        long res=0;
        long mod=1000000007;
        long c=1;
        long offset=max*mod;

        for(int i=0;i<=max;i++){
            for(int j=arr[i];j>0;j--){
                res=(res+c*i)%mod;
                c=(c<<1)%mod;
            }
        }
        //System.out.println(res);

        c=1;
        for(int i=max;i>=0;i--){
            for(int j=arr[i];j>0;j--){
                res=(res-c*i+offset)%mod;
                c=(c<<1)%mod;
            }
        }

        return (int)res;

    }


    public int sumSubseqWidthsV3(int[] A) {

        Arrays.sort(A);
        int len=A.length;
        long res=0;
        long mod=1000000007;
        long c=1;

        for(int i=0;i<len;i++){
            res=(res+c*A[i]-c*A[len-i-1])%mod;
            c=(c<<1)%mod;
        }
        return (int)res;
    }


}
