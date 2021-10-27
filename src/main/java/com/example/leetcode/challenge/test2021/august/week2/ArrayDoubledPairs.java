package com.example.leetcode.challenge.test2021.august.week2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of integers arr of even length, return true if and only if it is possible to reorder it such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,3,6]
 * Output: false
 * Example 2:
 *
 * Input: arr = [2,1,2,6]
 * Output: false
 * Example 3:
 *
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 *
 * Input: arr = [1,2,4,16,8,4]
 * Output: false
 */
public class ArrayDoubledPairs {
    public static void main(String[] args) {
        ArrayDoubledPairs arrayDoubledPairs = new ArrayDoubledPairs();
        boolean res = arrayDoubledPairs.canReorderDoubled(new int[]{0,0});
        System.out.println(res);
    }

    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int val : arr){
            count.compute(val, (k,v) -> {
                if(v == null)
                    return 1;
                else
                    return v + 1;
            });
        }
        List<Integer> list = count.keySet().stream().sorted(Comparator.comparingInt(Math::abs)).collect(Collectors.toList());

        int val;
        int valDouble;
        for (Integer key : list){
            if(count.containsKey(key)){
                val = count.get(key);
                valDouble = count.getOrDefault(key * 2, -1);
                if(valDouble > 0 && valDouble >= val ) {
                    if(valDouble - val > 0)
                        count.put(key * 2, valDouble - val);
                    else
                        count.remove(key * 2);
                } else
                    return false;
                count.remove(key);
            }
        }

        return count.isEmpty();
    }
}
