package com.example.leetcode.challenge.test2021.june.week1;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 *
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 *
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 */
public class MaximumPerformance {
    public static void main(String[] args) {

    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] items = new int[n][2];
        for(int i=0;i<n;i++){
            items[i][0]=speed[i];
            items[i][1]=efficiency[i];
        }
        // 数组按照效率由大到小排序
        Arrays.sort(items,(a, b)->b[1]-a[1]);
        // 返回结果
        long max=0;
        // 当前速度和
        long sum=0;
        PriorityQueue<Integer> q=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            // 如果queue的元素个数等于k
            if(q.size()==k){
                // 从queue中取出一个速度最慢的
                // 更新当前Queue中的速度和
                sum-=q.poll();
            }
            // 将当前元素的速度加入到Queue
            q.offer(items[i][0]);
            // 更新当前Queue中的速度和
            sum+=items[i][0];
            // 计算当前Queue中元素的速度乘以效率
            long res=sum*items[i][1];
            // 更新全局最大值
            max=Math.max(res, max);
        }
        return (int)(max%1000000007);
    }
}
