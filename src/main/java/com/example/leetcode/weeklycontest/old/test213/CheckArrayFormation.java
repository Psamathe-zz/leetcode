package com.example.leetcode.weeklycontest.old.test213;


/**
 * You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].
 *
 * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [85], pieces = [[85]]
 * Output: true
 * Example 2:
 *
 * Input: arr = [15,88], pieces = [[88],[15]]
 * Output: true
 * Explanation: Concatenate [15] then [88]
 * Example 3:
 *
 * Input: arr = [49,18,16], pieces = [[16,18,49]]
 * Output: false
 * Explanation: Even though the numbers match, we cannot reorder pieces[0].
 * Example 4:
 *
 * Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * Output: true
 * Explanation: Concatenate [91] then [4,64] then [78]
 * Example 5:
 *
 * Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= pieces.length <= arr.length <= 100
 * sum(pieces[i].length) == arr.length
 * 1 <= pieces[i].length <= arr.length
 * 1 <= arr[i], pieces[i][j] <= 100
 * The integers in arr are distinct.
 * The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */
public class CheckArrayFormation {
    public static void main(String[] args) {
        int[] arr = new int[]{91,4,64,78};
        int[][] pieces = new int[][]{
                {78},
                {4,64},
                {91}
        };
        CheckArrayFormation checkArrayFormation = new CheckArrayFormation();
        boolean res = checkArrayFormation.canFormArray(arr,pieces);
        System.out.println(res);
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int length = arr.length;
        int size = pieces.length;
        int index = 0;
        boolean findNext ;
        int rest = size;
        do{
            findNext = false;
            for (int i = 0; i < size; i++) {
                if(index < length && arr[index] == pieces[i][0]){
                    findNext = true;
                    for (int j = 0; j < pieces[i].length; j++) {
                        if(index < length && pieces[i][j] == arr[index]){
                            index++;
                        }else {
                            return false;
                        }
                    }
                    rest--;
                }
            }
            if(!findNext){
                return false;
            }
        } while (findNext && rest>0);
        return true;
    }


    public boolean canFormArrayV1(int[] arr, int[][] pieces) {
        int n = arr.length;
        int j = 0;
        boolean searching = true;
        while (searching && j < n) {
            searching = false;
            for (int[] p : pieces) {
                if (p[0] == arr[j]) {
                    for (int i = 0; i < p.length; i++) {
                        if (p[i] != arr[j]) {
                            return false;
                        }
                        j++;
                    }
                    searching = true;
                    break;
                }
            }
        }
        return j == n;
    }
}
