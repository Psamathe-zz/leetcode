package com.example.leetcode.weeklycontest.old.test268;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure to find the frequency of a given value in a given subarray.
 *
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 *
 * Implement the RangeFreqQuery class:
 *
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * Output
 * [null, 1, 2]
 *
 * Explanation
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
 * rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.
 *
 */
public class RangeFreqQuery {

    Map<Integer, List<Integer>> map;
    public RangeFreqQuery(int[] arr) {
        map = new HashMap<>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int finalI = i;
            map.compute(arr[i], (k, v) -> {
               if(v == null) {
                   List<Integer> list = new ArrayList<>();
                   list.add(finalI);
                   return list;
               } else {
                   v.add(finalI);
                   return v;
               }
            });
        }

    }

    public int query(int left, int right, int value) {
        int res = 0;
        if(map.containsKey(value)) {
            if (map.get(value).size() == 0)
                return 0;
            List<Integer> now = map.get(value);
            // 第一次二分找左端点下标
            int l = 0, r = now.size() - 1;
            while (l < r) {
                int mid = (r - l) / 2 + l;
                if (now.get(mid) < left) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            int a = l;
            // 不存在这样的左端点
            if (now.get(a) > right || now.get(a) < left)
                return 0;
            // 第二次二分，找右端点的下标
            l = a;
            r = now.size() - 1;
            while (l < r) {
                int mid = (r - l) / 2 + l;
                if (now.get(mid) < right) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            int b = l;
            if (now.get(b) > right) {
                b--;
            }
            return b - a + 1;
        }
        return res;
    }
}
