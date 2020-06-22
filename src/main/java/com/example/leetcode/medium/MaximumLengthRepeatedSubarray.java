package com.example.leetcode.medium;

import java.util.HashSet;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 *
 * Example 1:
 *
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 * 1,2,3,2,1,1,2,3,2,1
 *     3,2,1,4,7
 */
public class MaximumLengthRepeatedSubarray {
    public static void main(String[] args) {
        int[] A = new int[]{1,2,3,2,1};
        int[] B = new int[]{3,2,1,4,7};
        MaximumLengthRepeatedSubarray maximumLengthRepeatedSubarray = new MaximumLengthRepeatedSubarray();
        int result = maximumLengthRepeatedSubarray.findLengthV2(A,B);
        System.out.println(result);
    }

    public int findLength(int[] A, int[] B) {
        int result = 0;
        int u = A.length;
        int v = B.length;
        int[][] dp = new int[v][u];
        for(int i = 0;i < v; i++){
            for(int j = 0;j < u; j++){
                if(dp[i][j] != 1 && B[i] == A[j]){
                    dp[i][j] = 1;
                    int temp = 1;
                    int x = i + 1;
                    int y = j + 1;
                    while (x<v && y<u && B[x] == A[y] ) {
                        dp[x][y] = 1;
                        temp++;
                        x++;
                        y++;
                    }
                    if(result<temp)
                        result = temp;
                }
            }
        }
        return result;
    }

    /**
     * faster solution
     * @param A
     * @param B
     * @return
     * TODO
     */
    public int findLengthV2(int[] A, int[] B) {
        int la = A.length;
        int lb = B.length;

        int p = 119;
        int len = Math.max(la, lb)+1;
        int[] ps = new int[len];
        ps[0] = 1;

        for(int i = 1; i < len; i++) {
            ps[i] = ps[i-1]*p;
        }

        int[] hashA = new int[la+1];
        for(int i = 1; i <= la; i++) {
            hashA[i] = hashA[i-1] + A[i-1] * ps[i];
        }

        int[] hashB = new int[lb+1];
        for(int i = 1; i <= lb; i++) {
            hashB[i] = hashB[i-1] + B[i-1]*ps[i];
        }

        int lo = 1;
        int hi = Math.min(la, lb);
        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            HashSet<Integer> set = new HashSet<>();
            for(int i = 1; i+mid-1 <= la; i++) {
                int hashVal = (hashA[i+mid-1]-hashA[i-1])*ps[len-mid-i+1];
                set.add(hashVal);
            }
            boolean found = false;
            for(int i = 1; i+mid-1 <= lb; i++) {
                int hashVal = (hashB[i+mid-1]-hashB[i-1])*ps[len-mid-i+1];
                if(set.contains(hashVal)) {
                    found = true;
                    break;
                }
            }

            if(found) {
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }

        return hi;
    }
}
