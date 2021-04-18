package com.example.leetcode.weeklycontest.test237;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The XOR sum of a list is the bitwise XOR of all its elements. If the list only contains one element, then its XOR sum will be equal to this element.
 *
 * For example, the XOR sum of [1,2,3,4] is equal to 1 XOR 2 XOR 3 XOR 4 = 4, and the XOR sum of [3] is equal to 3.
 * You are given two 0-indexed arrays arr1 and arr2 that consist only of non-negative integers.
 *
 * Consider the list containing the result of arr1[i] AND arr2[j] (bitwise AND) for every (i, j) pair where 0 <= i < arr1.length and 0 <= j < arr2.length.
 *
 * Return the XOR sum of the aforementioned list.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3], arr2 = [6,5]
 * Output: 0
 * Explanation: The list = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6, 3 AND 5] = [0,1,2,0,2,1].
 * The XOR sum = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0.
 * Example 2:
 *
 * Input: arr1 = [12], arr2 = [4]
 * Output: 4
 * Explanation: The list = [12 AND 4] = [4]. The XOR sum = 4.
 *
 */
public class FindXORSum {
    public static void main(String[] args) {
        FindXORSum findXORSum = new FindXORSum();
        findXORSum.getXORSum(new int[]{12},new int[]{4});
    }

    public int getXORSum(int[] arr1, int[] arr2) {
        Map<Integer,Integer> map1 = new HashMap<>();
        Map<Integer,Integer> map2 = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int val : arr1){
            map1.compute(val,(k,v) -> {
                if(v == null || v==0)
                    return 1;
                else
                    return 0;
            });
        }
        for (int val : arr2){
            map2.compute(val,(k,v) -> {
                if(v == null || v==0)
                    return 1;
                else
                    return 0;
            });
        }
        for (Map.Entry<Integer,Integer> entry : map1.entrySet()){
            if(entry.getValue() == 1)
                list1.add(entry.getKey());
        }
        for (Map.Entry<Integer,Integer> entry : map2.entrySet()){
            if(entry.getValue() == 1)
                list2.add(entry.getKey());
        }
        int res = 0;
        for (int val1:list1){
            for (int val2:list2){
                res ^= val1 & val2;
            }
        }
        return res;
    }

    /**
     * faster solution
     * @param arr1
     * @param arr2
     * @return
     */
    public int getXORSumV0(int[] arr1, int[] arr2) {
        int xor1 = 0, xor2 = 0;
        for (int a: arr1) xor1 ^= a;
        for (int a: arr2) xor2 ^= a;
        return xor1 & xor2;
    }
}
