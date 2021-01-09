package com.example.leetcode.challenge.test2020.may.week4;

/**
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 *
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 *
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * 63
 * Output:
 * [0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Expected:
 * [0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6]
 */
public class CountingBits {
    public static void main(String[] args) {
        int num = 63;
        CountingBits countingBits = new CountingBits();
        int[] result = countingBits.countBits(num);
        System.out.println(result);
    }

    public int[] countBits(int num) {
        int length = num + 1;
        int[] result = new int[length];
        result[0] = 0;
        if(length == 1)
            return result;
        if(length >= 2){
            result[1] = 1;
            if(length == 2)
                return result;
        }
        int left = 1;
        int right = 1;
        int t1;
        int t2;
        boolean finish = false;
        while (num >= right && !finish){
            for(int i = left ; i <= right && !finish; i++){
                int temp = i << 1;
                t1 = temp;
                t2 = temp + 1;
                if(t1 > num){
                    finish = true;
                } else
                    result[t1] = result[i];

                if(t2 > num){
                    finish = true;
                } else
                    result[t2] = result[i] + 1;
            }
            left = left << 1;
            right = (right << 1) + 1;
        }
        return result;
    }


    /**
     * faster solution
     * @param num
     * @return
     */
    public int[] countBitsV2(int num) {
        if (num < 0)
            return new int[1];

        // allocate array incuding 0->num
        int[] countBitArray = new int[num + 1];

        // initial DP data
        countBitArray[0] = 0;

        for (int i = 1; i <= num; i++) {
            // if num is even, bit count is same as num / 2
            // if odd, bit count is same as (num / 2) + 1
            countBitArray[i] = countBitArray[i >> 1] + i % 2;
        }

        return countBitArray;

    }

    /**
     * less memory
     */
    public int[] countBitsV3(int num) {
        if (num == 0){
            return new int[]{0};
        }
        if (num == 1){
            return new int[]{0, 1};
        }
        int[] rst = new int[num + 1];
        rst[0] = 0;
        rst[1] = 1;
        for (int i = 2; i<= num; i++) {
            rst[i] = rst[i/2] + i%2;
        }
        return rst;
    }
}
