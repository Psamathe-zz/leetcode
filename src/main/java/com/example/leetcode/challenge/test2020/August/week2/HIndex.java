package com.example.leetcode.challenge.test2020.August.week2;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 *
 * Example:
 *
 * Input: citations = [[3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 *              received 3, 0, 6, 1, 5 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class HIndex {
    public static void main(String[] args) {
        int[] citations = {100};
        HIndex hIndex = new HIndex();
        int res =hIndex.hIndex(citations);
        System.out.println(res);
    }

    public int hIndex(int[] citations) {
        int length = citations.length;
        List<Integer> list = Arrays.stream(citations).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        int i = 0;
        for(Integer val : list){
            if(i >= val)
                return i;
            i++;
        }
        return length;
    }


    /**
     * faster solution
     * @param citations
     * @return
     */
    public int hIndexV1(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // 计数
        for (int c: citations)
            papers[Math.min(n, c)]++;
        // 找出最大的 k
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }
}
