package com.example.leetcode.challenge.test2020.October.week3;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the ith domino, so that A[i] and B[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 *
 * If it cannot be done, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 * Example 2:
 *
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 *
 * Constraints:
 *
 * 2 <= A.length == B.length <= 2 * 104
 * 1 <= A[i], B[i] <= 6
 */
public class MinimumDominoRotations {
    public static void main(String[] args) {
        int[] A = new int[]{2,1,2,4,2,2};
        int[] B = new int[]{5,2,6,2,3,2};
        MinimumDominoRotations minimumDominoRotations = new MinimumDominoRotations();
        minimumDominoRotations.minDominoRotations(A,B);
    }

    /**
     * http://www.noteanddata.com/leetcode-1007-Minimum-Domino-Rotations-For-Equal-Row-java-solution-note-2.html
     * @param A
     * @param B
     * @return
     * 无论怎么换位置，如果最后可以让某一行的数字完全一样，每个位置上只有两个可能，这个数要么是A的，要么是B的。 那么，对第0个数字来说，要么是A[0], 要么是B[0]。
     * 那么，不管在那一行，后面的数字要么和A[0]相同， 要么和B[0]相同， 否则就不能达到效果。
     * 所以，
     * –遍历数组一次， 如果可以在A或者B里面在每个位置上都找到和A[0]相同的元素，那么就可以把某一行全部变成A[0]
     * –再遍历数组一次， 如果可以在A或者B里面在每个位置上都找到和B[0]相同的元素，那么就可以把某一行全部变成B[0]
     * 然后，在两次遍历的时候都分别计数，看看有几个一样的， 那么n-count就是需要换的个数。
     */
    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        if(n <= 1)
            return 0;
        for(int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); ++i) {
            if(A[i] == A[0])
                a++;
            if(B[i] == A[0])
                b++;
            if(i == n-1)
                return Math.min(n-a, n-b);
        }
        for(int i = 0, a = 0, b = 0; i < n && (A[i] == B[0] || B[i] == B[0]); ++i) {
            if(A[i] == B[0])
                a++;
            if(B[i] == B[0])
                b++;
            if(i == n-1)
                return Math.min(n-a, n-b);
        }
        return -1;
    }

    /**
     * faster solution
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotationsV1(int[] A, int[] B) {
        int res = A.length+1;
        if(A[0] == B[0]){
            res = Math.min(helper(A, B, 1, A[0]), helper(B, A, 1, A[0]));
        }else{
            // use A[0] && rotate to A
            res = Math.min(res, helper(A, B, 1, A[0]));

            // use A[0] && rotate to B
            res = Math.min(res, 1+helper(B, A, 1, A[0]));

            // use B[0] && rotate to A
            res = Math.min(res, 1+helper(A, B, 1, B[0]));

            // use B[0] && rotate to B
            res = Math.min(res, helper(B, A, 1, B[0]));
        }

        return res > A.length ? -1 : res;
    }

    public int helper(int[] A, int[] B, int idx, int val){
        int cnt = 0;
        for(int i=idx; i<A.length; i++){
            if(A[i] == val) ;
            else if(B[i] != val) return A.length+1;
            else cnt++;
        }
        return cnt;

    }
}
