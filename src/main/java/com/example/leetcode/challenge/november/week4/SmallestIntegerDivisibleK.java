package com.example.leetcode.challenge.november.week4;


/**
 * Given a positive integer K, you need to find the length of the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
 *
 * Return the length of N. If there is no such N, return -1.
 *
 * Note: N may not fit in a 64-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: K = 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 * Example 2:
 *
 * Input: K = 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 * Example 3:
 *
 * Input: K = 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 *
 *
 * Constraints:
 *
 * 1 <= K <= 105
 */
public class SmallestIntegerDivisibleK {
    public static void main(String[] args) {

    }

    /**
     * https://blog.csdn.net/fuxuemingzhu/article/details/88778532
     * @param K
     * @return
     */
    public int smallestRepunitDivByK(int K) {
        if(K % 10 != 1 && K % 10 != 3 && K % 10 != 7 && K % 10 != 9)
            return -1;
        else{
            int r = 0;
            for (int i = 1; i <= K + 1 ; i++) {
                r = (10 * r + 1) % K;
                if(r == 0)
                    return i;
            }
            return -1;
        }
    }

    public int smallestRepunitDivByKV1(int K) {
        if(K%2==0 || K%5==0)
            return -1;
        if(K==1)
            return 1;
        //int output = 1;
        //int coef=2;
        //HashSet<Integer> seenRemainder = new HashSet();
        int remainder = 0;
        for(int i=1;i<=K;i++){
            //output+=Math.pow(10,i);
            remainder=(10*remainder+1)%K;
            if(remainder==0)
                return i;
            //else return -1;
        }
        return -1;
    }
}
