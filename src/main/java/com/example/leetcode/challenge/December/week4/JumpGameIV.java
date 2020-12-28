package com.example.leetcode.challenge.December.week4;


import java.util.*;

/**
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * Example 2:
 *
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * Example 3:
 *
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 * Example 4:
 *
 * Input: arr = [6,1,9]
 * Output: 2
 * Example 5:
 *
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 */
public class JumpGameIV {
    public static void main(String[] args) {

    }

    /**
     * https://blog.csdn.net/u013325815/article/details/106899289
     * @param arr
     * @return
     */
    public int minJumps(int[] arr) {
        HashMap<Integer, List<Integer>> hashmap = new HashMap<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            hashmap.putIfAbsent(arr[i], new ArrayList<Integer>());
            hashmap.get(arr[i]).add(i);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;

        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Integer index = queue.poll();
                if(index == n - 1) {
                    return step;
                }
                // current index;
                for(Integer curindex : hashmap.get(arr[index])) {
                    if(curindex != index && !visited[curindex]) {
                        queue.offer(curindex);
                        visited[curindex] = true;
                    }
                }
                // left;
                if(index - 1 >= 0 && !visited[index - 1])  {
                    queue.offer(index - 1);
                    visited[index - 1] = true;
                }
                // right;
                if(index + 1 < n && !visited[index + 1]) {
                    queue.offer(index + 1);
                    visited[index + 1] = true;
                }
                hashmap.get(arr[index]).clear();
            }
            step++;
        }
        return -1;
    }
}
