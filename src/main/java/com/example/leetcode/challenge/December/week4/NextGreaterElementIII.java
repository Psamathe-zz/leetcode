package com.example.leetcode.challenge.December.week4;


/**
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n.
 * If no such positive integer exists, return -1.
 *
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 *
 */
public class NextGreaterElementIII {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/cnoodle/p/12501777.html
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        String number = String.valueOf(n);
        char[] res = number.toCharArray();

        int i = res.length - 2;
        while (i >= 0 && res[i + 1] <= res[i]) {
            i--;
        }
        if (i < 0)
            return -1;

        int j = res.length - 1;
        while (j >= 0 && res[j] <= res[i]) {
            j--;
        }
        swap(res, i, j);
        reverse(res, i + 1);
        long val = Long.parseLong(new String(res));
        return val <= Integer.MAX_VALUE ? (int) val : -1;
    }

    private void reverse(char[] chars, int start) {
        int i = start;
        int j = chars.length - 1;
        while (i < j) {
            swap(chars, i++, j--);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
