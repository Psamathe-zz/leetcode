package com.example.leetcode.weeklycontest.old.test259;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 *
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 *                                //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 *                                //   - The first, second, and third points
 *                                //   - The first, third, and fourth points
 */
public class DetectSquares {
    public static void main(String[] args) {
        DetectSquares obj = new DetectSquares();
        obj.add(new int[]{3,10});
        obj.add(new int[]{11, 2});
        obj.add(new int[]{3, 2});
        int param_2 = obj.count(new int[]{11, 10});
        int param_3 = obj.count(new int[]{14, 8});
        obj.add(new int[]{11, 2});
        int param_4 = obj.count(new int[]{11, 10});
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
    }

    Map<Point, Integer> count;
    public DetectSquares() {
        count = new HashMap<>();
    }

    public void add(int[] point) {
        count.compute(new Point(point[0], point[1]), (k,v) -> v == null ? 1:v+1);
        System.out.println(count);
    }

    public int count(int[] point) {
        // 只找下方对角线的点
        int res = 0;
        int x = point[0];
        int y = point[1];
        for (Point key : count.keySet()) {
            int delX = key.x;
            int delY = key.y;
            int delCnt = count.get(key);
            if (delX != x && delY != y && Math.abs(delX - x) == Math.abs(delY - y) && delCnt > 0) {
                // 计算平行X的点(delX, y)和平行Y的点(x, delY)个数
                int xParCnt = count.getOrDefault(new Point(delX,y), 0);
                int yParCnt = count.getOrDefault(new Point(x ,delY), 0);
                res += delCnt * xParCnt * yParCnt;
            }
        }
        return res;
    }

    public class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
