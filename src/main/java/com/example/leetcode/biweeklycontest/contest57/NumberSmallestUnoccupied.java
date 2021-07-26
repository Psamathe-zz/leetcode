package com.example.leetcode.biweeklycontest.contest57;


import java.util.*;

/**
 * There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.
 *
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
 * When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.
 *
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.
 *
 * Return the chair number that the friend numbered targetFriend will sit on.
 *
 *
 *
 * Example 1:
 *
 * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * Output: 1
 * Explanation:
 * - Friend 0 arrives at time 1 and sits on chair 0.
 * - Friend 1 arrives at time 2 and sits on chair 1.
 * - Friend 1 leaves at time 3 and chair 1 becomes empty.
 * - Friend 0 leaves at time 4 and chair 0 becomes empty.
 * - Friend 2 arrives at time 4 and sits on chair 0.
 * Since friend 1 sat on chair 1, we return 1.
 * Example 2:
 *
 * Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * Output: 2
 * Explanation:
 * - Friend 1 arrives at time 1 and sits on chair 0.
 * - Friend 2 arrives at time 2 and sits on chair 1.
 * - Friend 0 arrives at time 3 and sits on chair 2.
 * - Friend 1 leaves at time 5 and chair 0 becomes empty.
 * - Friend 2 leaves at time 6 and chair 1 becomes empty.
 * - Friend 0 leaves at time 10 and chair 2 becomes empty.
 * Since friend 0 sat on chair 2, we return 2.
 *
 * [[33889,98676],[80071,89737],[44118,52565],[52992,84310],[78492,88209],[21695,67063],[84622,95452],[98048,98856],[98411,99433],[55333,56548],[65375,88566],[55011,62821],[48548,48656],[87396,94825],[55273,81868],[75629,91467]]
 * 6
 */
public class NumberSmallestUnoccupied {
    public static void main(String[] args) {
        NumberSmallestUnoccupied numberSmallestUnoccupied = new NumberSmallestUnoccupied();
        numberSmallestUnoccupied.smallestChair(new int[][]{
                {33889,98676},
                {80071,89737},
                {44118,52565},
                {52992,84310},
                {78492,88209},
                {21695,67063},
                {84622,95452},
                {98048,98856},
                {98411,99433},
                {55333,56548},
                {65375,88566},
                {55011,62821},
                {48548,48656},
                {87396,94825},
                {55273,81868},
                {75629,91467},
        }, 6);
    }


    public int smallestChair(int[][] times, int targetFriend) {
        int targetStart = times[targetFriend][0];
        Arrays.sort(times, (a, b) -> a[0] - b[0]);
        //优先队列用来存放数组。[0]为离开时间。[1]为座位号码
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((int[]) o1)[0] - ((int[]) o2)[0];
            }
        });
        int curNum = 0;
        //set用来维护已经占用的座位。
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < times.length; i++) {
            //判断是否应该更新当前最小值
            while (!queue.isEmpty() && queue.peek()[0] <= times[i][0]) {
                int i1 = queue.poll()[1];
                set.remove(i1);
                curNum = Math.min(curNum, i1);
            }
            //选取当前最小座位
            while (set.contains(curNum)) {
                curNum++;
            }
            //满足条件直接返回
            if (times[i][0] == targetStart) {
                return curNum;
            }
            //队列为空直接。当前最小值设置为0
            if (queue.isEmpty()) {
                curNum = 0;
            }
            set.add(curNum);
            queue.add(new int[]{times[i][1], curNum});
            curNum++;
        }
        return 0;
    }
}
