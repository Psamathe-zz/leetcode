package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 *
 *
 * Example 1:
 *
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 *
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 *
 *
 * Note:
 *
 * 2 <= A.length <= 30000
 * 0 <= A[i] <= 10^6
 * It is guaranteed there is at least one way to partition A as described.
 */
public class PartitionArrayintoDisjointIntervals {
    public static void main(String[] args) {
        int[] A = new int[]{1,1,1,0,6,12};
        PartitionArrayintoDisjointIntervals partitionArrayintoDisjointIntervals = new PartitionArrayintoDisjointIntervals();
        int result = partitionArrayintoDisjointIntervals.partitionDisjoint(A);
        System.out.println(result);
    }

    public int partitionDisjoint(int[] A) {
        int minRight = Arrays.stream(A).min().orElse(Integer.MIN_VALUE);
        int maxLeft = Integer.MIN_VALUE;
        int length;
        for( length = 0; length < A.length - 1; length++){
            if(A[length] > maxLeft)
                maxLeft = A[length];
            if(A[length] == minRight){
                minRight = IntStream.range(length+1, A.length).map(i -> A[i]).min().orElse(Integer.MIN_VALUE);
            }
            if(maxLeft < minRight)
                break;
        }
        return length+1;
    }

    /**
     * fasters solution
     * @param A
     * @return
     */
    public int partitionDisjointV1(int[] A) {
        int curMax = A[0];
        int gloMax = A[0];
        int index = 0;

        for (int i = 0; i < A.length; i++) {
            /*
            According to the requirement
            0. Every element in left is less than or equal to every element in right
            1. left and right are non-empty.
            2. left has the smallest possible size.

            The process will scan from left to right
            if an element is smaller then some elements in its left side, which means 0th requirement doesn't satisfy, then curMax should be updated to gloMax, which
            is the largest elment in the left side ended at i
            */
            if (A[i] < curMax) {
                index = i;
                curMax = gloMax;
            }
            gloMax = Math.max(gloMax, A[i]);

        }
        return index + 1;
    }


    /**
     * less memory
     * @param A
     * @return
     */
    public int partitionDisjointV2(int[] A) {
        int[] min = new int[A.length];
        min[min.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            min[i] = Math.min(A[i], min[i + 1]);
        }
        int max = A[0];
        // if (max >= min[1]) return 0;
        for (int i = 0; i < A.length - 1; i++) {
            max = Math.max(max, A[i]);
            if (max <= min[i + 1])
                return i + 1;
        }
        return -1;
    }
}
