package com.example.leetcode.weeklycontest.test243;


import java.util.*;

/**
 * You are given two 0-indexed integer arrays servers and tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i] is the weight of the i​​​​​​th​​​​ server, and tasks[j] is the time needed to process the j​​​​​​th​​​​ task in seconds.
 *
 * You are running a simulation system that will shut down after all tasks are processed. Each server can only process one task at a time. You will be able to process the jth task starting from the jth second beginning with the 0th task at second 0. To process task j, you assign it to the server with the smallest weight that is free, and in case of a tie, choose the server with the smallest index. If a free server gets assigned task j at second t,​​​​​​ it will be free again at the second t + tasks[j].
 *
 * If there are no free servers, you must wait until one is free and execute the free tasks as soon as possible. If multiple tasks need to be assigned, assign them in order of increasing index.
 *
 * You may assign multiple tasks at the same second if there are multiple free servers.
 *
 * Build an array ans​​​​ of length m, where ans[j] is the index of the server the j​​​​​​th task will be assigned to.
 *
 * Return the array ans​​​​.
 *
 *
 *
 * Example 1:
 *
 * Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]
 * Output: [2,2,0,2,1,2]
 * Explanation: Events in chronological order go as follows:
 * - At second 0, task 0 is added and processed using server 2 until second 1.
 * - At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
 * - At second 2, task 2 is added and processed using server 0 until second 5.
 * - At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
 * - At second 4, task 4 is added and processed using server 1 until second 5.
 * - At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.
 * Example 2:
 *
 * Input: servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
 * Output: [1,4,1,4,1,3,2]
 * Explanation: Events in chronological order go as follows:
 * - At second 0, task 0 is added and processed using server 1 until second 2.
 * - At second 1, task 1 is added and processed using server 4 until second 2.
 * - At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4.
 * - At second 3, task 3 is added and processed using server 4 until second 7.
 * - At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9.
 * - At second 5, task 5 is added and processed using server 3 until second 7.
 * - At second 6, task 6 is added and processed using server 2 until second 7.
 *
 */
public class ProcessTasksUsingServers {
    public static void main(String[] args) {
        ProcessTasksUsingServers processTasksUsingServers = new ProcessTasksUsingServers();
        processTasksUsingServers.assignTasks(new int[]{3,3,2}, new int[]{1,2,3,2,1,2});
    }

    /**
     * https://leetcode-cn.com/problems/process-tasks-using-servers/solution/you-xian-dui-lie-by-6tiy7ctlup-mqgg/
     * @param servers
     * @param tasks
     * @return
     */
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> unused= new PriorityQueue<int[]>((a,b)->{
            if(a[0]==b[0])
                return a[1]-b[1];
            else
                return a[0]-b[0];
        });
        PriorityQueue<int[]> using = new PriorityQueue<int[]>((a,b)->a[2]-b[2]);
        int res[]= new int[tasks.length];
        for (int i = 0; i <  servers.length; i++)
            unused.add(new int[] {servers[i],i});
        int j=0;
        for (int i = 0; j < tasks.length; i++) {
            while(!using.isEmpty()&&using.peek()[2]<=i) {
                int [] k=using.poll();
                unused.add(new int[] {k[0],k[1]});
            }
            while(!unused.isEmpty()&&j<tasks.length&&j<=i) {
                int[] ser = unused.poll();
                using.add(new int[] {ser[0],ser[1],i+tasks[j]});
                res[j]=ser[1];
                j++;
            }
            if(unused.isEmpty()) {
                int a[]=using.poll();
                i=a[2]-1;
                unused.add(new int[] {a[0],a[1]});
            }
        }
        return res;
    }
}
