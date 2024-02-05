package com.example.leetcode.weeklycontest.old.test282;

import java.util.Arrays;

/**
 * You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
 *
 * Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.
 *
 * You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. Return the minimum time required for all buses to complete at least totalTrips trips.
 *
 *
 *
 * Example 1:
 *
 * Input: time = [1,2,3], totalTrips = 5
 * Output: 3
 * Explanation:
 * - At time t = 1, the number of trips completed by each bus are [1,0,0].
 *   The total number of trips completed is 1 + 0 + 0 = 1.
 * - At time t = 2, the number of trips completed by each bus are [2,1,0].
 *   The total number of trips completed is 2 + 1 + 0 = 3.
 * - At time t = 3, the number of trips completed by each bus are [3,1,1].
 *   The total number of trips completed is 3 + 1 + 1 = 5.
 * So the minimum time needed for all buses to complete at least 5 trips is 3.
 * Example 2:
 *
 * Input: time = [2], totalTrips = 1
 * Output: 2
 * Explanation:
 * There is only one bus, and it will complete its first trip at t = 2.
 * So the minimum time needed to complete 1 trip is 2.
 */
public class MinimumTimeToCompleteTrips {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-time-to-complete-trips/solution/zhou-sai-di-282chang-zhou-sai-pu-tao-che-s4eo/
     * @param time
     * @param totalTrips
     * @return
     */
    public long minimumTime(int[] time, int totalTrips) {
        Arrays.sort(time);
        long left = 0;
        // 记录当前最大完成旅途的时间
        long right = 1L*  time[0] * totalTrips ;
        // 在最小时间和最大时间之间搜索符合条件的时间
        while (left < right ){
            long mid = left + (right - left) /2;
            // 记录当前完成旅途的车
            long trips = 0;
            // 遍历每个车次需要完成的时间
            for(int t : time){
                if(mid < t){
                    break;
                }
                // 记录当前时间能完成的趟数
                trips += mid / t;
            }
            // 如果当前完成的车次已经到达了完成的次数则缩小范围 搜索前面时间范围
            if(trips >= totalTrips){
                right = mid;
            } else {
                // 反之搜索后面时间范围
                left = mid + 1;
            }
        }
        return left;
    }
}
