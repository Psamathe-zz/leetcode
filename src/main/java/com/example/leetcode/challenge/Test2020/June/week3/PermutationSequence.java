package com.example.leetcode.challenge.June.week3;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 *
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class PermutationSequence {

    public static void main(String[] args) {
        int n = 3;
        int k = 3;
        PermutationSequence permutationSequence = new PermutationSequence();
        String result = permutationSequence.getPermutation(n,k);
        System.out.println(result);
    }

    public String getPermutation(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        String num = "123456789";
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i)
            f[i] = f[i - 1] * i;
        --k;
        for (int i = n; i >= 1; --i) {
            int j = k / f[i - 1];
            k %= f[i - 1];
            stringBuilder.append(num.charAt(j));
            num = num.substring(0,j) + num.substring(j+1);
        }
        return stringBuilder.toString();
    }


    public String getPermutationV1(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];

        k = k - 1;
        int factor = 1;
        for(int i = 1; i < n; i++) {
            factor *= i;
        }

        for(int i = 0; i < n; i++) {
            int index = k / factor;
            k %= factor;
            for(int j = 0; j < n; j++) {
                if(used[j] == false) {
                    if(index == 0) {
                        used[j] = true;
                        sb.append((char)('0' + j + 1));
                        break;
                    } else {
                        index--;
                    }
                }
            }

            if(i < n - 1) {
                factor = factor / (n - 1 - i);
            }
        }

        return sb.toString();
    }

}
