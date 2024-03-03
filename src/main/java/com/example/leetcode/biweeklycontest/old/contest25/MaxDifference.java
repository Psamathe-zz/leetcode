package com.example.leetcode.biweeklycontest.old.contest25;

/**
 * You are given an integer num. You will apply the following steps exactly two times:
 *
 * Pick a digit x (0 <= x <= 9).
 * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
 * Replace all the occurrences of x in the decimal representation of num by y.
 * The new integer cannot have any leading zeros, also the new integer cannot be 0.
 * Let a and b be the results of applying the operations to num the first and second times, respectively.
 *
 * Return the max difference between a and b.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 555
 * Output: 888
 * Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
 * The second time pick x = 5 and y = 1 and store the new integer in b.
 * We have now a = 999 and b = 111 and max difference = 888
 * Example 2:
 *
 * Input: num = 9
 * Output: 8
 * Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
 * The second time pick x = 9 and y = 1 and store the new integer in b.
 * We have now a = 9 and b = 1 and max difference = 8
 * Example 3:
 *
 * Input: num = 123456
 * Output: 820000
 * Example 4:
 * 923456
 * 103456
 *
 *
 * Input: num = 10000
 * Output: 80000
 * Example 5:
 *
 * Input: num = 9288
 * Output: 8700
 * 9988
 * 1288
 *
 * 1101057
 *
 * 9909057
 * 1101057
 *
 * Output:
 * 8808000
 * Expected:
 * 8808050
 *
 * Input:
 * 10000
 * Output:
 * 90000
 * Expected:
 * 80000
 */
public class MaxDifference {

    public static void main(String[] args) {
        int num = 10000;
        MaxDifference maxDifference = new MaxDifference();
        maxDifference.maxDiff(num);
    }

    public int maxDiff(int num) {
        String input = String.valueOf(num);
        int min;
        int max;
        char first = input.charAt(0);
        char maxChar = first;
        char minChar = first;
        if(maxChar != '9')
            max = Integer.valueOf(input.replace(maxChar,'9'));
        else{
            for(char c : input.toCharArray()){
                if(c != maxChar){
                    maxChar = c;
                    break;
                }
            }
            max = Integer.valueOf(input.replace(maxChar,'9'));
        }
        if(minChar!='1')
            min = Integer.valueOf(input.replace(minChar,'1'));
        else{
            for(char c : input.toCharArray()){
                if(c != minChar && c!='0'){
                    minChar = c;
                    break;
                }
            }
            min = minChar=='1'?num:Integer.valueOf(input.replace(minChar,'0'));
        }

        return max - min;
    }
}
