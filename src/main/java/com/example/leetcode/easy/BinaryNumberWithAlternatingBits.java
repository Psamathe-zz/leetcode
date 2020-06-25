package com.example.leetcode.easy;

public class BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {

    }

    public boolean hasAlternatingBits(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();
        for (int i = 1; i < bits.length; i++) {
            if(bits[i] == bits[i - 1])
                return false;
        }
        return true;
    }

    /**
     * faster solution
     * @param n
     * @return
     */
    public boolean hasAlternatingBitsV1(int n) {
        int cur = n % 2;
        n /= 2;
        while (n > 0) {
            if (cur == n % 2)
                return false;
            cur = n % 2;
            n /= 2;
        }
        return true;
    }
}
