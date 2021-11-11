package com.example.leetcode.biweeklycontest.contest64;

import java.util.*;

/**
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
 *
 * Return this maximum sum.
 *
 * Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 * Example 2:
 *
 * Example 1 Diagram
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 * Example 3:
 *
 *
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 *
 */
public class TwoBestNonOverlappingEvents {
    public static void main(String[] args) {
        TwoBestNonOverlappingEvents twoBestNonOverlappingEvents = new TwoBestNonOverlappingEvents();
        twoBestNonOverlappingEvents.maxTwoEvents(new int[][]{
                {1,3,2},
                {4,5,2},
                {2,4,3},
        });
    }

    public int maxTwoEvents(int[][] events) {
        int len = events.length;

        //sort by eventVal(desc)
        Arrays.sort(events, (a, b) -> b[2] - a[2]);

        TreeMap<Integer, int[]> treeMap = new TreeMap<>(Collections.reverseOrder()); //key: value(desc), value: event
        TreeSet<Integer> retSet = new TreeSet<>(); //combineValue(desc)
        int maxVal = events[0][2];

        // select one event
        retSet.add(maxVal);

        // select two events
        out: for(int i = 0; i < len; i++) {
            int[] event = events[i];
            int e1Start = event[0];
            int e1End = event[1];
            int e1Val = event[2];
            for (Map.Entry<Integer, int[]> entry : treeMap.entrySet()) {
                int[] e2 = entry.getValue();
                int e2Start = e2[0];
                int e2End = e2[1];
                int e2Val = e2[2];

                if(e1Val + e2Val < maxVal) {
                    // pruning: worst than one event
                    break out;
                } else if ((e1Start <= e2Start && e1End >= e2Start) || (e2Start <= e1Start && e2End >= e1Start)) {
                    // overlap
                    continue;
                } else {
                    retSet.add(e1Val + e2Val);

                    //pruning: get e1's best combine, don't try any other combin with e1
                    break;
                }
            }

            treeMap.put(e1Val, event);
        }

        return retSet.last();
    }
}
