package com.example.leetcode.weeklycontest.old.test268;

/**
 * A k-mirror number is a positive integer without leading zeros that reads the same both forward and backward in base-10 as well as in base-k.
 *
 * For example, 9 is a 2-mirror number. The representation of 9 in base-10 and base-2 are 9 and 1001 respectively, which read the same both forward and backward.
 * On the contrary, 4 is not a 2-mirror number. The representation of 4 in base-2 is 100, which does not read the same both forward and backward.
 * Given the base k and the number n, return the sum of the n smallest k-mirror numbers.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, n = 5
 * Output: 25
 * Explanation:
 * The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
 *   base-10    base-2
 *     1          1
 *     3          11
 *     5          101
 *     7          111
 *     9          1001
 * Their sum = 1 + 3 + 5 + 7 + 9 = 25.
 * Example 2:
 *
 * Input: k = 3, n = 7
 * Output: 499
 * Explanation:
 * The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
 *   base-10    base-3
 *     1          1
 *     2          2
 *     4          11
 *     8          22
 *     121        11111
 *     151        12121
 *     212        21212
 * Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.
 * Example 3:
 *
 * Input: k = 7, n = 17
 * Output: 20379000
 * Explanation: The 17 smallest 7-mirror numbers are:
 * 1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
 */
public class SumOfkMirrorNumbers {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/sum-of-k-mirror-numbers/solution/java-cong-chang-du-wei-yi-kai-shi-gou-za-qfap/
     * @param c
     * @return
     */
    private boolean isGood(char[] c) {
        for (int i = 0, j = c.length - 1; i < j; i++, j--) {
            if (c[i] != c[j]) {
                return false;
            }
        }
        return true;
    }

    public long kMirror(int k, int n) {
        long ans = 0;
        for (int len = 1; ; len++) {
            int x = (int) Math.pow(10, (len - 1) / 2);
            int y = (int) Math.pow(10, (len + 1) / 2);
            for (int i = x; i < y; i++) {
                long nn = i;
                for (int j = len % 2 == 0 ? i : i / 10; j > 0; j /= 10) {
                    nn = nn * 10 + j % 10;
                }
                String ss = Long.toString(nn, k);
                if (isGood(ss.toCharArray())) {
                    ans += nn;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }
}
