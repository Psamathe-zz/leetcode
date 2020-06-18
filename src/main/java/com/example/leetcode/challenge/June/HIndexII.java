package com.example.leetcode.challenge.June;

/**
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 * Example:
 *
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
 *              received 0, 1, 3, 5, 6 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note:
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * Follow up:
 *
 * This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
 * Could you solve it in logarithmic time complexity?
 */
public class HIndexII {
    public static void main(String[] args) {
        int[] citations = new int[]{0,1,3,5,6};
        HIndexII hIndexII = new HIndexII();
        int result = hIndexII.hIndex(citations);
        System.out.println(result);
    }

    public int hIndex(int[] citations) {
        int length = citations.length;
        if(length == 0)
            return 0;

        int index = length - 1;
        int max = Integer.MIN_VALUE;
        while (index >= 0){
            max = Math.max(max,Math.min(length - index,citations[index]));
            index--;

        }
        return max;

    }

    public int hIndexV1(int[] citations) {
        int length = citations.length;
        if(length == 0)
            return 0;

        int index = length - 1;
        int max = 0;
        while (index >= 0){
            int num = length-index;
            if(num <= citations[index]) {
                max=num;
            }
            index--;

        }
        return max;
    }

    public int hIndexV2(int[] citations) {
        int level = 0, n = citations.length;
        int left = 0, right = n - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(citations[mid] >= n - mid) {  //一旦发现引用次数大于 n-mid，就在左半部查找可能更大的 level
                level = n - mid;
                right = mid - 1;
            }
            else
                left = mid + 1;  //说明左半部没有更大的 level，则在右半部查找
        }
        return level;
    }

}
