package com.example.leetcode.medium;

public class BinaryStringWithSubstrings {
    public static void main(String[] args) {
        String S = "0110";
        int N = 4;
        BinaryStringWithSubstrings binaryStringWithSubstrings = new BinaryStringWithSubstrings();
        boolean result = binaryStringWithSubstrings.queryString(S,N);
        System.out.println(result);
    }
    public boolean queryString(String S, int N) {
        for (int i = 1; i <= N ; i++) {
            if(!S.contains(Integer.toBinaryString(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * faster and less memory
     * @param S
     * @param N
     * @return
     */
    public boolean queryStringV1(String S, int N) {
        for (int i = N; i > N / 2; i--) {
            if (!S.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
