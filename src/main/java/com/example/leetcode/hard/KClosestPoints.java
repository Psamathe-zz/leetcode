package com.example.leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class KClosestPoints {
    public static void main(String[] args) {
        int[][] points = new int[][] {{3,3},{5,-1},{-2,4}};
        int K = 2;
        KClosestPoints kClosestPoints = new KClosestPoints();
        int[][] result = kClosestPoints.kClosest(points,K);
        System.out.println(result);
    }

    public int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][2];
        Map<Point,Integer> map = new HashMap<>();
        for (int[] point:points){
            Point point1 = new Point(point[0],point[1]);
            map.put(point1,point1.distance());
        }
        List<Point> list = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(e->e.getKey()).collect(Collectors.toList());
        int index = 0;
        for(int i = 0 ; i< list.size() && index<K;i++){
            result[index][0] = list.get(i).x;
            result[index][1] = list.get(i).y;
            index++;
        }
        return result;
    }

    public class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance(){
            return (int)(Math.pow(x,2) + Math.pow(y,2));
        }
    }


    /**
     * faster solution
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestV2(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }


    /**
     * less memory
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestV3(int[][] points, int K) {

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0]*p2[0] + p2[1]*p2[1] - p1[0]*p1[0] - p1[1]*p1[1]);
        int[][] nearestPoints = new int[K][2];

        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > K)  pq.poll();
        }

        while (K > 0) nearestPoints[--K] = pq.poll();
        return nearestPoints;
    }
}
