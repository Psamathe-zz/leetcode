package com.example.leetcode.medium;

import java.util.*;

/**
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 *
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 *
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 *
 * The distance between these two cars is ignored - they are assumed to have the same position.
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 *
 * How many car fleets will arrive at the destination?
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 * The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Note:
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * All initial positions are different.
 */
public class CarFleet {
    public static void main(String[] args) {
        int target = 12;
        int[] position = new int[]{10,8,0,5,3};
        int[] speed = new int[]{2,4,1,1,3};
        CarFleet carFleet = new CarFleet();
        carFleet.carFleet(target,position,speed);
    }

    /**
    * https://www.cnblogs.com/grandyang/p/10540136.html
     */
    // position + speed
    public int carFleet(int target, int[] position, int[] speed) {
        int res = 0;
        int n = position.length;
        double[] time = new double[n];
        List<Car> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            time[i] = (double) (target - position[i])/speed[i];
            list.add(new Car(position[i],time[i]));
        }
        Collections.sort(list, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o2.position - o1.position;
            }
        });
        double cur = 0;
        for (Car car : list) {
            if (car.time <= cur)
                continue;
            cur = car.time;
            ++res;
        }
        return res;
    }

    public class Car{
        int position;
        double time;

        public Car(int position, double time) {
            this.position = position;
            this.time = time;
        }
    }


    /**
     * faster solution
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleetV1(int target, int[] position, int[] speed) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < position.length; i++){
            map.put(position[i], speed[i]);
        }
        double max = 0;
        boolean flag = false;
        int ans = 0;
        Arrays.sort(position);
        for(int i = position.length -1; i >=0; i--){
            double cur = (double)((double)(target - position[i])/(double)map.get(position[i]));
            if(cur > max){
                max = cur;
                ans++;

            }

        }
        return ans;

    }
}
