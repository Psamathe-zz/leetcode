package com.example.leetcode.weeklycontest.test216;


import java.util.Arrays;

/**
 * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.
 *
 * The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
 *
 * You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.
 *
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 27
 * Output: "aay"
 * Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
 * Example 2:
 *
 * Input: n = 5, k = 73
 * Output: "aaszz"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * n <= k <= 26 * n
 */
public class SmallestString {
    public static void main(String[] args) {
        SmallestString smallestString = new SmallestString();
        String res = smallestString.getSmallestString(5,73);
        System.out.println(res);
    }
    char[] res;
    public String getSmallestString(int n, int k){
        res = new char[n];
        helper(n,n,k);
        return String.valueOf(res);
    }

    public void helper(int max, int n, int k) {
        if(n == 1){
            res[max - 1] = (char) ('a' + (k - 1));
            return;
        }

        int i;
        for ( i = 1; i <= 26; i++) {
            if(k - i <= (n - 1) * 26){
                break;
            }
        }
        res[max - n] = (char) ('a' + (i - 1));
        helper(max,n - 1, k - i);
    }


    /**
     * 示例2：
     * 输入：n = 5, k = 73
     * 输出："aaszz"
     *
     * 先写“aaaaa”，k-5=68；
     * 68/25=2 余18
     * 说明最后两位a可以变成z，倒数第三位'a'+18='s'
     *
     * 注意不能用concat，会超时，可以用构造函数。
     *
     * 作者：justqby
     * 链接：https://leetcode-cn.com/problems/smallest-string-with-a-given-numeric-value/solution/java-xian-jiang-mei-wei-she-zhi-cheng-azai-cong-we/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @param k
     * @return
     */
    public String getSmallestStringV1(int n, int k) {
        int chan=k-n;
        int div=chan/25;//div<n
        int rest=chan%25;
        char[] z=new char[div];
        Arrays.fill(z,'z');
        if(rest==0) {
            char[] a=new char[n-div];
            Arrays.fill(a,'a');
            String res=new String(a);
            String re=new String(z);
            return res+re;
        } else {
            char[] a=new char[n-div-1];
            Arrays.fill(a,'a');
            String res=new String(a);
            char s=(char) ('a'+rest);
            String re=new String(z);
            return res+s+re;
        }
    }

}
