package com.example.leetcode.challenge.test2021.september.week1;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 *
 * You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.
 *
 * Return the coordinates of trees that are exactly located on the fence perimeter.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
 * Example 2:
 *
 *
 * Input: points = [[1,2],[2,2],[4,2]]
 * Output: [[4,2],[2,2],[1,2]]
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 3000
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * All the given points are unique.
 */
public class ErectFence {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/erect-the-fence/solution/java-tu-bian-xing-dai-ma-jian-ji-yi-dong-tu2j/
     * @param points
     * @return
     */
    public int[][] outerTrees(int[][] points) {
        /*
            convex

            1. find the leftmost point

            2. for
                find the next one
                add into data strcurture

            3. List
               -> arr
        */

        int n = points.length;

        if(n < 4 || onSameLine(points))
            return points;

        int l = 0;
        for(int i = 0; i < n; i++){
            if(points[l][0] > points[i][0])
                l = i;
        }

        int p = l;
        List<int[]> list = new ArrayList<>();
        do{
            list.add(points[p]);

            int q = (p + 1) % n;

            //get the next point after p
            for(int i = 0; i < n; i++){
                if(i != p && i != q && orientation(points[p], points[q], points[i]) == 1)
                    q = i;
            }


            for(int i = 0; i < n; i ++){
                if(i != p && i != q && onSegement(points[p], points[q], points[i]))
                    list.add(points[i]);
            }

            p = q;

        }while(p != l);


        int[][] res = new int[list.size()][2];
        for(int i = 0; i< list.size(); i++){
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }

        return res;
    }

    /*
        on val == 1, counter-clockwise
        on val == 2, clock wise
        on val == 0, horizontal
    */
    private int orientation(int[] p, int[] q, int[] r){
        int val = (q[1] - r[1]) * (r[0] - p[0]) - (q[0] - r[0]) * (r[1] - p[1]);

        if(val == 0)
            return 0;

        return val > 0 ? 1 : 2;
    }

    private boolean onSegement(int[] p, int[] q, int[] r){
        return r[0] >= Math.min(p[0], q[0]) && r[0] <= Math.max(p[0], q[0])
                && r[1] >= Math.min(p[1], q[1]) && r[1] <= Math.max(p[1], q[1])
                && orientation(p, q, r) == 0;
    }

    private boolean onSameLine(int[][] points){
        for(int i = 0; i < points.length - 2; i++){
            if(orientation(points[i], points[i + 1], points[i + 2]) != 0)
                return false;
        }

        return true;
    }

}
