package com.example.leetcode.weeklycontest.test256;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.
 *
 * Return the string that represents the kth largest integer in nums.
 *
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["3","6","7","10"], k = 4
 * Output: "3"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
 * The 4th largest integer in nums is "3".
 * Example 2:
 *
 * Input: nums = ["2","21","12","1"], k = 3
 * Output: "2"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
 * The 3rd largest integer in nums is "2".
 * Example 3:
 *
 * Input: nums = ["0","0"], k = 2
 * Output: "0"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["0","0"].
 * The 2nd largest integer in nums is "0".
 */
public class FindKthLargestInteger {
    public static void main(String[] args) {
        FindKthLargestInteger findKthLargestInteger = new FindKthLargestInteger();
        String str = findKthLargestInteger.kthLargestNumber(new String[]{"2","21","12","1"},3);
        System.out.println(str);
    }

    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if(o1.length() != o2.length())
                return o1.length() - o2.length();
            else
                return o1.compareTo(o2);
        });

        for(String str: nums){
            priorityQueue.add(str);
        }
        int index = 0;
        while ( index < nums.length - k){
            priorityQueue.poll();
            index++;
        }
        return priorityQueue.poll();
     }
}
