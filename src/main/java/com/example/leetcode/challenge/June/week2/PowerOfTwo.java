package com.example.leetcode.challenge.June.week2;

/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: 218
 * Output: false
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        int n = 8;
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        boolean result = powerOfTwo.isPowerOfTwoV2(n);
        System.out.println(result);
    }



    public boolean isPowerOfTwo(int n) {
        if(n < 0)
            return false;
        double temp = (double) n/2;
        int t = (int)temp;
        if(temp != t )
            return false;
        return isPowerOfTwo(t);
    }
    public boolean isPowerOfTwoV1(int n) {
        if(n < 0)
            return false;
        int cpt = 0;
        String str = Integer.toBinaryString(n);
        for(char c : str.toCharArray()){
            if(c=='1'){
                cpt++;
            }
            if(cpt > 1){
                return false;
            }

        }
        return cpt == 1;
    }

    /**
     * faster solution
     * @param n
     * @return
     * n = 8
     * n = 1000
     * -n = 11111111111111111111111111000
     */
    public boolean isPowerOfTwoV2(int n) {
        if(n<=0)
            return false;
        String str = Integer.toBinaryString(n);
        String str1 = Integer.toBinaryString(-n);
        int t = n&(-n);
        return (n&(-n)) == n;

    }
}
