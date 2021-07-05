package com.example.leetcode.challenge.test2021.june.week3;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        PascalsTriangle pascalsTriangle = new PascalsTriangle();
        pascalsTriangle.generate(5);
    }

    public List<List<Integer>> generate(int numRows) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp;
        int pre;
        int val;
        for (int i = 0; i < numRows; i++) {
            temp = new ArrayList<>();
            pre = 0;
            for (int j = 0; j <= i ; j++) {
                val = queue.poll();
                temp.add(val);
                queue.offer(pre + val);
                pre = val;
            }
            queue.offer(pre);
            res.add(temp);
        }
        return res;
    }
}
