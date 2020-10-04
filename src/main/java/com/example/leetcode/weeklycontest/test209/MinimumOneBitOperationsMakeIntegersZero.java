package com.example.leetcode.weeklycontest.test209;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Given an integer n, you must transform it into 0 using the following operations any number of times:
 *
 * Change the rightmost (0th) bit in the binary representation of n.
 * Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through 0th bits are set to 0.
 * Return the minimum number of operations to transform n into 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 0
 * Output: 0
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: The binary representation of 3 is "11".
 * "11" -> "01" with the 2nd operation since the 0th bit is 1.
 * "01" -> "00" with the 1st operation.
 * Example 3:
 *
 * Input: n = 6
 * Output: 4
 * Explanation: The binary representation of 6 is "110".
 * "110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
 * "010" -> "011" with the 1st operation.
 * "011" -> "001" with the 2nd operation since the 0th bit is 1.
 * "001" -> "000" with the 1st operation.
 * Example 4:
 *
 * Input: n = 9
 * Output: 14
 * Example 5:
 *
 * Input: n = 333
 * Output: 393
 *
 *
 * Constraints:
 *
 * 0 <= n <= 109
 */
public class MinimumOneBitOperationsMakeIntegersZero {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-one-bit-operations-to-make-integers-zero/solution/gen-zhao-ti-shi-zhao-gui-lu-kan-liao-bie-ren-de-ge/
     * @param n
     * @return
     */
    HashMap<Integer, Integer> map = new HashMap<>();
    TreeSet<Integer> set = new TreeSet<>();

    public int minimumOneBitOperations(int n) {
        map.put(0, 0);
        int last2Powk = 1;//记录上一个2^k的数值
        while(last2Powk <= n){
            map.put(last2Powk, map.get(last2Powk / 2) * 2 + 1);
            set.add(last2Powk);
            last2Powk *= 2;
        }
        return helper(n);
    }

    public int helper(int n){
        if(map.containsKey(n)) return map.get(n);

        int low = set.floor(n);//找到小于n的最大的完全平方数
        int res = helper(low) - helper(n % low);
        map.put(n, res);
        return res;
    }


    /**
     * 格雷码
     * @param n
     * @return
     */
    public int minimumOneBitOperationsV0(int n) {
        int res = 0;
        while(n != 0){
            res ^= n;
            n >>= 1;
        }
        return res;
    }


    /**
     * faster solution
     * @param n
     * @return
     */
    public int minimumOneBitOperationsV1(int n) {
        int[] dp1 = new int[32];
        dp1[0] = 1;
        for (int s = 1; s < 32; s++) {
            dp1[s] = dp1[s-1]*2 + 1;
        }
        int[] dp2 = new int[32];
        dp2[0] = 1;
        dp2[1] = 2;
        for (int s = 2; s < 32; s++) {
            dp2[s] = dp2[s-1] + dp1[s-2] + 1;
        }
        int s = 30;
        int bit = 0;
        int ans = 0;
        while (s >= 0) {
            if ((n & (1 << s)) != (bit << s)) {
                if (bit == 1) {
                    if (s == 0) {
                        ans++;
                    } else {
                        ans += dp1[s-1];
                        ans++;
                    }
                } else {
                    ans += dp2[s];
                    bit = 1-bit;
                }
            } else {
                bit = 0;
            }
            s--;
        }
        return ans;
    }


    public int minimumOneBitOperationsV3(int n) {
        if (n < 2)
            return n;
        int l = Integer.toBinaryString(n).length();
        return (1<<(l-1))+minimumOneBitOperations(n^(3<<(l-2)));
    }
}
