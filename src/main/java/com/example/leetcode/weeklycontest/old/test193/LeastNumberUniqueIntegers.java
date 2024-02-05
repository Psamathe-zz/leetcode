package com.example.leetcode.weeklycontest.old.test193;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 */
public class LeastNumberUniqueIntegers {
    public static void main(String[] args) {
        int[] nums = new int[]{4,3,1,1,3,3,2};
        int k = 3;
        LeastNumberUniqueIntegers leastNumberUniqueIntegers = new LeastNumberUniqueIntegers();
        leastNumberUniqueIntegers.findLeastNumOfUniqueIntsV1(nums,k);
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;

        for(int value : arr){
            map.put(value,map.getOrDefault(value,0) + 1);
        }

        List<Map.Entry<Integer,Integer>> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        for (Map.Entry<Integer,Integer> entry : list){
            k -= entry.getValue();
            count++;
            if(k <= 0){
                if(k<0)
                    count--;
                break;
            }
        }
        return map.size() - count;
    }


    /**
     * faster solution
     */
    public int findLeastNumOfUniqueIntsV1(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int a : arr)
            freq.put(a, freq.getOrDefault(a, 0) + 1);
        int[] f = new int[arr.length + 1];
        for(int key : freq.keySet())
            f[freq.get(key)]++;
        int count = 0;
        for(int i=1; i<=arr.length; i++){
            if(k > 0){
                if(i*f[i] <= k){
                    k = k - i*f[i];
                    f[i] = 0;
                } else {
                    f[i] = f[i] - (k/i);
                    k = 0;
                }
            }
            count += f[i];
        }
        return count;
    }
}
