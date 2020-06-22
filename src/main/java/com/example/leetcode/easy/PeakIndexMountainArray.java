package com.example.leetcode.easy;

/**
 * Let's call an array A a mountain if the following properties hold:
 *
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 *
 * Example 1:
 *
 * Input: [0,1,0]
 * Output: 1
 *
 * Example 2:
 *
 * Input: [0,2,1,0]
 * Output: 1
 *
 * Input
 * [3,4,5,1]
 * Output
 * 1
 * Expected
 * 2
 */
public class PeakIndexMountainArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3,4,5,1};
        PeakIndexMountainArray peakIndexMountainArray = new PeakIndexMountainArray();
        int result = peakIndexMountainArray.peakIndexInMountainArray(nums);
        System.out.println(result);
    }

    public int peakIndexInMountainArray(int[] A) {
        int result = 0;
        int max = 0;
        int maxCount = 0;
        for(int i = 0; i< A.length;i++){
            if(A[i]>max){
               max = A[i];
               maxCount= 0;
               result = i;
            }
            if(A[i] == max)
                maxCount++;
        }
        if(result>0 && result<A.length-1 && maxCount==1)
            return result;
        else
            return 0;
    }

    /**
     * faster solution
     * @param A
     * @return
     */
    public int peakIndexInMountainArrayV2(int[] A) {
        int left = 0, right = A.length - 1;

        while (left < right) {
            int middle = (left + right) / 2;

            if (A[middle] < A[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    /**
     * less memeory
     * @param A
     * @return
     */
    public int peakIndexInMountainArrayV3(int[] A) {
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
