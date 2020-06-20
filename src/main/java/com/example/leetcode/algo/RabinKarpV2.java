package com.example.leetcode.algo;

public class RabinKarpV2 {
    private static long M = 1000000000000000003L;   //集合空间大小，一个很大的素数
    private static int R = 31;  //进制
    {
        //M = BigInteger.valueOf((long) Math.pow(10, 18)).nextProbablePrime().longValue();
    }

    private long patternHash;
    private long RK; // R^K % M，用于减去第一个数时的计算
    private int K; // 模式字符串的长度

    public RabinKarpV2(String pattern) {
        patternHash = hash(pattern);
        K = pattern.length();

        // 计算RM
        RK = 1;
        for (int i = 0; i < K; i++) {
            RK = (R * RK) % M;
        }
    }

    public int search(String txt) {
        long substrHash = hash(txt, 0, K);
        if (substrHash == patternHash) return 0;    //一开始就匹配成功
        for (int i = 1; i + K <= txt.length(); i++) {
            // H(i+1) % M = [H(i) % M * R + s[i+k] - s[i] * R^k % M + M] % M
            substrHash = (substrHash * R  + txt.charAt(i + K - 1) - txt.charAt(i - 1) * RK % M  + M ) % M;
            if (substrHash == patternHash)
                return i;
        }
        return -1;
    }

    // Horner rule 计算字符串hash值
    private long hash(String str, int start, int length) {
        long hash = 0;
        for (int i = start; i < length; i++) {
            hash = (hash * R + str.charAt(i)) % M;
        }
        return hash;
    }

    private long hash(String str) {
        return hash(str, 0, str.length());
    }

    public static void main(String[] args) {
        String pattern = "y similar t";
        RabinKarpV2 rk = new RabinKarpV2(pattern);
        String txt = "Technically, this algorithm is only similar to the true number in a non-decimal";
        System.out.println(txt);
        int index = rk.search(txt);
        if (index >= 0) {
            for (int i = 0; i < index; i++) {
                System.out.print(" ");
            }
            System.out.print(pattern);
        }

    }
}
