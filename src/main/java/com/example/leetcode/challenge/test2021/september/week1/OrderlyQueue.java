package com.example.leetcode.challenge.test2021.september.week1;

import java.util.Arrays;

/**
 * You are given a string s and an integer k. You can choose one of the first k letters of s and append it at the end of the string..
 *
 * Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cba", k = 1
 * Output: "acb"
 * Explanation:
 * In the first move, we move the 1st character 'c' to the end, obtaining the string "bac".
 * In the second move, we move the 1st character 'b' to the end, obtaining the final result "acb".
 * Example 2:
 *
 * Input: s = "baaca", k = 3
 * Output: "aaabc"
 * Explanation:
 * In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
 * In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".
 */
public class OrderlyQueue {
    public static void main(String[] args) {

    }

    public String orderlyQueue(String s, int k) {
        if(k == 1){
            String res = s;
            String temp;
            for (int i = 1; i < s.length(); i++) {
                temp =  s.substring(i) + s.substring(0,i);
                if(res.compareTo(temp) > 0)
                    res = temp;
            }
            return res;
        } else {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return String.valueOf(chars);
        }
    }
}
