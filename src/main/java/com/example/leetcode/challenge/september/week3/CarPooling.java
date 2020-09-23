package com.example.leetcode.challenge.september.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
 *
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.
 *
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 *
 *
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * Example 3:
 *
 * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
 * Output: true
 * Example 4:
 *
 * Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
 * Output: true
 *
 *
 *
 * Constraints:
 *
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 */
public class CarPooling {
    public static void main(String[] args) {

    }

    public boolean carPooling(int[][] trips, int capacity) {
        int length = trips.length;
        Map<Integer,Integer> count = new HashMap<>();
        int start_location;
        int end_location;
        int num_passengers;
        int temp;
        for (int i = 0; i < length; i++) {
            num_passengers = trips[i][0];
            start_location = trips[i][1];
            end_location = trips[i][2];
            for (int j = start_location; j < end_location; j++) {
                temp = count.getOrDefault(j,0)+num_passengers;
                if(temp > capacity)
                    return false;
                count.put(j,temp);
            }
        }
        return true;
    }

    /**
     * faster solution
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPoolingV1(int[][] trips, int capacity) {
        int stops[] = new int[1001];

        for (int t[] : trips)
        {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }

        for (int i = 0; capacity>=0&&i<1001; ++i)
            capacity-=stops[i];

        return capacity>=0;
    }
}
