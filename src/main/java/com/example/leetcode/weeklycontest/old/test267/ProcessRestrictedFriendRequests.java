package com.example.leetcode.weeklycontest.old.test267;

/**
 * You are given an integer n indicating the number of people in a network. Each person is labeled from 0 to n - 1.
 *
 * You are also given a 0-indexed 2D integer array restrictions, where restrictions[i] = [xi, yi] means that person xi and person yi cannot become friends, either directly or indirectly through other people.
 *
 * Initially, no one is friends with each other. You are given a list of friend requests as a 0-indexed 2D integer array requests, where requests[j] = [uj, vj] is a friend request between person uj and person vj.
 *
 * A friend request is successful if uj and vj can be friends. Each friend request is processed in the given order (i.e., requests[j] occurs before requests[j + 1]), and upon a successful request, uj and vj become direct friends for all future friend requests.
 *
 * Return a boolean array result, where each result[j] is true if the jth friend request is successful or false if it is not.
 *
 * Note: If uj and vj are already direct friends, the request is still successful.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
 * Output: [true,false]
 * Explanation:
 * Request 0: Person 0 and person 2 can be friends, so they become direct friends.
 * Request 1: Person 2 and person 1 cannot be friends since person 0 and person 1 would be indirect friends (1--2--0).
 * Example 2:
 *
 * Input: n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
 * Output: [true,false]
 * Explanation:
 * Request 0: Person 1 and person 2 can be friends, so they become direct friends.
 * Request 1: Person 0 and person 2 cannot be friends since person 0 and person 1 would be indirect friends (0--2--1).
 * Example 3:
 *
 * Input: n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
 * Output: [true,false,true,false]
 * Explanation:
 * Request 0: Person 0 and person 4 can be friends, so they become direct friends.
 * Request 1: Person 1 and person 2 cannot be friends since they are directly restricted.
 * Request 2: Person 3 and person 1 can be friends, so they become direct friends.
 * Request 3: Person 3 and person 4 cannot be friends since person 0 and person 1 would be indirect friends (0--4--3--1).
 */
public class ProcessRestrictedFriendRequests {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/process-restricted-friend-requests/solution/java-bing-cha-ji-shuang-bai-by-jiyi-3zzw/
     */
    private int[] pre;
    private boolean[][] res;
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        pre = new int[n];
        res = new boolean[n][n];
        for(int i=0; i<n; i++){
            pre[i] = i;
        }
        int r=restrictions.length;
        for(int i=0; i<r; i++){
            int x=restrictions[i][0],y=restrictions[i][1];
            res[x][y] = true;
            res[y][x] = true;
        }
        int m=requests.length;
        boolean[] ans = new boolean[m];
        for(int i=0; i<m; i++){
            int x=find(requests[i][0]), y=find(requests[i][1]);
            if(x == y){
                ans[i] = true;
            }else{
                if(res[x][y] || res[y][x]){
                    ans[i] = false;
                }else{
                    pre[x] = y;
                    for(int j=0; j<n; j++){
                        if(res[x][j]){
                            res[y][j] = true;
                            res[j][y] = true;
                        }
                    }
                    ans[i] = true;
                }
            }
        }
        return ans;
    }

    public int find(int x){
        int r = x;
        while(pre[r] != r){
            r = pre[r];
        }
        int i=x, j;
        while(i != r){
            j = pre[i];
            pre[i] = r;
            i = j;
        }
        return r;
    }

}
