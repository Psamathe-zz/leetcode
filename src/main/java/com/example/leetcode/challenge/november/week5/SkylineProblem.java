package com.example.leetcode.challenge.november.week5;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class SkylineProblem {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/the-skyline-problem/solution/can-kao-da-lao-de-sao-miao-xian-fa-javaban-ben-by-/
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();

        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int[] build : buildings) {
            //插入左节点
            if (!map.containsKey(build[0]))
                map.put(build[0], new ArrayList<>());
            map.get(build[0]).add(-build[2]);
            //插入右节点
            if (!map.containsKey(build[1]))
                map.put(build[1], new ArrayList<>());
            map.get(build[1]).add(build[2]);
        }
        //保留当前位置的所有高度 重定义排序：从大到小
        Map<Integer, Integer> heights = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        //保留上一个位置的横坐标及高度
        int[] last = {0, 0};

        for (int key : map.keySet()) {
//            Integer[] yArrays =(Integer[]) map.get(key).toArray();
            List<Integer> yArrays = map.get(key);
            //排序
            Collections.sort(yArrays);

            for (int y : yArrays) {
                //左端点,高度入队
                if (y < 0) {
                    int val = heights.getOrDefault(-y, 0);
                    heights.put(-y, val + 1);
                } else {
                    //右端点移除高度
                    int val = heights.getOrDefault(y, 0);
                    if (val == 1)
                        heights.remove(y);
                    else
                        heights.put(y, val - 1);
                }
                //获取heights的最大值:就是第一个值
                Integer maxHeight = 0;
                if (!heights.isEmpty())
                    maxHeight = heights.keySet().iterator().next();

                //如果当前最大高度不同于上一个高度，说明其为转折点
                if (last[1] != maxHeight) {
                    //更新last，并加入结果集
                    last[0] = key;
                    last[1] = maxHeight;
                    res.add(Arrays.asList(key, maxHeight));
                }
            }
        }

        return res;
    }



    /** ideally this is a sorting question... feels like greedy or dp
     / Good news: given list is already sort in asending by Li
     / can I do in one pass
     / main issue, getting interception points
     / dry run
     / 1. take the left top corrder when it is possible else take the right bottom one
     /     condition for the left top cor
     /       1. if the current top left has higher height and it is with in the prev right bound
     2. if the element is not with ine the bound take the prev right most element's r value as ground value and
     add current top left is a new starting point and update the right most element
     for each current element find the element on the left side with right most bound and higher height than current
     for each current element find the element on the right side with left most bound and higher height than current
     // base on those two values we can setting up two point into the result
     // time complexit n^2 space n for the result.
     // Can we do better here? we need to store the find the left most element that overlap with current one
     // dp question
     // init idea:
     dp[i] = the index of xxxx
     dp[i+1] =

     by observation, if the height is increasing then we just take the top left
     if the height is decreasing ... complex case need to be handle
     using stack here
     // if it is always increasing then
     // the ending point will be pop out of stack if the next .r < curr.r stop we have reach the end
     // else keep computing the intercept between two rectange
     // the above also happens until the next element has higher heigth and overlap with the top of stack
     // then we excaute to the next element

     // in the case it is decreasing:
     ... 1. we keep pop element from the stack until the a overlap and higher height element is found
     then we compute the coordate and add the current element back in

     */
    public List<List<Integer>> getSkylineV1(int[][] buildings) {
        // this will be use to store the element index
        List<List<Integer>> results = new ArrayList<>();

        //PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.<int[]>comparingInt(e -> e[2]).reversed().thenComparing(e -> e[1]));
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[1] - b[1] : b[2] - a[2]);
        int[] prev = new int[] { -1, Integer.MAX_VALUE, 0 };

        for (int[] curr : buildings) {
            while (!queue.isEmpty() && curr[0] > prev[1]) {
                int[] next = queue.poll();
                if (next[1] <= prev[1])
                    continue;
                results.add(List.of(prev[1], next[2]));
                prev = next;
            }
            if (curr[2] > prev[2]) {
                if (curr[0] == prev[0]) {
                    results.remove(results.size() - 1);
                }
                results.add(List.of(curr[0], curr[2]));
                if (curr[1] < prev[1])
                    queue.offer(prev);
                prev = curr;
            } else if (curr[1] > prev[1]) {
                if (curr[2] == prev[2]) {
                    prev[1] = curr[1];
                } else {
                    queue.offer(curr);
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            if (next[1] <= prev[1])
                continue;
            results.add(List.of(prev[1], next[2]));
            prev = next;
        }
        if (prev[2] == Integer.MAX_VALUE) {
            results.add(List.of(prev[1], 0));
        }
        return results;
    }
}
