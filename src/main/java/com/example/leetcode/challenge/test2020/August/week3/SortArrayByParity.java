package com.example.leetcode.challenge.test2020.August.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 *
 * You may return any answer array that satisfies this condition.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 */
public class SortArrayByParity {
    public static void main(String[] args) {

    }

    public int[] sortArrayByParity(int[] A) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int val : A){
            if(val % 2 == 0){
                list1.add(val);
            } else {
                list2.add(val);
            }
        }
        list1.addAll(list2);
        return list1.stream().mapToInt(e->e).toArray();
    }
}
