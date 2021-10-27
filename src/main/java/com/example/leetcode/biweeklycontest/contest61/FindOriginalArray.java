package com.example.leetcode.biweeklycontest.contest61;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 * Example 2:
 *
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 * Example 3:
 *
 * Input: changed = [1]
 * Output: []
 * Explanation: changed is not a doubled array.
 *[0,0,0,0]
 */
public class FindOriginalArray {
    public static void main(String[] args) {
        FindOriginalArray findOriginalArray = new FindOriginalArray();
        int[] res = findOriginalArray.findOriginalArray(new int[]{0,0,0,0});
        System.out.println(res);
    }

    public int[] findOriginalArray(int[] changed) {
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int val : changed){
            count.compute(val, (k, v ) -> {
               if(v == null)
                   return 1;
               else
                   return v + 1;
            });
        }

        List<Integer> res = new ArrayList<>();
        for (Integer key : count.keySet()){
            if(count.get(key) == 0)
                continue;
            if((key == 0 && count.get(key) % 2 == 1) || (count.get(key) > count.getOrDefault(key * 2,0))) {
                return new int[]{};
            }

            for (int i = 0; i < (key == 0?count.get(key)/2:count.get(key)); i++) {
                res.add(key);
            }
            count.put(key * 2, count.get(key * 2) - count.get(key));
            count.put(key, 0);
        }

        for (Integer key : count.keySet()){
            if(count.get(key) > 0)
                return new int[]{};
        }

        return res.stream().mapToInt(e -> e).toArray();
    }
}
