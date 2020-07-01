package com.example.leetcode.challenge.July.week1;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 *
 * n = 5
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * Because the 3rd row is incomplete, we return 2.
 * Example 2:
 *
 * n = 8
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 *
 * Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoins {
    public static void main(String[] args) {
        ArrangingCoins arrangingCoins = new ArrangingCoins();
        arrangingCoins.arrangeCoinsV1(8);
    }

    public int arrangeCoins(int n) {
        int count = 0;
        int start = 1;
        while (n >= start){
            count++;
            n -= start;
            start++;
        }
        return count;
    }


    /**
     * faster solution
     * https://blog.csdn.net/u011809767/article/details/70237948
     * @param n
     * @return
     */
    public int arrangeCoinsV1(int n) {
        int left = 0;
        int right = n;
        long mid;

        long curr;
        while (left <= right) {
            mid =(int)(left + (right - left) / 2);
            curr = mid * (mid + 1) / 2;

            if (curr == n)
                return (int)mid;

            if (n < curr) {
                right = (int)mid - 1;
            } else {
                left = (int)mid + 1;
            }
        }
        return right;
    }

    /**
     * https://www.cnblogs.com/xiejunzhao/p/73abac075151f1abc87d70f56d0f2211.html
     * Math
     * @param n
     * @return
     */
    public int ArrangingCoinsV2(int n) {
        return (int)((Math.sqrt(8L * n + 1) - 1) / 2);
    }

}
