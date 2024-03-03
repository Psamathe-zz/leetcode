package com.example.leetcode.biweeklycontest.old.contest44;


/**
 * There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
 *
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1]. For example, if perm = [1,3,2], then encoded = [2,1].
 *
 * Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: encoded = [3,1]
 * Output: [1,2,3]
 * Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * Example 2:
 *
 * Input: encoded = [6,5,4,6]
 * Output: [2,4,1,5,3]
 *
 */
public class DecodeXORedPermutation {
    public static void main(String[] args) {
        DecodeXORedPermutation decodeXORedPermutation = new DecodeXORedPermutation();
        decodeXORedPermutation.decode(new int[]{6,5,4,6});
    }

    /**
     * https://leetcode-cn.com/problems/decode-xored-permutation/solution/ling-ji-yi-dong-de-jie-fa-by-motmlsc-5zkm/
     * 首先，这是一个构造问题。根据题目要求，我们只知道 permperm 数组是前 nn 个正整数的某种排列，但是在 n < 10^5n<10
     * 5
     *   的数量级下，我们是无法利用 O(n!)O(n!) 的复杂度遍历所有可能排列并去验证的。所以我们需要想一些奇思妙计。
     *
     * 从示例 22 入手，如下图所示，我们知道的 encodedencoded 数组是第二行的各个元素，我们所要求的 permperm 数组为第一行所要求的元素。既然不知道怎么往回推，不如继续往前走到底，“撞一撞南墙”。
     *
     *
     * 我们发现，“前进”到第四层时，得到了 B\bigoplus C \bigoplus D \bigoplus EB⨁C⨁D⨁E 的异或值（为方便表示，我们之后都用 BCDEBCDE 这种直接联结的形式表示长异或串），而根据给定的 encodedencoded 数组长度为 n - 1n−1，我们是能计算得到 ABCDEABCDE 的异或值的。那么这两个值再进行异或，就可以确定 AA 的值，从而可以唯一确定 permperm 数组。
     *
     * 如何得到 BCDEBCDE 呢？我们如果按照图示的方式每层向下遍历一次，最终的时间复杂度是 O((n - 1)!)O((n−1)!)，但通过观察， 我们通过第二层的 BCBC 的异或值和 DEDE 的异或值即可得到 BCDEBCDE 的异或值，而第二层正是题目给定的 encodedencoded 数组。由于题目特别说明了 nn 为奇数，说明除开 AA 外，后面 n - 1n−1 个数刚好可以两两配对（例如本题的 BCBC 和 DEDE），我们只需要在 encodedencoded 数组中每隔一个数进行一次计算，就可以得到除开 AA 外，剩余所有数的异或值了。（例如， n = 7n=7 时，给定的 encodedencoded 数组一定是形如 [AB, BC, CD, DE, EF, FG][AB,BC,CD,DE,EF,FG] 的，那么我们跳着取 BC, DE, FGBC,DE,FG 并做异或即可）

     *
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        int length = encoded.length;
        int[] res = new int[length+1];
        int all = 0; // 全部 n 个正整数的异或值
        for(int i = 1; i <= length + 1; i++)
             all ^= i;
        int all_but_first = 0; // 除开第一个数的异或值
        for(int i = 1; i < length; i += 2)
            all_but_first ^= encoded[i];
        res[0] = all ^ all_but_first; // 得到第一个数
        for(int i = 1; i <= length; i++)
            res[i] = res[i - 1] ^ encoded[i - 1];
        return res;
    }

    public int[] decode(int[] encoded, int first) {
        int length = encoded.length;
        int[] res = new int[length + 1];
        res[0] = first;
        for (int i = 1; i <= length ; i++) {
            res[i] = res[i-1] ^ encoded[i - 1];
        }
        return res;
    }
}
