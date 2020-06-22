package com.example.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *Given an array of integers arr, replace each element with its rank.
 *
 * The rank represents how large the element is. The rank has the following rules:
 *
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 *
 *
 * Example 1:
 *
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * Example 2:
 *
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * Example 3:
 *
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 */
public class RankTransformArray {

    public static void main(String[] args) {
        int[] arr = new int[]{40,10,20,30};
        RankTransformArray rankTransformArray = new RankTransformArray();
        rankTransformArray.arrayRankTransform(arr);
    }

    public int[] arrayRankTransform(int[] arr) {
        int[] result = Arrays.copyOf(arr,arr.length);
        Arrays.sort(arr);

        Map<Integer,Integer> map = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < arr.length; i++) {
            if(i>0&&arr[i]>arr[i-1])
                rank++;
            map.put(arr[i], rank);
        }

        for (int i = 0; i < arr.length; i++) {
            result[i] = map.get(result[i]);
        }
        return result;
    }


    /**
     * faster solution
     * @param arr
     * @return
     */
    public int[] arrayRankTransformV2(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int a : arr){
            if (a > max){
                max = a;
            }
            if (a < min){
                min = a;
            }
        }

        int[] rank = new int[max - min + 1];

        for (int a : arr){
            rank[a - min] = 1;
        }
        // 排名递增
        for (int i = 1; i < rank.length; i++){
            rank[i] += rank[i-1];
        }

        int len = arr.length;
        for (int i = 0; i < len; i++){
            arr[i] = rank[arr[i]-min];
        }

        return arr;
    }
}
