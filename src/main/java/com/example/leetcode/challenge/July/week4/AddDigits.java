package com.example.leetcode.challenge.July.week4;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * Example:
 *
 * Input: 38
 * Output: 2
 * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
 *              Since 2 has only one digit, return it.
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 */
public class AddDigits {
    public static void main(String[] args) {
        int num = 38;
        AddDigits addDigits = new AddDigits();
        int res = addDigits.addDigits(num);
        System.out.println(res);
    }

    public int addDigits(int num) {
        if(num < 10)
            return num;
        else {
            int temp = 0;
            while (num > 0){
                temp += num%10;
                num /= 10;
            }
            return addDigits(temp);
        }

    }

    /**
     * less memory
     * @param num
     * @return
     */
    public int addDigitsV1(int num) {
        return num == 0 ? 0 : 1 + (num - 1) % 9;
    }
}
