package com.example.leetcode.weeklycontest.test188;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr.
 *
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 *
 * Let's define a and b as follows:
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 *
 * Return the number of triplets (i, j and k) Where a == b.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 * Example 2:
 *
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 * Example 3:
 *
 * Input: arr = [2,3]
 * Output: 0
 * Example 4:
 *
 * Input: arr = [1,3,5,7,9]
 * Output: 3
 * Example 5:
 *
 * Input: arr = [7,11,12,9,5,2,7,17,22]
 * Output: 8
 *           异或
 *          如果相对应位值相同，则结果为0，否则为1
 *          对一个数异或两次等于没有异或
 *          a ^ b ^ b = a;
 *          x ^ x = 0
 *          0 ^ x = x.
 */
public class CountTriplets {
    public static void main(String[] args) {
        int[] arr = new int[]{7,11,12,9,5,2,7,17,22};
        CountTriplets countTriplets = new CountTriplets();
        int result = countTriplets.countTriplets(arr);
        System.out.println(result);
    }

    public int countTriplets(int[] arr) {
        int result = 0;
        if(arr.length <= 1)
            return result;

        int indexI;
        int indexJ;
        int indexK;

        for(indexI = 0; indexI < arr.length - 1; indexI++){
            for(indexJ = indexI + 1; indexJ < arr.length; indexJ++){
                for(indexK = indexJ; indexK < arr.length; indexK++){
                    if(getValue(arr,indexI,indexJ) == getValue(arr,indexJ,indexK+1)){
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public int getValue(int[] arr, int start, int end){
        int value = arr[start];
        for(int i = start + 1; i< end;i++){
            value ^= arr[i];
        }
        return value;
    }


    /**
     * faster solution
     * @param arr
     * @return
     */
    public int countTripletsV2(int[] arr) {
        int[] pre = new int[arr.length+1];
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i-1] ^ arr[i-1];
        }

        int count = 0;
        for (int i = 0; i < pre.length; i++) {
            for (int j = i+1; j < pre.length; j++) {
                if ((pre[j] ^ pre[i]) == 0) {
                    count += (j-i-1);
                }
            }
        }

        return count;
    }


    public int countTripletsV3(int[] arr) {
        Map<Integer, Integer> xorToIdxSum = new HashMap<>();
        Map<Integer, Integer> xorToCount = new HashMap<>();
        xorToIdxSum.put(0, -1);
        xorToCount.put(0, 1);
        int ans = 0;
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (xorToIdxSum.containsKey(xor)) {
                ans += (i - 1) * xorToCount.get(xor) - xorToIdxSum.get(xor);
            }
            xorToIdxSum.put(xor, xorToIdxSum.getOrDefault(xor, 0) + i);
            xorToCount.put(xor, xorToCount.getOrDefault(xor, 0) + 1);
        }
        return ans;
    }
}
