package com.example.leetcode.weeklycontest.old.test197;

/**
 * A delivery company wants to build a new service centre in a new city. The company knows the positions of all the customers in this city on a 2D-Map and wants to build the new centre in a position such that the sum of the euclidean distances to all customers is minimum.
 *
 * Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map, return the minimum sum of the euclidean distances to all customers.
 *
 * In other words, you need to choose the position of the service centre [xcentre, ycentre] such that the following formula is minimized:
 *
 *
 * Answers within 10^-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: positions = [[0,1],[1,0],[1,2],[2,1]]
 * Output: 4.00000
 * Explanation: As shown, you can see that choosing [xcentre, ycentre] = [1, 1] will make the distance to each customer = 1, the sum of all distances is 4 which is the minimum possible we can achieve.
 * Example 2:
 *
 *
 * Input: positions = [[1,1],[3,3]]
 * Output: 2.82843
 * Explanation: The minimum possible sum of distances = sqrt(2) + sqrt(2) = 2.82843
 * Example 3:
 *
 * Input: positions = [[1,1]]
 * Output: 0.00000
 * Example 4:
 *
 * Input: positions = [[1,1],[0,0],[2,0]]
 * Output: 2.73205
 * Explanation: At the first glance, you may think that locating the centre at [1, 0] will achieve the minimum sum, but locating it at [1, 0] will make the sum of distances = 3.
 * Try to locate the centre at [1.0, 0.5773502711] you will see that the sum of distances is 2.73205.
 * Be careful with the precision!
 * Example 5:
 *
 * Input: positions = [[0,1],[3,2],[4,5],[7,6],[8,9],[11,1],[2,12]]
 * Output: 32.94036
 * Explanation: You can use [4.3460852395, 4.9813795505] as the position of the centre.
 *
 *
 * Constraints:
 *
 * 1 <= positions.length <= 50
 * positions[i].length == 2
 * 0 <= positions[i][0], positions[i][1] <= 100
 */
public class BestPositionServiceCentre {
    public static void main(String[] args) {

    }
    double dis(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.abs((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
    }

    public double getMinDistSum(int[][] positions) {
    int[][] dx= new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    double eps = 1E-8;
    double t = 0.98;

        double x = .0, y = .0;
        double ret = 1e18;
        double step = 100;

        while (step > eps) {
            int flag = 1;
            while (flag == 1) {
                flag = 0;
                for (int i = 0; i < 4; i++) {
                    double nx = x + dx[i][0] * step;
                    double ny = y + dx[i][1] * step;
                    double tmp = .0;
                    for (int j = 0; j < positions.length; j++) {
                        tmp += dis((double)positions[j][0], (double)positions[j][1], nx, ny);
                    }
                    if (tmp < ret) {
                        ret = tmp;
                        x = nx;
                        y = ny;
                        flag = 1;
                    }
                }
            }
            step *= t;
        }
        return ret;
    }


    /**
     * faster solution
     * @param positions
     * @return
     */
    public double getMinDistSumV1(int[][] positions) {
        int n = positions.length;
        double[] curr = new double[]{0, 0};
        for (int[] p : positions) {
            curr[0] += p[0];
            curr[1] += p[1];
        }
        curr[0] /= n;
        curr[1] /= n;
        double ans = totalDist(curr, positions);

        double step = 16;
        double[] next = new double[2];
        int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for (int t = 0; t < 1000; t++) {
            boolean progress = false;
            for (int[] dir : dirs) {
                next[0] = curr[0] + step * dir[0];
                next[1] = curr[1] + step * dir[1];
                double nextDist = totalDist(next, positions);
                if (nextDist < ans) {
                    curr[0] = next[0];
                    curr[1] = next[1];
                    ans = nextDist;
                    progress = true;
                }
            }
            if (!progress) step /= 2;
        }
        return ans;
    }

    double dist(double[] a, int[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    double totalDist(double[] o, int[][] points) {
        double dist = 0;
        for (int[] p : points) dist += dist(o, p);
        return dist;
    }

}
