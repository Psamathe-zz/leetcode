package com.example.leetcode.challenge.test2021.July.week1;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
 * Example 2:
 *
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 * Example 3:
 *
 * Input: arr = [1,9]
 * Output: 1
 * Example 4:
 *
 * Input: arr = [1000,1000,3,7]
 * Output: 1
 * Example 5:
 *
 * Input: arr = [1,2,3,4,5,6,7,8,9,10]
 * Output: 5
 * [3,3,3,3,5,5,5,2,2,7]
 */
public class ReduceArraySize {
    public static void main(String[] args) {
        ReduceArraySize reduceArraySize = new ReduceArraySize();
        int res = reduceArraySize.minSetSize(new int[]{3,3,3,3,5,5,5,2,2,7});
        System.out.println(res);
    }

    public int minSetSize(int[] arr) {
        int length = arr.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int val : arr){
            map.compute(val,(k , v) -> v==null?1:v+1);
        }
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        int sum = 0;
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList())){
            sum += entry.getValue();
            res++;
            if(sum * 2 >= length)
                break;
        }
        return res;
    }
}
