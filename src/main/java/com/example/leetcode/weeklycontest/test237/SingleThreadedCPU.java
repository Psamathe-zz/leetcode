package com.example.leetcode.weeklycontest.test237;


import java.util.*;

/**
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 * Example 2:
 *
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 *
 */
public class SingleThreadedCPU {
    public static void main(String[] args) {
        int[][] tasks = new int[][]{
                {1,2},
                {2,4},
                {3,2},
                {4,1}
        };
        SingleThreadedCPU singleThreadedCPU = new SingleThreadedCPU();
        singleThreadedCPU.getOrder(tasks);
    }

    public int[] getOrder(int[][] tasks) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer,List<Task>> map = new TreeMap<>();
        int index = 0;
        for (int[] task : tasks){
            int enqueueTime = task[0];
            Task newTask = new Task(index, task[0], task[1]);
            map.compute(enqueueTime,(k,v) ->{
                if(v == null){
                    v = new ArrayList<>();
                }
                v.add(newTask);
                return v;
            });
            index++;
        }
        int startTime;
        int finishTime = 0;
        Task task;
        PriorityQueue<Task> queue = new PriorityQueue<>((o1, o2) -> {
            if(o1.processingTime != o2.processingTime)
                return o1.processingTime-o2.processingTime;
            else
                return o1.taskIndex-o2.taskIndex;
        });
        do{
            if(!queue.isEmpty()){
                task = queue.poll();
                finishTime += task.processingTime;
            } else {
                Map.Entry<Integer,List<Task>> entry = map.firstEntry();
                startTime = entry.getKey();
                List<Task> list = entry.getValue();
                Collections.sort(list);

                map.remove(startTime);
                task = list.get(0);
                finishTime = task.processingTime + startTime;
                for (int i = 1; i < list.size(); i++) {
                    queue.offer(list.get(i));
                }
            }
            res.add(task.taskIndex);
            for (Integer key: map.keySet()){
                if(key > finishTime){
                    break;
                }
                queue.addAll(map.get(key));
            }

            Iterator<Integer> iter = map.keySet().iterator();

            while (iter.hasNext()) {
                if (iter.next()<= finishTime)
                    iter.remove();
                else
                    break;
            }

        }while (!map.isEmpty() || !queue.isEmpty());

        return res.stream().mapToInt(e->e).toArray();
    }

    public class Task implements Comparable<Task>{
        int taskIndex;
        int enqueueTime;
        int processingTime;

        public Task(int taskIndex, int enqueueTime, int processingTime) {
            this.taskIndex = taskIndex;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }

        @Override
        public int compareTo(Task o) {
            return this.processingTime - o.processingTime;
        }
    }

    /**
     * faster solution
     * @param tasks
     * @return
     */
    public int[] getOrderV0(int[][] tasks) {
        int n = tasks.length;
        int[][] t = new int[n][];
        for(int i = 0; i< n; i++)
            t[i] = new int[]{i, tasks[i][0], tasks[i][1]};
        Arrays.sort(t, (int[] i1, int[] i2) -> Integer.compare(i1[1], i2[1]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] i1, int[] i2) -> {
            if(i1[1] != i2[1])return Integer.compare(i1[1], i2[1]);
            return Integer.compare(i1[0], i2[0]);
        });
        int[] order = new int[n];
        int ind = 0;
        int time = 0;
        for(int i = 0; i< n; i++){

            while(!pq.isEmpty() && time < t[i][1]){
                int[] task = pq.poll();
                order[ind++] = task[0];
                time += task[1];
            }
            time = Math.max(time, t[i][1]);
            pq.add(new int[]{t[i][0], t[i][2]});
        }
        while(!pq.isEmpty()){
            int[] task = pq.poll();
            order[ind++] = task[0];
            time += task[1];
        }
        return order;
    }
}
