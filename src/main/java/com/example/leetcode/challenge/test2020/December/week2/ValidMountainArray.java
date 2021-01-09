package com.example.leetcode.challenge.test2020.December.week2;


/**
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < A[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1]
 * Output: false
 * Example 2:
 *
 * Input: arr = [3,5,5]
 * Output: false
 * Example 3:
 *
 * Input: arr = [0,3,2,1]
 * Output: true
 * [9,8,7,6,5,4,3,2,1,0]
 *
 */
public class ValidMountainArray {
    public static void main(String[] args) {

    }

    public boolean validMountainArray(int[] arr) {
        int length = arr.length;
        if(length < 3)
            return false;
        int pre = arr[1] - arr[0];
        if(pre <= 0)
            return false;
        int count = 0;
        for (int i = 2; i < length; i++) {
            if(arr[i] == arr[i-1])
                return false;
            else{
                if(pre * (arr[i] - arr[i-1]) < 0)
                    count++;
                if(count > 1)
                    return false;
                pre = arr[i] - arr[i-1];
            }
        }
        return count == 1;
    }


    /**
     * faster solution
     * @param arr
     * @return
     */
    public boolean validMountainArrayV1(int[] arr) {
        int n = arr.length;
        int i = 0;

        while(i<n-1 && arr[i] < arr[i+1]){
            i++;
        }
        if(i ==0 || i == n-1) return false;

        while(i<n-1 && arr[i] > arr[i+1]) i++;

        return (i == n-1) ? true : false;
    }
}
