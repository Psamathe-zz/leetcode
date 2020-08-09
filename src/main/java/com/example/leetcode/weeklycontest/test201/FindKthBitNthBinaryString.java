package com.example.leetcode.weeklycontest.test201;

/**
 * Given two positive integers n and k, the binary string  Sn is formed as follows:
 *
 * S1 = "0"
 * Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 *
 * For example, the first 4 strings in the above sequence are:
 *
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 1
 * Output: "0"
 * Explanation: S3 is "0111001". The first bit is "0".
 * Example 2:
 *
 * Input: n = 4, k = 11
 * Output: "1"
 * Explanation: S4 is "011100110110001". The 11th bit is "1".
 * Example 3:
 *
 * Input: n = 1, k = 1
 * Output: "0"
 * Example 4:
 *
 * Input: n = 2, k = 3
 * Output: "1"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= 2n - 1
 */
public class FindKthBitNthBinaryString {
    public static void main(String[] args) {
        FindKthBitNthBinaryString findKthBitNthBinaryString = new FindKthBitNthBinaryString();
        char c = findKthBitNthBinaryString.findKthBit(4,11);
        System.out.println(c);
    }

    public char findKthBit(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder("0");
        StringBuilder stringBuilderTemp;
        for (int i = 1; i < n; i++) {
            stringBuilderTemp = new StringBuilder(stringBuilder);
            invert(stringBuilderTemp);
            stringBuilder.append('1').append(stringBuilderTemp.reverse());
        }
        return stringBuilder.charAt(k-1);
    }

    public void invert(StringBuilder stringBuilder){
        for (int i = 0; i < stringBuilder.length(); i++) {
            if(stringBuilder.charAt(i) == '0'){
                stringBuilder.setCharAt(i,'1');
            } else {
                stringBuilder.setCharAt(i,'0');
            }
        }
    }


    /**
     * faster solution
     * @param n
     * @param k
     * @return
     */
    public char findKthBitV1(int n, int k) {
        if(n == 1)
            return '0';
        int mid = 1<<n-1;
        if(k == 1<<n-1)
            return '1';
        if(k < mid)
            return findKthBitV1(n-1, k);
        return (char)(findKthBitV1(n-1, (1<<n)-k)^1);
    }
}
