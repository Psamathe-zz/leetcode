package com.example.leetcode.challenge.July.week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{
                {1,0},
                {0,1}
        };
        CourseScheduleII courseScheduleII = new CourseScheduleII();
        courseScheduleII.findOrder(numCourses,prerequisites);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4504793.html
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[][] map = new int[numCourses][numCourses];
        int[] in = new int[numCourses];

        for (int[] prerequisite : prerequisites){
            map[prerequisite[1]][prerequisite[0]] = 1;
            in[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> res = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if(in[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()){
            int t = queue.poll();
            res.add(t);

            for (int i = 0; i < numCourses; i++) {
                if(map[t][i] == 1){
                    in[i]--;
                    if(in[i] == 0){
                        queue.add(i);
                    }
                }
            }
        }
        if(res.size() != numCourses)
            return new int[0];
        else
            return res.stream().mapToInt(e->e).toArray();
    }


    /**
     * faster solution
     */
    List<Integer>[] adj;
    boolean[] visited;
    boolean[] explored;
    int[] res;
    int count = 0;
    public int[] findOrderV1(int N, int[][] P) {
        adj = new ArrayList[N];
        visited = new boolean[N];
        explored = new boolean[N];
        res = new int[N];

        for(int i = 0 ;i<N;i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0;i<P.length;i++){
            adj[P[i][0]].add(P[i][1]);
        }
        for(int i =0;i<N;i++){
            if(!visited[i]){
                if(isCyclic(i)){
                    return new int[0];
                }
            }
        }
        // int[] ans = new int[N];
        // int count = 0;
        // for(int i : res)
        //     ans[count++] = i;
        return res;
    }
    public boolean isCyclic(int N){
        visited[N] = true;
        for(int i :adj[N]){
            if(!visited[i]){
                if(isCyclic(i))
                    return true;
            }
            else if(!explored[i])
                return true;
        }
        explored[N] = true;
        res[count++] = N;
        return false;
    }
}
