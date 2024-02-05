package com.example.leetcode.weeklycontest.old.test209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
 *
 * Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 *
 *
 * You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
 *
 * There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
 *
 * Return the maximum number of points you can see.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
 * Example 2:
 *
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including the one at your location.
 * Example 3:
 *
 *
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 109
 */
public class MaximumNumberVisiblePoints {
    public static void main(String[] args) {

    }

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        double[] angles = new double[points.size()];
        int index = 0;
        int count = 0;   // 自己所在的位置有多少个点
        for(List<Integer> point : points) {
            if(point.get(0).equals(location.get(0)) && point.get(1).equals(location.get(1))) {
                count++;
                continue;
            }
            // 得到[0,360)范围的角度
            double theta = Math.atan2(point.get(1) - location.get(1), point.get(0) - location.get(0)) / Math.PI * 180;
            if(theta < 0)
                theta += 360;
            angles[index++] = theta;
        }
        Arrays.sort(angles, 0, index);
        double[] nums = new double[index * 2];
        System.arraycopy(angles, 0, nums, 0, index);
        for(int i = 0; i < index; i++) {
            angles[i] += 360;
        }
        System.arraycopy(angles, 0, nums, index, index);

        // 双指针法找angle覆盖下的最长的子数组
        int left = 0, res = 0;
        for(int i = 0; i < nums.length; i++) {
            double diff = nums[i] - nums[left];
            while(left <= i && diff > angle) {
                left++;
                diff = nums[i] - nums[left];
            }
            res = Math.max(res, i - left + 1);
        }
        return res + count;
    }


    /**
     * faster solution
     * @param points
     * @param angle
     * @param location
     * @return
     */
    public int visiblePointsV1(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> list = new ArrayList<>();
        int count = 0;
        for (List<Integer> p: points) {
            if (p.get(0)-location.get(0) == 0) {
                if (p.get(1)-location.get(1) > 0) {
                    list.add(90.0);
                } else if (p.get(1)-location.get(1) < 0) {
                    list.add(-90.0);
                } else {
                    count++;
                }
            } else {
                double tan = (p.get(1)-location.get(1)) * 1.0 / (p.get(0)-location.get(0));
                if (p.get(0) > location.get(0)) {
                    list.add(180 / Math.PI * Math.atan(tan));
                }
                else {
                    double a = 180 / Math.PI * Math.atan(-tan);
                    if (a > 0) {
                        list.add(180.0-a);
                    } else {
                        list.add(-180.0-a);
                    }
                }
            }
        }
        Collections.sort(list);
        int s = list.size();
        for (int i = 0; i < s; i++) {
            list.add(list.get(i)+360.0);
        }
        int l = 0, max = 0;
        for (int r = 0; r < list.size(); r++) {
            while (list.get(r)-list.get(l) > angle+1e-5) {
                l++;
            }
            max = Math.max(max, r-l+1);
        }

        return max+count;
    }
}
