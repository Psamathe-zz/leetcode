package com.example.leetcode.biweeklycontest.old.contest24;

/**
 * A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.
 *
 * Given the string s and the integer k. There can be multiple ways to restore the array.
 *
 * Return the number of possible array that can be printed as a string s using the mentioned program.
 *
 * The number of ways could be very large so return it modulo 10^9 + 7
 *
 * Example 1:
 *
 * Input: s = "1000", k = 10000
 * Output: 1
 * Explanation: The only possible array is [1000]
 * Example 2:
 *
 * Input: s = "1000", k = 10
 * Output: 0
 * Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.
 * Example 3:
 *
 * Input: s = "1317", k = 2000
 * Output: 8
 * Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
 * Example 4:
 *
 * Input: s = "2020", k = 30
 * Output: 1
 * Explanation: The only possible array is [20,20]. [2020] is invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros.
 * Example 5:
 *
 * Input: s = "1234567890", k = 90
 * Output: 34
 * 设f(i)为前i位可能存在的方案数， 我们有:
 * f(i) = \sum_{j = 0}^{i - 1} {f(j) | s[j...i-1] <= k}f(i)=∑
 * j=0
 * i−1
 * ​
 *  f(j)∣s[j...i−1]<=k
 *
 */
public class RestoreArray {
    public static void main(String[] args) {
        String s = "1317";
        int k = 30;
        RestoreArray restoreArray = new RestoreArray();
        int result = restoreArray.numberOfArrays(s,k);
        System.out.println(result);
    }

    public int numberOfArrays(String s, int k) {
        int result = 0;
        int length = s.length()>=9?9:s.length();
        for(int i=0;i< length;i++){
            for(int j = 0;j< i-1;j++){
                if(Integer.valueOf(s.substring(j,i-1)) <= k)
                    result += numberOfArrays(s.substring(0,j),k);
            }
        }
        return result;
    }
}
