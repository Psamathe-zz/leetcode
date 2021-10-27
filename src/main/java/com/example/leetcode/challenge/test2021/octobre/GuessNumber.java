package com.example.leetcode.challenge.test2021.octobre;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns 3 possible results:
 *
 * -1: The number I picked is lower than your guess (i.e. pick < num).
 * 1: The number I picked is higher than your guess (i.e. pick > num).
 * 0: The number I picked is equal to your guess (i.e. pick == num).
 * Return the number that I picked.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10, pick = 6
 * Output: 6
 * Example 2:
 *
 * Input: n = 1, pick = 1
 * Output: 1
 * Example 3:
 *
 * Input: n = 2, pick = 1
 * Output: 1
 * Example 4:
 *
 * Input: n = 2, pick = 2
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= n <= 231 - 1
 */
public class GuessNumber {
    public static void main(String[] args) {

    }

    public int guessNumber(int n) {
        int max = n;
        int min = 1;
        int mid;
        int temp;
        do {
            mid = min + (max - min) / 2;
            temp = guess(mid);
            if(temp == 1)
                min = mid + 1;
            else if(temp == -1)
                max = mid - 1;
        } while (temp != 0);
        return mid;
    }

    public int guess(int val){
        return 0;
    }


}
