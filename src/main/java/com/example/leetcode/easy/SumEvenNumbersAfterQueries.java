package com.example.leetcode.easy;

/**
 * We have an array A of integers, and an array queries of queries.
 *
 * For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].
 * Then, the answer to the i-th query is the sum of the even values of A.
 *
 * (Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)
 *
 * Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
 * Output: [8,6,2,4]
 * Explanation:
 * At the beginning, the array is [1,2,3,4].
 * After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
 * After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
 * After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
 * After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * 1 <= queries.length <= 10000
 * -10000 <= queries[i][0] <= 10000
 * 0 <= queries[i][1] < A.length
 */
public class SumEvenNumbersAfterQueries {
    public static void main(String[] args) {

    }

    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        int pre;
        int sumEvent = 0;
        for (int i = 0; i < A.length; i++) {
            sumEvent += A[i]%2==0?A[i]:0;
        }
        int index = 0;
        for (int[] query : queries){
            pre = A[query[1]];
            A[query[1]] = pre + query[0];
            if(( A[query[1]] ) % 2 == 0){
                if(pre % 2 == 0){
                    sumEvent += query[0];
                } else {
                    sumEvent += A[query[1]];
                }
            } else {
                if(pre % 2 == 0){
                    sumEvent -= pre;
                }
            }
            result[index] = sumEvent;
            index++;
        }
        return result;
    }


    /**
     * faster solution
     * @param a
     * @param q
     * @return
     */
    public int[] sumEvenAfterQueriesV1(int[] a, int[][] q) {

        int ret[] = new int[q.length];

        int es = 0;
        for(int i=0;i<a.length;i++){
            if((a[i]&1)==0)
                es += a[i];
        }

        for(int i=0;i<q.length;i++){

            int val = q[i][0];
            int ind = q[i][1];

            if((a[ind]&1)==0)
                es -= a[ind];

            a[ind] += val;

            if((a[ind]&1)==0)
                es += a[ind];

            ret[i] = es;
        }

        return ret;
    }
}
