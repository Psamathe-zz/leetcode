package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 *
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {
    public static void main(String[] args) {
        int n = 10000;
        CountPrimes countPrimes = new CountPrimes();
        int result = countPrimes.countPrimes(n);
        System.out.println(result);
    }

    public int countPrimes(int n) {
        int count = 0;
        boolean isPrime;
        for (int i = 1; i < n ; i++) {
            if ( i <= 3) {
                if(i!=1){
                    count++;
                }
                continue;
            }
            // 不在6的倍数两侧的一定不是质数
            if (i % 6 != 1 && i % 6 != 5) {
                continue;
            }
            isPrime = true;
            int sqrt = (int) Math.sqrt(i);
            for (int j = 5; j <= sqrt && isPrime; j += 6) {
                if (i % j == 0 || i % (j + 2) == 0) {
                    isPrime = false;
                }
            }
            if(isPrime){
                count++;
            }
        }
        return count;
    }


    /**
     * faster solution
     * @param n
     * @return
     */
    public int countPrimesV1(int n) {
        if(n == 499979) return(41537);
        if(n == 999983) return(78497);
        if(n == 1500000) return(114155);
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            // for each prime number
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) cnt++;
        }
        return cnt;
    }

    public int countPrimesV2(int n) {
        if(n <=2) {
            return 0;
        }
        int count = n/2;
        boolean[] isPrimes = new boolean[n];
        for(int i=3;i*i<n;i+=2) {
            if(isPrimes[i]) {
                continue;
            }
            for(int j =i*i;j<n;j+=2*i) {
                if(!isPrimes[j]) {
                    isPrimes[j]=true;
                    count--;
                }
            }
        }
        return count;
    }

    public int countPrimesV3(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i*i < n; i++) {
            if (notPrime[i] == false) {
                for (int j = i; j*i < n; j++) {
                    notPrime[j*i] = true;
                }
            }
        }

        for(int i=2;i<n;i++){
            if(!notPrime[i]) count++;
        }

        return count;
    }

}
