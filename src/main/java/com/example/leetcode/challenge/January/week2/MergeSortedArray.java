package com.example.leetcode.challenge.January.week2;

public class MergeSortedArray {
    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int indexA = m - 1;
        int indexB = n - 1;
        while (index >= 0){
            if(indexB < 0 || (indexA >= 0 && nums1[indexA] >= nums2[indexB])){
                nums1[index] = nums1[indexA];
                indexA--;
            } else {
                nums1[index] = nums2[indexB];
                indexB--;
            }
            index--;
        }
    }

    public void mergeV1(int[] A, int m, int[] B, int n) {
        int p = m-1, q = n-1, i = m+n-1;
        while ( q>=0 ) {
            if ( p<0 || B[q] >= A[p] )
                A[i--] = B[q--];
            else
                A[i--] = A[p--];
        }
    }
}
