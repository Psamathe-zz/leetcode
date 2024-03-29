package com.example.leetcode.challenge.test2021.april.week1;


/**
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 *
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 *
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 *
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 *
 * Example 1:
 *
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 * Example 2:
 *
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 * Note:
 *
 * A will be a permutation of [0, 1, ..., A.length - 1].
 * A will have length in range [1, 5000].
 * The time limit for this problem has been reduced.
 */
public class GlobalLocalInversions {
    public static void main(String[] args) {

    }

    public boolean isIdealPermutation(int[] A) {
        int length = A.length;
        int global = 0;
        int local = 0;
        for (int i = 0; i < length - 1; i++) {

            for (int j = i + 1; j < length; j++) {
                if(A[i] > A[j]){
                    if(j == i + 1)
                        local++;
                    global++;
                }
            }
        }
        return global == local;
    }

    /**
     * faster solution
     * @param A
     * @return
     */
    public boolean isIdealPermutationV0(int[] A) {
        int sum = 0, currSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += i;
            currSum += A[i];
            if (currSum - sum > 1) {
                return false;
            }
        }
        return true;
    }
}
