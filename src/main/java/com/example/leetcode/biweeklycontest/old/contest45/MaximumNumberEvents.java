package com.example.leetcode.biweeklycontest.old.contest45;


import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.
 *
 * You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.
 *
 * Return the maximum sum of values that you can receive by attending events.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
 * Output: 7
 * Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
 * Example 2:
 *
 *
 *
 * Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
 * Output: 10
 * Explanation: Choose event 2 for a total value of 10.
 * Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
 * Example 3:
 *
 *
 *
 * Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
 * Output: 9
 * Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.
 *
 */
public class MaximumNumberEvents {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended-ii/solution/zhan-wei-by-simon123-t-spkn/
     * @param events
     * @param k
     * @return
     */
    public int maxValue(int[][] events, int k) {
        int ans = 0;
        //预处理，以会议截止日期排序
        Arrays.sort(events,new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        //保存每轮依次计算以第i个会议为小明要参加的第k次会议取得的最大价值
        TreeMap<Integer,Integer> map = new TreeMap<>();
        //加这句便于查询，不然需要特定判断
        map.put(0,0);
        for(int L=1;L<=k;L++){
            TreeMap<Integer,Integer> tmp = new TreeMap<>();
            //同上，便于判断
            tmp.put(0,0);
            for(int[] event:events){
                int start = event[0];
                int end = event[1];
                int val = event[2];
                Integer key = map.floorKey(start-1);
                //start之前不一定有符合条件的L-1个会议
                if(key == null){
                    continue;
                }
                //小明要参加第i个会议，那么要计算start之前的(L-1)次会议的最大价值+第i次会议价值，保存在tmp里面
                int tmpval = map.get(key) + val;
                //本轮中小明要参加的第L次会议，end为右侧边界能获取的最大价值，更新
                tmp.put(end, Math.max(tmpval, tmp.get(tmp.floorKey(end))));
            }
            //本轮处理完成后，比较中间值更新结果
            ans = Math.max(ans,tmp.get(tmp.floorKey((int)1e9)));
            map = tmp;
        }
        return ans;
    }
}
