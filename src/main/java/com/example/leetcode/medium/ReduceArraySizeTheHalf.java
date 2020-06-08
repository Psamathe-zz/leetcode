package com.example.leetcode.medium;

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
 */
public class ReduceArraySizeTheHalf {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        ReduceArraySizeTheHalf reduceArraySizeTheHalf = new ReduceArraySizeTheHalf();
        int result = reduceArraySizeTheHalf.minSetSize(arr);
        System.out.println(result);
    }

    public int minSetSize(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        int length = 0;
        int temp = 0;
        int result = 0;
        for(int value : arr){
            map.put(value,map.getOrDefault(value,0) + 1);
            length++;
        }
        List<Map.Entry> list = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
        for(Map.Entry<Integer,Integer> entry : list){
            temp += entry.getValue();
            result++;
            if(temp >= length/2){
                break;
            }
        }
        return result;
    }

    /**
     * faster solution
     * @param arr
     * @return
     */
    public int minSetSizeV1(int[] arr) {
        int max = 0;
        for (int n : arr)
            max = Math.max(max, n);

        int[] map = new int[max + 1];
        int maxFreq = 0;
        for (int n : arr) {
            map[n] += 1;
            maxFreq = Math.max(maxFreq, map[n]);
        }

        int[] count = new int[maxFreq + 1];
        for (int n : map) {
            if (n > 0) {
                count[n] += 1;
            }
        }

        int sum = 0;
        int res = 0;
        for (int i = maxFreq; i >= 0; i--) {
            while (count[i] > 0) {
                sum += i;
                res += 1;
                if(sum >= arr.length / 2){
                    return res;
                }
                count[i] -= 1;
            }
        }
        return res;
    }
}
