package com.example.leetcode.challenge.Test2020.may.week4;

/**
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 *
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 *
 * A[i] == B[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
 * Example 2:
 *
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 *
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 *
 *
 * Note:
 *
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 *
 * [2,1]
 * [1,2,1,3,3,2]
 * t:
 * [1,1,2,1,2]
 * [1,3,2,3,1]
 * Output:
 * 2
 * Expected:
 * 3
 */
public class UncrossedLines {
    public static void main(String[] args) {
        int[] A = new int[]{1,1,2,1,2};
        int[] B = new int[]{1,3,2,3,1};
        UncrossedLines uncrossedLines = new UncrossedLines();
        int result = uncrossedLines.maxUncrossedLines(A,B);
        System.out.println(result);
    }

    int[][] dp;
    public int maxUncrossedLines(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        dp = new int[lengthA][lengthB];

        for (int i = 0; i < lengthA; i++) {
            for (int j = 0; j < lengthB; j++) {

                if(A[i] == B[j] ) {
                    dp[i][j] = Math.max(dp[i][j],1);
                    if (i  >= 1 && j  >= 1 )
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);
                } else {
                    if (i >= 1 && j >= 1)
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]);
                    if (j >= 1)
                        dp[i][j] = Math.max(dp[i][j],dp[i][j-1]);
                    if (i >= 1)
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j]);
                }
            }
        }

        return dp[lengthA - 1][lengthB - 1];
    }

    /**
     * faster solution
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLinesV1(int[] A, int[] B) {
        int mx[] = new int[B.length];
        int cm[] = new int[B.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                cm[j] = Math.max((j == 0 ? 0 : cm[j - 1]), mx[j]);
            }

            //System.out.println(Arrays.toString(cm) + " sa");
            for(int j = 0; j < B.length; j++){
                if(A[i] == B[j]){
                    mx[j] = Math.max((j == 0 ? 0 : cm[j - 1]) + 1, mx[j]);
                }
            }


            // System.out.println(Arrays.toString(mx));
        }

        int ans = 0;

        for(int i = 0; i < B.length; i++){
            ans = Math.max(ans, mx[i]);
        }
        return ans;

    }

    public int maxUncrossedLinesV3(int[] A, int[] B) {
        //https://www.youtube.com/watch?v=x_djkQhnuTk
        int m=A.length, n=B.length;
        if (m==0 || n==0)   return 0;
        int[][]dp = new int[m+1][n+1];

        for (int i=1; i<=m; i++){
            for (int j=1; j<=n; j++){
                if (A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }

    public int maxUncrossedLinesV4(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length+1];
        for(int i=1;i<=A.length;i++){
            for(int j=1;j<=B.length;j++){
                dp[i][j] = A[i-1] == B[j-1] ?
                        dp[i-1][j-1] + 1 : Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[A.length][B.length];
    }




    public int maxUncrossedLinesOld(int[] A, int[] B) {

        return help(A,B,0,A.length,0,B.length);
    }
    public int help(int[] A, int[] B,
                    int aStart, int aEnd,
                    int bStart, int bEnd) {
        int result = 0;
        int temp;
        for (int i = aStart; i < aEnd; i++) {
            for (int j = bStart; j < bEnd; j++) {
                if(A[i] == B[j]){
                    temp = 1 + help(A,B,aStart,i,bStart,j) + help(A,B,i+1,aEnd,j+1,bEnd);
                    if(temp > result)
                        result = temp;
                }
            }
        }
        return result;
    }
}
