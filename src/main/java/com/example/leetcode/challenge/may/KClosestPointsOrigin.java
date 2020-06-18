package com.example.leetcode.challenge.may;

import java.util.*;

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
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 */
public class KClosestPointsOrigin {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1,3},
                {-2,2}
        };
        int K = 1;
        KClosestPointsOrigin kClosestPointsOrigin = new KClosestPointsOrigin();
        int[][] result = kClosestPointsOrigin.kClosest(points,K);
        System.out.println(result);
    }

    public int[][] kClosest(int[][] points, int K) {
        List<Point> list = new LinkedList<>();

        for(int[] point : points){
            list.add(new Point(point[0],point[1]));
        }
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.compareTo(o2);
            }
        });
        int[][] result = new int[K][2];
        for(int i = 0; i < K; i++){
            result[i] = new int[]{list.get(i).x,list.get(i).y};
        }
        return result;
    }

    public class Point implements Comparable<Point>{
        int x;
        int y;
        double distance;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            distance = Math.pow(x,2) + Math.pow(y,2);
        }

        @Override
        public int compareTo(Point o) {
            if(distance > o.distance)
                return 1;
            else if(distance < o.distance)
                return -1;
            else
                return 0;
        }
    }


    /**
     * faster solution
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestV1(int[][] points, int K) {

        int len = points.length;
        int left = 0;
        int right = len - 1;

        while(left <= right){
            int partitionIndex = partition(points,left,right);
            if(partitionIndex == K){
                break;
            }
            if(partitionIndex < K){
                left = partitionIndex + 1;
            }else{
                right = partitionIndex - 1;
            }
        }

        return Arrays.copyOfRange(points,0,K);
    }

    public int partition(int[][] points,int left,int right){
        int[] pivot = points[left];

        while(left < right){
            while(left < right && compare(points[right],pivot) <= 0)
                right--;
            points[left] = points[right];
            while(left < right && compare(points[left],pivot) >= 0)
                left++;
            points[right] = points[left];
        }
        points[left] = pivot;

        return left;
    }

    public int compare(int[] point1,int[] point2){
        return (point2[1] * point2[1] + point2[0] * point2[0]) - point1[1] * point1[1] - point1[0] * point1[0];
    }
}
