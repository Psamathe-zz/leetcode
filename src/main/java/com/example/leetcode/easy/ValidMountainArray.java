package com.example.leetcode.easy;

import com.example.leetcode.medium.ValidateBinarySearchTree;

/**
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 *
 * Recall that A is a mountain array if and only if:
 *
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1]
 * Output: false
 * Example 2:
 *
 * Input: [3,5,5]
 * Output: false
 * Example 3:
 *
 * Input: [0,3,2,1]
 * Output: true
 * [0,1,2,3,4,5,6,7,8,9]
 * [0,1,2,4,2,1]
 *
 */
public class ValidMountainArray {
    public static void main(String[] args) {
        int[] A = new int[]{0,1,2,4,2,1};
        ValidMountainArray validMountainArray = new ValidMountainArray();
        boolean result = validMountainArray.validMountainArray(A);
        System.out.println(result);
    }

    public boolean validMountainArray(int[] A) {
        boolean increasing = false;
        boolean decreasing= false;
        int length = A.length;
        if(length <= 2)
            return false;
        int preValue = A[0];
        for (int i = 1; i < A.length; i++) {
            if(preValue == A[i])
                return false;
            if(preValue < A[i]){
                if(decreasing)
                    return false;
                increasing=true;
            }
            if (preValue > A[i]) {
                if(!increasing)
                    return false;
                decreasing=true;
            }
            preValue = A[i];
        }
        return decreasing;
    }


    /**
     * faster solution
     * @param A
     * @return
     */
    public boolean validMountainArrayV1(int[] A) {
        if (A.length < 3) return false;
        int start = 0;
        int end = A.length-1;
        while (start < end) {
            if (A[start+1] > A[start]) {
                start++;
            } else if (A[end-1] > A[end]) {
                end--;
            } else {
                break;
            }
        }
        return start != 0 && end != A.length-1 && start == end;
    }
}
