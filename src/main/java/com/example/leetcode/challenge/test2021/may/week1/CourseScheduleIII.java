package com.example.leetcode.challenge.test2021.may.week1;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 *
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * Return the maximum number of courses that you can take.
 *
 *
 *
 * Example 1:
 *
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * Example 2:
 *
 * Input: courses = [[1,2]]
 * Output: 1
 * Example 3:
 *
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 */
public class CourseScheduleIII {
    public static void main(String[] args) {
        CourseScheduleIII courseScheduleIII = new CourseScheduleIII();
        courseScheduleIII.scheduleCourse(new int[][]{
                {100,200},
                {200,1300},
                {1000,1250},
                {2000,3200}
        });
    }

    /**
     * http://leetcode.jp/leetcode-630-course-schedule-iii-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        int curTime = 0;
        PriorityQueue<Integer> q=new PriorityQueue<>((a,b)->b-a);
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);
        for (int[] course : courses) {
            curTime += course[0];
            q.add(course[0]);
            if (curTime > course[1]) {
                curTime -= q.poll();
            }
        }
        return q.size();
    }

}
