package com.example.leetcode.challenge.may;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 *
 * 3
 * [[0,1],[0,2],[1,2]]
 * Output:
 * false
 * Expected:
 * true
 *
 * 4
 * [[0,1],[3,1],[1,3],[3,2]]
 * 100
 * {6,27},{83,9},{10,95},{48,67},{5,71},{18,72},{7,10},{92,4},{68,84},{6,41},{82,41},{18,54},{0,2},{1,2},{8,65},{47,85},{39,51},{13,78},{77,50},{70,56},{5,61},{26,56},{18,19},{35,49},{79,53},{40,22},{8,19},{60,56},{48,50},{20,70},{35,12},{99,85},{12,75},{2,36},{36,22},{21,15},{98,1},{34,94},{25,41},{65,17},{1,56},{43,96},{74,57},{19,62},{62,78},{50,86},{46,22},{10,13},{47,18},{20,66},{83,66},{51,47},{23,66},{87,42},{25,81},{60,81},{25,93},{35,89},{65,92},{87,39},{12,43},{75,73},{28,96},{47,55},{18,11},{29,58},{78,61},{62,75},{60,77},{13,46},{97,92},{4,64},{91,47},{58,66},{72,74},{28,17},{29,98},{53,66},{37,5},{38,12},{44,98},{24,31},{68,23},{86,52},{79,49},{32,25},{90,18},{16,57},{60,74},{81,73},{26,10},{54,26},{57,58},{46,47},{66,54},{52,25},{62,91},{6,72},{81,72},{50,35},{59,87},{21,3},{4,92},{70,12},{48,4},{9,23},{52,55},{43,59},{49,26},{25,90},{52,0},{55,8},{7,23},{97,41},{0,40},{69,47},{73,68},{10,6},{47,9},{64,24},{95,93},{79,66},{77,21},{80,69},{85,5},{24,48},{74,31},{80,76},{81,27},{71,94},{47,82},{3,24},{66,61},{52,13},{18,38},{1,35},{32,78},{7,58},{26,58},{64,47},{60,6},{62,5},{5,22},{60,54},{49,40},{11,56},{19,85},{65,58},{88,44},{86,58}}
 */
public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{0,1},{0,2},{1,2}};
        CourseSchedule courseSchedule = new CourseSchedule();
        boolean result = courseSchedule.canFinishV2(numCourses,prerequisites);
        System.out.println(result);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] dp = new int[numCourses][numCourses];
        int[] visit = new int[numCourses];
        boolean result = true;

        for(int[] temp : prerequisites){
            int a = temp[0];
            int b = temp[1];
            dp[a][b] = 1;
        }
        for (int i = 0; i < numCourses && result; i++) {
            if(!findPrerequisite(dp, visit, i, numCourses))
                return false;
        }

        return true;
    }

    public boolean findPrerequisite(int[][] dp, int[] visit,int start,int numCourses){
        if (visit[start] == -1)
            return false;
        if (visit[start] == 1)
            return true;
        visit[start] = -1;
        for (int i = 0; i < numCourses; i++) {
            if(dp[start][i] == 1)
                if(!findPrerequisite(dp, visit, i, numCourses))
                    return false;
        }
        visit[start] = 1;
        return true;
    }


    /**
     * faster dfs
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishV1(int numCourses, int[][] prerequisites) {
        if (numCourses ==0 || prerequisites.length ==0)
            return true;
        ArrayList[] graph = new ArrayList[numCourses];
        //populating the graph
        for(int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<Integer>();
        }
        //populating the prerequisites
        for(int i=0;i<prerequisites.length;i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        int[] visited = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if (!dfs(i,graph,visited)){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int course,ArrayList[] children,int[] visited){
        // System.out.println("course"+course+"  visited[course]"+visited[course]);
        if (visited[course] == 1){
            return false;
        }
        visited[course] = 1;
        for(int i=0;i<children[course].size();i++){
            int val = (int)children[course].get(i);
            // System.out.println("val"+val+"  visited[val]"+visited[val]);
            if (visited[val] == 1)
                return false;
            if (visited[val] == 0 && !dfs(val,children,visited)){
                return false;
            }
        }
        visited[course] = 2;
        return true;
    }


    /**
     * less memory
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishV2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pre : prerequisites){
            int i = pre[0];
            int j = pre[1];
            if (map.containsKey(i)) {
                map.get(i).add(j);
            } else {
                map.put(i, new ArrayList<>());
                map.get(i).add(j);
            }
        }
        int[] visited = new int[numCourses];
        int tmp = 1;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                //System.out.println(1);
                if (BFS(i, map, visited, tmp)) {
                    //System.out.println(1);
                    return false;
                }
                tmp += 2;
                //System.out.println(tmp);
            }
        }
        return true;
    }

    private boolean DFS(int i, Map<Integer, List<Integer>> map, int[] visited, int tmp) {
        visited[i] = tmp;
        if (!map.containsKey(i)) {
            visited[i] = tmp + 1;
            return false;
        }
        for (int neighbor : map.get(i)) {
            System.out.println(visited[neighbor]);
            System.out.println(tmp);
            if (visited[neighbor] == tmp) {
                //System.out.println(2);
                return true;
            } else if (visited[neighbor] != 0) {
                continue;
            } else {
                if (DFS(neighbor, map, visited, tmp)) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        visited[i] = tmp + 1;
        return false;
    }

    private boolean BFS(int i, Map<Integer, List<Integer>> map, int[] visited, int tmp) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerFirst(i);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int cur = queue.pollLast();
                visited[cur] = tmp;
                if (map.containsKey(cur)) {
                    for (int neighbor : map.get(cur)) {
                        //System.out.println(neighbor);
                        //System.out.println(visited[neighbor]);
                        if (visited[neighbor] == tmp + 1) {
                            //System.out.println(neighbor);
                            return true;
                        } else if (visited[neighbor] != 0) {
                            continue;
                        }
                        queue.offerFirst(neighbor);
                    }
                } else {
                    visited[cur] = tmp + 1;
                }
                visited[cur] = tmp + 1;
            }
        }
        return false;
    }

}
