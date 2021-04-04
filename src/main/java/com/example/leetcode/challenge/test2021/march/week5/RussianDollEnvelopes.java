package com.example.leetcode.challenge.test2021.march.week5;


import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * Return the maximum number of envelopes can you Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 *
 *
 *
 * Example 1:
 *
 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Example 2:
 *
 * Input: envelopes = [[1,1],[1,1],[1,1]]
 * Output: 1
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();
        russianDollEnvelopes.maxEnvelopes(new int[][]{
                {5,4},
                {6,4},
                {6,7},
                {2,3}
        });
    }

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if( index < 0 )
                index = -(index + 1);
            dp[index] = envelope[1];
            if( index == len )
                len++;
        }
        return len;
    }

    /**
     * another DP
     * @param envelopes
     * @return
     */
    public int maxEnvelopesV1(int[][] envelopes) {
        Arrays.sort(envelopes, (a,b) -> a[0] == b[0] ?
                Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0])); //!!!
        int[] arr = new int[envelopes.length];
        for(int i = 0; i < envelopes.length; i++){
            arr[i] = envelopes[i][1];
        }
        return lis(arr);
    }

    private int lis(int[] arr){
        int[] dp = new int[arr.length];
        int len = 0;
        for(int n : arr){
            int index = binSearch(dp, 0, len, n);
            dp[index] = n;
            if(index == len){
                len++;
            }
        }
        return len;
    }

    private int binSearch(int[] arr, int start, int end, int target){
        int mid = 0;
        while(start < end){
            mid = start + (end - start) / 2;
            if(arr[mid] == target){
                return mid;
            }
            if(arr[mid] > target){
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
