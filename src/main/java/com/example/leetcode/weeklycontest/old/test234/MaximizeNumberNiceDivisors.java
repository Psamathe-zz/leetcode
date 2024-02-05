package com.example.leetcode.weeklycontest.old.test234;

import java.math.BigInteger;

/**
 * You are given a positive integer primeFactors. You are asked to construct a positive integer n that satisfies the following conditions:
 *
 * The number of prime factors of n (not necessarily distinct) is at most primeFactors.
 * The number of nice divisors of n is maximized. Note that a divisor of n is nice if it is divisible by every prime factor of n. For example, if n = 12, then its prime factors are [2,2,3], then 6 and 12 are nice divisors, while 3 and 4 are not.
 * Return the number of nice divisors of n. Since that number can be too large, return it modulo 109 + 7.
 *
 * Note that a prime number is a natural number greater than 1 that is not a product of two smaller natural numbers. The prime factors of a number n is a list of prime numbers such that their product equals n.
 *
 *
 *
 * Example 1:
 *
 * Input: primeFactors = 5
 * Output: 6
 * Explanation: 200 is a valid value of n.
 * It has 5 prime factors: [2,2,2,5,5], and it has 6 nice divisors: [10,20,40,50,100,200].
 * There is not other value of n that has at most 5 prime factors and more nice divisors.
 * Example 2:
 *
 * Input: primeFactors = 8
 * Output: 18
 *
 */
public class MaximizeNumberNiceDivisors {
    public static void main(String[] args) {
        MaximizeNumberNiceDivisors maximizeNumberNiceDivisors = new MaximizeNumberNiceDivisors();
        maximizeNumberNiceDivisors.maxNiceDivisors(5);
    }

    /**
     * https://leetcode-cn.com/problems/maximize-number-of-nice-divisors/solution/shu-xue-tui-dao-fa-class-solution-public-oviz/
     * @return
     */
    public int maxNiceDivisors(int primeFactors) {
        double modN =  (1e9 + 7);
        if (primeFactors <= 3) {
            return primeFactors;
        } else {
            int a = primeFactors / 3, b = primeFactors % 3;
            if (b == 0) {
                return (int) ((myPow(3, a) ) % modN);
            } else if (b == 1) {
                return (int) ((myPow(3, a - 1) * 4) % modN);
            } else {
                return (int) ((myPow(3, a) * 2) % modN);
            }
        }
    }
    static long myPow( long x, int n) {
        int modN = (int) (1e9 + 7);
        long ans = 1;
        n = Math.abs(n);
        while(n!=0){
            if(n % 2 != 0){
                ans *= x;
                ans %= modN;
            }
            x *= x;
            x %= modN;
            n /= 2;
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/maximize-number-of-nice-divisors/solution/java-kuai-su-mi-by-sikichen-rhrs/
     * @param primeFactors
     * @return
     */
    public int maxNiceDivisorsV0(int primeFactors) {
        int MOD = 1000000007;
        int cnt = primeFactors / 3;
        int cnt2 = 0;
        if(primeFactors % 3 == 0){
            cnt2 = 0;
        }
        if(primeFactors % 3 == 1){
            if(cnt != 0){
                cnt--;
                cnt2 = 2;
            }else{
                cnt2 = 0;
            }
        }
        if(primeFactors % 3 == 2){
            cnt2 = 1;
        }
        return (int) ((qpow(3, cnt) * qpow(2,cnt2)) % MOD);
    }

    public double qpow(int p, int q){
        int MOD = 1000000007;
        long res = 1L;
        long base = p;
        while(q != 0){
            if((q & 1) != 0){
                res = res * base % MOD;
            }
            base = base * base % MOD;
            q >>= 1;
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/maximize-number-of-nice-divisors/solution/javazi-dai-kuai-su-mi-shuang-bai-jie-jue-qg9u/
     * @param primeFactors
     * @return
     */
    public int maxNiceDivisorsV1(int primeFactors) {
        if(primeFactors==1) //之所以特判，是因为-4那里会变成负数
            return 1;
        BigInteger b=new BigInteger("3");//这道题就是尽可能地分出3，b作为底数 等下去快速幂
        BigInteger mod=new BigInteger("1000000007");//取余的数
        if(primeFactors%3==1)//如果时留下一个1，则有更好的解，因为2*2>1*3
            b=b.modPow(new BigInteger(String.valueOf((primeFactors-4)/3)),mod).multiply(new BigInteger("4"));
        else if(primeFactors%3==2)
            b=b.modPow(new BigInteger(String.valueOf((primeFactors-2)/3)),mod).multiply(new BigInteger("2"));
        else b=b.modPow(new BigInteger(String.valueOf(primeFactors/3)),mod);
        return Integer.parseInt(b.mod(mod).toString());
    }





}
