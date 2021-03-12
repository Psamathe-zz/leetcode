package com.example.leetcode.challenge.test2021.february.week3;


/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 *
 * A slice (P, Q) of the array A is called arithmetic if the sequence:
 * A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 *
 * The function should return the number of arithmetic slices in the array A.
 *
 *
 * Example:
 *
 * A = [1, 2, 3, 4]
 *
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class ArithmeticSlices {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/5968340.html
     * 这道题让我们算一种算数切片，说白了就是找等差数列，限定了等差数列的长度至少为3，那么[1,2,3,4]含有3个长度至少为3的算数切片，我们再来看[1,2,3,4,5]有多少个呢:
     * len = 3: [1,2,3], [2,3,4], [3,4,5]
     *
     * len = 4: [1,2,3,4], [2,3,4,5]
     *
     * len = 5: [1,2,3,4,5]
     *
     * 那么我们可以归纳出规律，长度为n的等差数列有1个，长度为n-1的等差数列有2个，... ，长度为3的等差数列有 n-2 个，那么总共就是 1 + 2 + 3 + ... + n-2 ，
     * 此时就要祭出高斯求和公式了，长度为n的等差数列中含有长度至少为3的算数切片的个数为(n-1)(n-2)/2，
     * 那么题目就变成了找原数组中等差数列的长度，然后带入公式去算个数即可，参见代码如下：
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0, len = 2, n = A.length;
        for (int i = 2; i < n; ++i) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                ++len;
            } else {
                if (len > 2)
                    res += (len - 1) * (len - 2) * 0.5;
                len = 2;
            }
        }
        if (len > 2)
            res += (len - 1) * (len - 2) * 0.5;
        return res;
    }
}
