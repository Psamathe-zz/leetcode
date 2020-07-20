package com.example.leetcode.easy;

/**
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Example 1:
 *
 * Input: 27
 * Output: true
 * Example 2:
 *
 * Input: 0
 * Output: false
 * Example 3:
 *
 * Input: 9
 * Output: true
 * Example 4:
 *
 * Input: 45
 * Output: false
 * Follow up:
 * Could you do it without using any loop / recursion?
 */
public class PowerThree {
    public static void main(String[] args) {
        PowerThree powerThree = new PowerThree();
        boolean res = powerThree.isPowerOfThree(45);
        System.out.println(res);
    }
    public boolean isPowerOfThree(int n) {
        if(n == 0)
            return false;
        if(n == 1)
            return true;
        if(n % 3 != 0)
            return false;
        return isPowerOfThree(n / 3);
    }

    /**
     * faster solution
     * @param n
     * @return
     */
    public boolean isPowerOfThreeV1(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n == 2) return false;
        if (n % 3 == 0){
            return isPowerOfThree(n/3);
        }else
            return false;
    }
}
