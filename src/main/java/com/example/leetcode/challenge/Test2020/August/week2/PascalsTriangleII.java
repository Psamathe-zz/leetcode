package com.example.leetcode.challenge.August.week2;

import java.util.ArrayList;
import java.util.List;

/**
 *Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangleII {
    public static void main(String[] args) {
        int rowIndex = 3;
        PascalsTriangleII pascalsTriangleII = new PascalsTriangleII();
        List<Integer>  res = pascalsTriangleII.getRow(rowIndex);
        System.out.printf("", res);
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if(rowIndex == 0){
            res.add(1);
            return res;
        }
        List<Integer> temp = getRow(rowIndex - 1);
        res.add(1);
        for (int i = 1; i < temp.size(); i++) {
            res.add(temp.get(i-1) + temp.get(i));
        }
        res.add(1);

        return res;
    }
}
