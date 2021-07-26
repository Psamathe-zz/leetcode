package com.example.leetcode.challenge.test2021.July.week4;

/**
 * Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Example 2:
 *
 * Input: n = 1
 * Output: 2
 * Example 3:
 *
 * Input: n = 2
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 */
public class NonnegativeIntegers {
    public static void main(String[] args) {
        NonnegativeIntegers nonnegativeIntegers = new NonnegativeIntegers();
        int res = nonnegativeIntegers.findIntegersV1(7);
        System.out.println(res);
    }

    /**
     * https://blog.csdn.net/magicbean2/article/details/78968556
     * @param n
     * @return
     */
    public int findIntegers(int n) {
        int[] f = new int[32];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < 32; ++i) {
            f[i] = f[i-1] + f[i-2];
        }
        int ans = 0, k = 30, pre_bit = 0;
        while (k >= 0) {
            if ((n & (1 << k)) != 0) {   // find the first '1' from right to left
                ans += f[k];
                if (pre_bit != 0) {
                    return ans;
                }
                pre_bit = 1;
            }
            else {
                pre_bit = 0;
            }
            --k;
        }
        return ans + 1;
    }

    /**
     * easy solution
     * https://blog.csdn.net/wyxwyx469410930/article/details/84498525
     * @param num
     * @return
     */
    public int findIntegersV1(int num) {
        StringBuilder sb=new StringBuilder(Integer.toBinaryString(num)).reverse();

        int n=sb.length();

        int a[]=new int[n];
        int b[]=new int[n];

        a[0]=1;
        b[0]=1;

        for(int i=1;i<n;i++){
            a[i]=a[i-1]+b[i-1];
            b[i]=a[i-1];
        }

        int res= a[n-1]+b[n-1];

        for(int i=n-2;i>=0;i--){
            if(sb.charAt(i)=='0' && sb.charAt(i+1)=='0')
                res=res-b[i];
            if(sb.charAt(i)=='1' && sb.charAt(i+1)=='1')
                break;
        }

        return res;
    }
}
