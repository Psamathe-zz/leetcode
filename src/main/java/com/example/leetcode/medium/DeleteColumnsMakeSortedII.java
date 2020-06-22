package com.example.leetcode.medium;

import java.util.HashSet;

/**
 * We are given an array A of N lowercase letter strings, all of the same length.
 *
 * Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
 *
 * For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].
 *
 * Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).
 *
 * Return the minimum possible value of D.length.
 *
 *
 *
 * Example 1:
 *
 * Input: ["ca","bb","ac"]
 * Output: 1
 * Explanation:
 * After deleting the first column, A = ["a", "b", "c"].
 * Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
 * We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
 * Example 2:
 *
 * Input: ["xc","yb","za"]
 * Output: 0
 * Explanation:
 * A is already in lexicographic order, so we don't need to delete anything.
 * Note that the rows of A are not necessarily in lexicographic order:
 * ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
 * Example 3:
 *
 * Input: ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation:
 * We have to delete every column.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 */
public class DeleteColumnsMakeSortedII {
    public static void main(String[] args) {

        String[] toTest = new String[]{"abx","agz","bgc","bfc"};
        DeleteColumnsMakeSortedII deleteColumnsMakeSortedII = new DeleteColumnsMakeSortedII();
        int result = deleteColumnsMakeSortedII.minDeletionSize(toTest);
        System.out.println(result);
    }

    public int minDeletionSize(String[] A) {
        int result = 0;
        boolean equal;
        boolean less;
        boolean[] issettle = new boolean[A.length - 1];
        for(int i=0;i<A[0].length();i++){
            equal = false;
            less = false;
            for(int j=0;j<A.length - 1;j++){

                if(A[j+1].charAt(i) - A[j].charAt(i) < 0 && issettle[j] == false){
                    result++;
                    less = true;
                    break;
                }
                if(A[j+1].charAt(i) - A[j].charAt(i) == 0 && issettle[j] == false){
                    equal = true;
                }
            }
            if(equal == false && less == false){
                break;
            }
            if(equal == true && less == false){
                for(int j=0;j<A.length - 1 ;j++){
                    if(A[j+1].charAt(i) - A[j].charAt(i) > 0 && issettle[j] == false){
                        issettle[j] = true;
                    }
                }
            }
        }

        return result;
    }

    /**
     *
     * @param A
     * @return
     */
    public int minDeletionSizeV2(String[] A) {
        HashSet<Integer> toBeDeleted = new HashSet<>();
        for(int row = 1; row < A.length; row++) {
            //Match current & prev row for all the columns
            for(int col = 0; col < A[row].length(); col++) {
                if(toBeDeleted.contains(col) || A[row - 1].charAt(col) == A[row].charAt(col)) continue;
                if(A[row - 1].charAt(col) > A[row].charAt(col)) {
                    /**
                     If current row character is less than prev row character for a column,
                     add that column to the Hashset
                     */
                    toBeDeleted.add(col);

                    /**
                     Restart row, cause deleting column might change ordering of previously ordered rows
                     */
                    row = 0;
                }
                break;
            }
        }
        return toBeDeleted.size();
    }


}
