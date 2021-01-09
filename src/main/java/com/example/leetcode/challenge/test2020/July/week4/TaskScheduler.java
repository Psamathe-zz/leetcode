package com.example.leetcode.challenge.test2020.July.week4;

import java.util.*;

/**
 * You are given a char array representing tasks CPU need to do. It contains capital letters A to Z where each letter represents a different task.
 * Tasks could be done without the original order of the array.
 * Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 *
 * You need to return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 *
 */
public class TaskScheduler {
    public static void main(String[] args) {

          /*
        String tasks = "AAAAAABCDEFG";
        char[] task = tasks.toCharArray();
*/
        char[] task = new char[]{'A','A','B','B','C','C','D','D','E','E','F','F','G','G','H','H','I','I','J','J','K','K','L','L','M','M','N','N','O','O','P','P','Q','Q','R','R','S','S','T','T','U','U','V','V','W','W','X','X','Y','Y','Z','Z'};

        int n = 2;
        TaskScheduler taskScheduler = new TaskScheduler();
        int res = taskScheduler.leastIntervalOld(task,n);
        System.out.println(res);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/7098764.html
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25])
            i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);

    }

    public int leastIntervalOld(char[] tasks, int n) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Map<Character,Integer> count = new HashMap<>();
        int res = 0;
        int cycle = n + 1;
        int length = tasks.length;

        for (int i = 0; i < length; i++) {
            count.put(tasks[i],count.getOrDefault(tasks[i],0)+1);
        }

        for (int val : count.values())
            queue.add(val);
        while (!queue.isEmpty()){
            int cnt = 0;
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < cycle; ++i) {
                if (!queue.isEmpty()) {
                    list.add(queue.poll());
                    ++cnt;
                }
            }
            for (int d : list) {
                if (--d > 0)
                    queue.offer(d);
            }
            res += queue.isEmpty() ? cnt : cycle;
        }
        return res;
    }
}
