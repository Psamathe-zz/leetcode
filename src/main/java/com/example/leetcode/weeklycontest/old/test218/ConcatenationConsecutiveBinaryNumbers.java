package com.example.leetcode.weeklycontest.old.test218;


/**
 * Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: "1" in binary corresponds to the decimal value 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 27
 * Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
 * After concatenating them, we have "11011", which corresponds to the decimal value 27.
 * Example 3:
 *
 * Input: n = 12
 * Output: 505379714
 * Explanation: The concatenation results in "1101110010111011110001001101010111100".
 * The decimal value of that is 118505380540.
 * After modulo 109 + 7, the result is 505379714.
 *
 */
public class ConcatenationConsecutiveBinaryNumbers {
    public static void main(String[] args) {

    }

    /**
     * 因此，我们只需要判断当前n值是否为为2的幂，如果是，位数偏移在之前的基础上加1，否则位数偏移不变
     * 判断n值是否为2的幂方法有很多，在这里我采用了一种比较简单的方法i & (i-1)是否等于0，如果i是2的幂，说明仅某一位为1，其余均为0，那么i-1即为其余位均为1，自然与运算为0。如果难以理解可以想象99和100的关系。
     *
     * 作者：civitas
     * 链接：https://leetcode-cn.com/problems/concatenation-of-consecutive-binary-numbers/solution/java-beat-100-by-civitas-gwad/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private static final int MOD = 1000000007;
    public int concatenatedBinary(int n) {
        int res = 0, shift = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                // 说明是2的幂，则进位
                shift++;
            }
            res = (int) ((((long) res << shift) + i) % MOD);
        }
        return res;
    }
}
