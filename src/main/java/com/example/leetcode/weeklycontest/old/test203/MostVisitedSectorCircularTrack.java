package com.example.leetcode.weeklycontest.old.test203;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n and an integer array rounds. We have a circular track which consists of n sectors labeled from 1 to n. A marathon will be held on this track, the marathon consists of m rounds. The ith round starts at sector rounds[i - 1] and ends at sector rounds[i]. For example, round 1 starts at sector rounds[0] and ends at sector rounds[1]
 *
 * Return an array of the most visited sectors sorted in ascending order.
 *
 * Notice that you circulate the track in ascending order of sector numbers in the counter-clockwise direction (See the first example).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, rounds = [1,3,1,2]
 * Output: [1,2]
 * Explanation: The marathon starts at sector 1. The order of the visited sectors is as follows:
 * 1 --> 2 --> 3 (end of round 1) --> 4 --> 1 (end of round 2) --> 2 (end of round 3 and the marathon)
 * We can see that both sectors 1 and 2 are visited twice and they are the most visited sectors. Sectors 3 and 4 are visited only once.
 * Example 2:
 *
 * Input: n = 2, rounds = [2,1,2,1,2,1,2,1,2]
 * Output: [2]
 * Example 3:
 *
 * Input: n = 7, rounds = [1,3,5,7]
 * Output: [1,2,3,4,5,6,7]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= m <= 100
 * rounds.length == m + 1
 * 1 <= rounds[i] <= n
 * rounds[i] != rounds[i + 1] for 0 <= i < m
 * 3
 * [3,2,1,2,1,3,2,1,2,1,3,2,3,1]
 */
public class MostVisitedSectorCircularTrack {
    public static void main(String[] args) {
        MostVisitedSectorCircularTrack mostVisitedSectorCircularTrack = new MostVisitedSectorCircularTrack();
        mostVisitedSectorCircularTrack.mostVisited(3,new int[]{3,2,1,2,1,3,2,1,2,1,3,2,3,1});
    }

    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int length = rounds.length;
        int[] count = new int[n + 1];

        for (int i = 1; i < length; i++) {
            if(rounds[i]>rounds[i-1]) {
                for (int j = rounds[i - 1]; j < rounds[i]; j++) {
                    count[j]++;
                }
            } else {
                for (int j = rounds[i - 1]; j <= n ; j++) {
                    count[j]++;
                }
                for (int j = 1; j < rounds[i] ; j++) {
                    count[j]++;
                }
            }
        }
        count[rounds[length-1]]++;
        int max = 0;
        for (int i = 1; i <= n ; i++) {
            max = Math.max(max,count[i]);
        }
        for (int i = 1; i <= n ; i++) {
            if(count[i] == max)
                res.add(i);
        }
        return res;
    }


    /**
     * faster solution
     * @param n
     * @param rounds
     * @return
     */
    public List<Integer> mostVisitedV1(int n, int[] rounds) {
        int len = rounds.length, fr = rounds[0], to = rounds[len - 1];
        List<Integer> res = new ArrayList<>();
        if (to >= fr) {     // no circle, such as [1,3,1,2]
            for (int i = fr; i <= to; i++) res.add(i);
        } else {            // cross a circle, such as [2,3,2,1]
            for (int i = 1; i <= n; i++) {
                if (i == to + 1) i = fr;
                res.add(i);
            }
        }
        return res;
    }
}
