package com.example.leetcode.weeklycontest.old.test251;


import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a string s consisting of lowercase English letters, and an integer k.
 *
 * First, convert s into an integer by replacing each letter with its position in the alphabet (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26). Then, transform the integer by replacing it with the sum of its digits. Repeat the transform operation k times in total.
 *
 * For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
 *
 * Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
 * Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
 * Transform #2: 17 ➝ 1 + 7 ➝ 8
 * Return the resulting integer after performing the operations described above.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "iiii", k = 1
 * Output: 36
 * Explanation: The operations are as follows:
 * - Convert: "iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
 * - Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
 * Thus the resulting integer is 36.
 * Example 2:
 *
 * Input: s = "leetcode", k = 2
 * Output: 6
 * Explanation: The operations are as follows:
 * - Convert: "leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
 * - Transform #1: 12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
 * - Transform #2: 33 ➝ 3 + 3 ➝ 6
 * Thus the resulting integer is 6.
 * Example 3:
 *
 * Input: s = "zbax", k = 2
 * Output: 8
 */
public class SumDigits {
    public static void main(String[] args) {
        SumDigits sumDigits = new SumDigits();
        sumDigits.getLucky("zbax",2);
    }

    public int getLucky(String s, int k) {
        char[] chars = s.toCharArray();
        Deque<Integer> queue = new LinkedList<>();
        for (char c: chars){
            int x = c - 'a' + 1 ;
            if(x / 10  != 0)
                queue.add(x / 10);
            queue.add( x % 10);
        }
        int size;
        int temp = 0;

        for (int i = 0; i < k; i++) {
            temp = 0;
            size = queue.size();
            while (size > 0){
                temp += queue.poll();
                size--;
            }
            if(i < k - 1){
                while (temp != 0){
                    queue.addFirst(temp % 10);
                    temp /= 10;
                }
            }
        }

        return temp;
    }


    /**
     * better solution
     * @param s
     * @param k
     * @return
     */
    public int getLuckyV1(String s, int k) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            int val = c - 'a' + 1;
            sum += val % 10 + val / 10;
        }
        for (int i = 1; i < k; i++) {
            int newsum = 0;
            while (sum > 0) {
                newsum += sum % 10;
                sum /= 10;
            }
            sum = newsum;
        }
        return sum;
    }
}
