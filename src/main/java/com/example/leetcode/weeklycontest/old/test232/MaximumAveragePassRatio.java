package com.example.leetcode.weeklycontest.old.test232;


import java.util.*;

/**
 * There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.
 *
 * You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
 *
 * The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
 *
 * Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * Output: 0.78333
 * Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
 * Example 2:
 *
 * Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * Output: 0.53485
 */
public class MaximumAveragePassRatio {
    public static void main(String[] args) {
        MaximumAveragePassRatio maximumAveragePassRatio = new MaximumAveragePassRatio();
        double res = maximumAveragePassRatio.maxAverageRatio(new int[][]{
                {3,5},
                {1,2},
                {2,2}
        }, 2);
        System.out.println(res);
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {

        //初始化存储班级通过情况数组的大根堆，按照添加一个聪明学生后对班级通过率贡献最大的大根堆顺序排列
        Queue<double[]> pq = new PriorityQueue<double[]>((a, b) -> {
            double x = (double)(a[0] + 1) / (double)(a[1] + 1) - (double)(a[0] / a[1]);
            double y = (double)(b[0] + 1) / (double)(b[1] + 1) - (double)(b[0] / b[1]);
            if(y > x) return 1;
            if(y < x) return -1;
            return 0;
        });
        //存入各班级考试通过情况
        int n = classes.length;
        for(int[] c: classes){
            pq.offer(new double[]{c[0], c[1]});
        }
        //将n个聪明学生逐个按局部最优策略放入相应班级
        while(extraStudents-- > 0){
            double[] c = pq.poll();
            c[0] += 1.0d;
            c[1] += 1.0d;
            pq.offer(c);
        }
        //输出结果
        double ans = 0;
        while(!pq.isEmpty()){
            double[] c = pq.poll();
            ans += c[0] / c[1];
        }
        return ans / (double)n;
    }


    public double maxAverageRatioVOld(int[][] classes, int extraStudents) {
        int length = classes.length;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(6);
        q.add(2);
        q.add(9);
        PriorityQueue<double[]> classList = new PriorityQueue<>(Comparator.comparingDouble(a -> (a[0] - a[1]) / (a[1] * (a[1] + 1))));
        for (int i = 0; i < length; i++) {
            classList.add(new double[]{classes[i][0], classes[i][1]});
        }


        while (extraStudents > 0){

            extraStudents--;
        }
        double[] c = classList.poll();
        return 0;
    }
    public class MyClass {
        int pass;
        int total;

        public MyClass(int pass, int total) {
            this.pass = pass;
            this.total = total;
        }

    }
}
