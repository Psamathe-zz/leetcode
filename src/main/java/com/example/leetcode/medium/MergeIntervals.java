package com.example.leetcode.medium;


import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };

        MergeIntervals mergeIntervals = new MergeIntervals();
        mergeIntervals.merge(intervals);
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0])
                    return o1[0] - o2[0];
                else
                    return o1[1] - o2[1];
            }
        });
        List<int[]> result = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][0];

        for (int i = 1; i < intervals.length; i++) {
            if(end < intervals[i][0]){
                result.add(new int[]{start,end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end,intervals[i][1]);
            }
        }
        result.add(new int[]{start,end});
        int[][] res = new int[result.size()][];
        int j = 0;
        while (j < result.size())
            res[j] = result.get(j++);
        return res;
    }


    /**
     * faster solution
     * @param intervals
     * @return
     */
    public int[][] mergeV1(int[][] intervals) {
        int count = intervals.length;
        for(int i = 0; i < intervals.length - 1; ++i) {
            int[] i1 = intervals[i];
            for(int j = i + 1; j < intervals.length; ++j) {
                int[] i2 = intervals[j];
                if(i1[0] <= i2[1] && i1[1] >= i2[0]) {
                    i2[0] = Math.min(i1[0], i2[0]);
                    i2[1] = Math.max(i1[1], i2[1]);
                    i1[0] = 1;
                    i1[1] = 0;
                    --count;
                    break;
                }
            }
        }

        int[][] ans = new int[count][];
        for(int i = 0, j = 0; i < intervals.length; ++i) {
            if(intervals[i][0] <= intervals[i][1]) {
                ans[j++] = intervals[i];
            }
        }
        return ans;
    }


    /**
     * less memory
     * @param ints
     * @return
     */
    public int[][] mergeV2(int[][] ints) {
        return usingQueue(ints);
    }

    private int[][] usingQueue(int[][] ints) {

        if (ints == null || ints.length < 2)
            return ints;

        // order intervals by their start time
        Queue<int[]> q = new PriorityQueue<>((i1, i2) -> {
            return new Integer(i1[0]).compareTo(new Integer(i2[0]));
        });
        for (int i = 0; i < ints.length; i++)
            q.offer(ints[i]);

        // coll tracks the intervals after they are merged
        List<int[]> coll = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            if (q.isEmpty()) {
                coll.add(curr);
                break;
            }

            int[] next = q.peek();
            if (overlap(curr, next)) {
                int[] m = merge(curr, next);
                q.poll();
                q.offer(m);
            } else {
                coll.add(curr);
            }
        }

        // convert list of integer arrays to an array of arrays
        int[][] res = new int[coll.size()][2];
        for (int i = 0; i < coll.size(); i++) {
            res[i] = coll.get(i);
        }
        return res;
    }

    private int[] merge(int[] i1, int[] i2) {
        return new int[] {Math.min(i1[0], i2[0]), Math.max(i1[1], i2[1])};
    }

    private boolean overlap(int[] i1, int[] i2) {
        return i1[1] >= i2[0];
    }

    /*
    public int[][] merge(int[][] intervals) {
        List<int[]> iList = new ArrayList<>();
        for(int[] interval : intervals) {
            iList.add(interval);
        }
        Collections.sort(iList, (a, b) -> a[0] - b[0]);

        int i = 0;
        while(i < iList.size() - 1) {
            int[] i1 = iList.get(i);
            int[] i2 = iList.get(i + 1);
            if(i1[1] >= i2[0]) {
                i1[0] = i1[0] <= i2[0] ? i1[0] : i2[0];
                i1[1] = i1[1] >= i2[1] ? i1[1] : i2[1];
                iList.remove(i + 1);
                continue;
            }
            ++i;
        }

        int[][] res = new int[iList.size()][2];
        for(int j = 0; j < iList.size(); ++j) {
            res[j][0] = iList.get(j)[0];
            res[j][1] = iList.get(j)[1];
        }
        return res;
    }
    */
}
