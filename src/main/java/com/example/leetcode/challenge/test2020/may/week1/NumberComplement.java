package com.example.leetcode.challenge.test2020.may.week1;

/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 *
 *
 *
 * Example 1:
 *
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 *
 *
 * Example 2:
 *
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 *
 *
 * Note:
 *
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class NumberComplement {

    public static void main(String[] args) {
        int num = 5;
        NumberComplement numberComplement = new NumberComplement();
        int result = numberComplement.findComplementV4(num);
        System.out.println(result);
    }

    public int findComplement(int num) {
        String input = Integer.toBinaryString(num);
        StringBuffer stringBuffer = new StringBuffer();
        for(char c: input.toCharArray()){
            stringBuffer.append(c=='0'?'1':'0');
        }
        return Integer.valueOf(stringBuffer.toString(),2);
    }


    /**
     * faster solution
     * @param num
     * @return
     */
    public int findComplementV2(int num) {
        int cp = num;
        int sum = 0;
        while(num > 0){
            sum = (sum << 1) + 1;
            num >>= 1;
        }
        return sum - cp;

    }

    public int findComplementV3(int num) {
        //complement = num ^ allone
        //010 = 101 ^ 111
        //calculate all one set
        int todo = num, bit = 1;
        while(todo != 0) {
            //flip curr bit
            num = num ^ bit;
            bit = bit << 1;
            todo = todo >> 1; //record the length;
        }
        return num;
    }

    public int findComplementV4(int num) {
        int t = Integer.highestOneBit(num);
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        num = ~num;
        /**
         *  5转换为二进制：0000 0000 0000 0000 0000 0000 0000 0101
         * -------------------------------------------------------------------------------------
         *
         * -6转换为二进制： 1111 1111 1111 1111 1111 1111 1111 1010
         * mask为二进制：   0000 0000 0000 0000 0000 0000 0000 0111
         * 10
         * -------------------------------------------------------------------------------------
         */
        return num & mask;
    }
}
