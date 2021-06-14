package com.example.leetcode.challenge.test2021.june.week2;


import java.util.Collections;
import java.util.PriorityQueue;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 *
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
 *
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
 *
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 *
 * What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 * Example 2:
 *
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can't reach the target (or even the first gas station).
 * Example 3:
 *
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation:
 * We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 * 1000
 * 83
 * [[47,220],[65,1],[98,113],[126,196],[186,218],[320,205],[686,317],[707,325],[754,104],[781,105]]
 * 1000
 * 299
 * [[13,21],[26,115],[100,47],[225,99],[299,141],[444,198],[608,190],[636,157],[647,255],[841,123]]
 */
public class MinimumNumber {
    public static void main(String[] args) {
        MinimumNumber minimumNumber = new MinimumNumber();
        int res = minimumNumber.minRefuelStops(1000, 299, new int[][]{
                {13,21},
                {26,115},
                {100,47},
                {225,99},
                {299,141},
                {444,198},
                {608,190},
                {636,157},
                {647,255},
                {841,123},
        });
        System.out.println(res);
    }


    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        long[] dp = new long[N + 1];
        dp[0] = startFuel;
        for (int i = 0; i < N; ++i)
            for (int t = i; t >= 0; --t)
                if (dp[t] >= stations[i][0])
                    dp[t+1] = Math.max(dp[t+1], dp[t] + (long) stations[i][1]);

        for (int i = 0; i <= N; ++i)
            if (dp[i] >= target) return i;
        return -1;
    }


    public int minRefuelStopsV1(int target, int startFuel, int[][] stations) {
        // pq is a maxheap of gas station capacities
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        int ans = 0, prev = 0;
        for (int[] station: stations) {
            int location = station[0];
            int capacity = station[1];
            startFuel -= location - prev;
            while (!pq.isEmpty() && startFuel < 0) {  // must refuel in past
                startFuel += pq.poll();
                ans++;
            }

            if (startFuel < 0) return -1;
            pq.offer(capacity);
            prev = location;
        }

        // Repeat body for station = (target, inf)
        {
            startFuel -= target - prev;
            while (!pq.isEmpty() && startFuel < 0) {
                startFuel += pq.poll();
                ans++;
            }
            if (startFuel < 0) return -1;
        }

        return ans;
    }
}
