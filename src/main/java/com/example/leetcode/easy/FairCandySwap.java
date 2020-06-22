package com.example.leetcode.easy;

import java.util.Arrays;

/**
 * Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.
 *
 * Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)
 *
 * Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.
 *
 * If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,1], B = [2,2]
 * Output: [1,2]
 * Example 2:
 *
 * Input: A = [1,2], B = [2,3]
 * Output: [1,2]
 * Example 3:
 *
 * Input: A = [2], B = [1,3]
 * Output: [2,3]
 * Example 4:
 *
 * Input: A = [1,2,5], B = [2,4]
 * Output: [5,4]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * It is guaranteed that Alice and Bob have different total amounts of candy.
 * It is guaranteed there exists an answer.
 */
public class FairCandySwap {
    public static void main(String[] args) {
        int[] A = new int[]{1,2,5} ;
        int[] B = new int[]{2,4} ;
        FairCandySwap fairCandySwap = new FairCandySwap();
        int[] result = fairCandySwap.fairCandySwap(A,B);
        System.out.println(result);
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int valueA = A[0];
        int valueB = B[0];
        boolean notFound = true;
        for( int i = 0; i< A.length && notFound;i++){
            for (int j = 0; j <B.length && notFound; j++) {
                if(sumA - A[i] + B[j] == sumB + A[i] - B[j] ) {
                    valueA = A[i];
                    valueB = B[j];
                    notFound = false;
                }
            }
        }
        return new int[]{valueA,valueB};
    }

    /**
     * faster solution
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwapV1(int[] A, int[] B) {
        int candiesWithA = 0, candiesWithB = 0;
        boolean frequencyB[] = new boolean[100010];
        int answer[] = new int[2];

        for(int i=0;i<A.length;i++)
            candiesWithA += A[i];

        for(int i=0;i<B.length;i++){
            candiesWithB += B[i];
            frequencyB[B[i]] = true;
        }

        int actualCandies = (candiesWithB - candiesWithA)/2;
        for(int i=0;i<A.length;i++){
            int pair = A[i]+actualCandies;
            if(pair >=0 && pair<=100010 && frequencyB[pair]){
                answer[0] = A[i];
                answer[1] = pair;
            }

        }
        return answer;
    }
}
