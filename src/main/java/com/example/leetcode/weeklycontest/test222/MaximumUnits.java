package com.example.leetcode.weeklycontest.test222;


import java.util.Arrays;
import java.util.Comparator;

/**
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 *
 * Return the maximum total number of units that can be put on the truck.
 *
 *
 *
 * Example 1:
 *
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation: There are:
 * - 1 box of the first type that contains 3 units.
 * - 2 boxes of the second type that contain 2 units each.
 * - 3 boxes of the third type that contain 1 unit each.
 * You can take all the boxes of the first and second types, and one box of the third type.
 * The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
 * Example 2:
 *
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 91
 */
public class MaximumUnits {
    public static void main(String[] args) {
        int[][] boxTypes = new int[][]{
                {1,3},
                {2,2},
                {3,1}
        };
        int truckSize = 4;
        MaximumUnits maximumUnits = new MaximumUnits();
        maximumUnits.maximumUnits(boxTypes, truckSize);
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int length = boxTypes.length;
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int res = 0;
        int index = 0;
        int size;
        while (truckSize > 0){
            size = truckSize >= boxTypes[index][0]? boxTypes[index][0]: truckSize;
            res += size * boxTypes[index][1];
            truckSize -= size;
            index++;
            if(index == length)
                break;
        }

        return res;
    }
}
