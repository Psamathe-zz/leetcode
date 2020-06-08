package com.example.leetcode.easy;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example :
 *
 * Input: n = 10, pick = 6
 * Output: 6
 */
public class GuessNumberHigherLower {
    public static void main(String[] args) {
        GuessNumberHigherLower guessNumberHigherLower = new GuessNumberHigherLower();
        guessNumberHigherLower.pick = 6;
        int result = guessNumberHigherLower.guessNumber(10);
        System.out.println(result);
    }
    int pick;
    public int guessNumber(int n) {
        if (guess(n) == 0) return n;
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int t = guess(mid);
            if (t == 0)
                return mid;
            if (t == 1)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
    public int guessNumberVold(int n) {

        if (guess(n) == 0)
            return n;
        int left = 1;
        int right = n;
        int mid = (left + right) / 2;
        int value;
        while (left <= right){
            value = guess(mid);
            if(value == 0){
                break;
            } else if( value < 0){
                left = mid + 1;
            } else
                right = mid - 1;
            mid = (left + right) / 2;
        }
        return mid;
    }

    public int guess(int num){
        if(num < pick)
            return -1;
        else if(num == pick)
            return 0;
        else
            return 1;
    }
}
