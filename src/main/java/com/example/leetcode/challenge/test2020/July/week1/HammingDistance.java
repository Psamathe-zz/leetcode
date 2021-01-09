package com.example.leetcode.challenge.test2020.July.week1;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 *
 * Note:
 * 0 ≤ x, y < 231.
 *
 * Example:
 *
 * Input: x = 1, y = 4
 *
 * Output: 2
 *
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * The above arrows point to positions where the corresponding bits are different.
 */
public class HammingDistance {
    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        hammingDistance.hammingDistance(1,4);
    }

    public int hammingDistance(int x, int y) {
        int result = 0;
        int diff = x^y;
        result += diff & 1;
        while((diff = diff >> 1) > 0){
            result += diff & 1;
        }
        return result;
    }


    /**
     * https://www.jianshu.com/p/99a47ed39c43
     * 而上面这个解法中能够只执行 count 次就结束循环，堪称完美！那么，现在我们来看看最为关键的代码 n = n & (n - 1) 有什么奥秘。
     *
     * 二进制数减一是一个奇妙的操作——当一个二进制数减一的时候，低位的 0 会变成 1，直到遇到一个最低位的 1 被减成 0。假设这个数 n 中最低位的 1 位于第 m 位（m >= 0），最高位的 1 位于第 M 位，最高位为第 N 位。那么此时，0~m 位各位上的数字都做了取反操作（包含一个 m 位的 1 和 0 ~ m-1 位的所有 0），而 m+1 ~ N 位各位上的数字都保持不变，即数 n 与上 (n - 1) 会导致 0~m 位均变成 0 ，这个过程中影响到了最低位（m 位上的一个 1）。即，做一次 n = n & (n - 1) 的操作会使得二进制数少一个最低位上的 1。
     *
     * 特别的，二进制数中只有一个 1 的时候，n & (n - 1) // == 0。由此 n > 0 && (n & (n - 1)) 也常用于判断整数 n 是不是 2 的指数：
     *
     * 作者：白蜀黍
     * 链接：https://www.jianshu.com/p/99a47ed39c43
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistanceV2(int x, int y) {
        int n = x ^ y ;
        int len = 0;
        while(n > 0){
            int a = n-1;
            n = n & n - 1;
            len++;
        }
        return len;
    }
}
