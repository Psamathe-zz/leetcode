package com.example.leetcode.weeklycontest.old.test239;


import java.util.*;

/**
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
 *
 * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.
 *
 * Return an array containing the answers to the queries.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 * Example 2:
 *
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 1 <= lefti <= righti <= 107
 * 1 <= queries[j] <= 107
 */
public class MinimumInterval {
    public static void main(String[] args) {

    }

    /**
     * faster solution
     * @param intervals
     * @param queries
     * @return
     */
    public int[] minIntervalVFaster(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (int[] i1, int[] i2) -> Integer.compare(i1[1]-i1[0]+1, i2[1]-i2[0]+1));
        TreeSet<Integer> set = new TreeSet<>();
        for(int x:queries)set.add(x);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int[] x:intervals){
            Integer nxt;
            int length = x[1]-x[0]+1;
            while((nxt = set.ceiling(x[0])) != null && nxt <= x[1]){
                map.put(nxt, length);
                set.remove(nxt);
            }
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i< queries.length; i++)ans[i] = map.getOrDefault(queries[i], -1);
        return ans;
    }


    /**
     * https://leetcode-cn.com/problems/minimum-interval-to-include-each-query/solution/you-xian-dui-lie-by-huang-fu-peng-ptia/
     * @param intervals
     * @param queries
     * @return
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        //对区间按起始位置升序排序
        Arrays.sort(intervals, (v1,v2) -> v1[0] - v2[0]);
        //将queries的值queries[i]和序号i封装到一个二维整数数组
        int n = queries.length;
        int[][] p = new int[n][2];
        for(int i = 0; i < n; i++){
            p[i][0] = queries[i];
            p[i][1] = i;
        }
        //将p按查询位置p[i][0]升序排序
        Arrays.sort(p, (v1,v2) -> v1[0] - v2[0]);
        int[] ans = new int[n];
        //将ans事先用-1填充
        Arrays.fill(ans, -1);
        //创建优先队列
        PriorityQueue<int[]> queue = new PriorityQueue<>((v1, v2) -> v1[1] - v1[0] - v2[1] +v2[0]);
        //未遍历到的intervals起始位置
        int index = 0;
        for(int i = 0; i < n; i++){
            //将所有起始位置小于等于查询位置的区间intervals[i]添加到优先队列中
            while(index < intervals.length && p[i][0] >= intervals[index][0]){
                queue.offer(new int[]{intervals[index][0], intervals[index][1]});
                index++;
            }
            //将队列中区间长度最短但区间结束位置小于查询位置的区间弹出队列
            while(!queue.isEmpty() && queue.peek()[1] < p[i][0]){
                queue.poll();
            }
            //如果队列不为空，则将最短区间的长度添加到对应的查询结果中ans[p[i][1]]
            if(!queue.isEmpty()){
                int[] temp = queue.peek();
                ans[p[i][1]] = temp[1] - temp[0] + 1;
            }
        }
        return ans;
    }


    public int[] minIntervalVold(int[][] intervals, int[] queries) {
        int queriesLength = queries.length;
        Arrays.sort(intervals, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });
        int[] res = new int[queriesLength];
        for (int i = 0; i < queriesLength; i++) {
            res[i] = findQuery(intervals,queries[i]);
        }
        return res;
    }

    public int findQuery(int[][] intervals, int target){
        int length = intervals.length;
        int min = Integer.MAX_VALUE;
        int preStart=0;
        for (int i = 0; i < length; i++) {
            if(intervals[i][0] > target)
                break;
            if(intervals[i][1] < target || preStart == intervals[i][1])
                continue;

             if(intervals[i][1] - intervals[i][0] + 1 < min) {
                 min = intervals[i][1] - intervals[i][0] + 1;
                 preStart = intervals[i][0];
             }
        }
        if(min == Integer.MAX_VALUE)
            return -1;
        else
            return min;
    }
}
