package com.example.leetcode.challenge.test2021.July.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * For some fixed n, an array nums is beautiful if it is a permutation of the integers 1, 2, ..., n, such that:
 *
 * For every i < j, there is no k with i < k < j such that nums[k] * 2 = nums[i] + nums[j].
 *
 * Given n, return any beautiful array nums.  (It is guaranteed that one exists.)
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: n = 5
 * Output: [3,1,2,5,4]
 *
 */
public class BeautifulArray {
    public static void main(String[] args) {
        BeautifulArray beautifulArray = new BeautifulArray();
        beautifulArray.beautifulArray(4);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/12287146.html
     * @param n
     * @return
     */
    public int[] beautifulArray(int n) {
        List<Integer> res = new ArrayList<>();
        List<Integer> temp;
        res.add(1);
        while (res.size() < n) {
            temp = new ArrayList<>();
            for (Integer val : res){
                if(val * 2 - 1 <= n)
                    temp.add(val * 2 - 1);
            }
            for (Integer val : res){
                if(val * 2  <= n)
                    temp.add(val * 2);
            }
            res = temp;
        }
        return res.stream().mapToInt(e -> e).toArray();
    }
}
