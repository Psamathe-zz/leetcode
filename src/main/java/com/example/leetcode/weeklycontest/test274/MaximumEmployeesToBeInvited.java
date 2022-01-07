package com.example.leetcode.weeklycontest.test274;

import java.util.LinkedList;
import java.util.Queue;

/*
A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.

The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.

Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.



Example 1:


Input: favorite = [2,2,1,2]
Output: 3
Explanation:
The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
The maximum number of employees that can be invited to the meeting is 3.
Example 2:

Input: favorite = [1,2,0]
Output: 3
Explanation:
Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
The seating arrangement will be the same as that in the figure given in example 1:
- Employee 0 will sit between employees 2 and 1.
- Employee 1 will sit between employees 0 and 2.
- Employee 2 will sit between employees 1 and 0.
The maximum number of employees that can be invited to the meeting is 3.
Example 3:


Input: favorite = [3,0,1,4,1]
Output: 4
Explanation:
The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.

 */
public class MaximumEmployeesToBeInvited {
    public static void main(String[] args) {

    }

    int[] v;
    int[] cnt;
    int[] len;
    boolean[] vis;
    int n;
    int ans;

    /*
    https://leetcode-cn.com/problems/maximum-employees-to-be-invited-to-a-meeting/solution/nickzhou-sai-xi-lie-jian-nan-6fen-ti-by-u49ch/
     */
    public int maximumInvitations(int[] favorite) {
        //数组长度
        n = favorite.length;
        v = favorite;
        //每个点的入度数组
        cnt = new int[n];
        vis = new boolean[n];
        len = new int[n];
        for (int i = 0; i < n; i++) {
            //计算每个元素的入度
            cnt[favorite[i]]++;
        }
        //对每个枝上的点的长度进行计算
        //剪枝操作
        topSort();
        //答案默认为0
        ans = 0;
        //对所有点计算,如果a,b相互喜欢，则在结果上加上+1+len[i]，即点本身以及对应枝的长度
        for (int i = 0; i < n; i++) {
            if (favorite[favorite[i]] == i) {
                ans += 1 + len[i];
            }
        }
        //对于所有点如果未被访问过，那么尝试深搜
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfs(i, 0);
        }
        return ans;
    }

    //对所有可能存在于环上的点进行深搜，然后将环的长度与ans进行比较，ans取较大值，即为输出结果
    public void dfs(int index, int count) {
        if (!vis[index]) {
            vis[index] = true;
            dfs(v[index], count + 1);
        } else {
            ans = Math.max(ans, count);
        }
    }

    //本质是剪枝，使用len[]数组储存每个点位如果存在在枝上，那么从枝的起点到当前点的长度
    public void topSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0)
                //入度为0则将该点加入进队列中
                q.offer(i);
        }
        //表明当前层数
        int level = 0;
        while (!q.isEmpty()) {
            level++;
            //当前队列长度
            int qLen = q.size();
            //遍历当前队列中的每个元素（入度为0)
            for (int i = 0; i < qLen; i++) {
                //取出元素
                int now = q.poll();
                //定义数组，当前点已取用
                vis[now] = true;
                //当前元素的喜爱点的入度减1
                cnt[v[now]]--;
                //如果当前点的喜爱点的入度为0，就将该点加入进队列中
                if (cnt[v[now]] == 0)
                    q.offer(v[now]);
                //定义当前点的喜爱的点到入度为0的点的距离为level
                len[v[now]] = level;
            }
        }
    }
}
