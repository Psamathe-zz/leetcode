package com.example.leetcode.weeklycontest.test285;

import java.util.ArrayList;
import java.util.List;

/**
 * Alice and Bob are opponents in an archery competition. The competition has set the following rules:
 *
 * Alice first shoots numArrows arrows and then Bob shoots numArrows arrows.
 * The points are then calculated as follows:
 * The target has integer scoring sections ranging from 0 to 11 inclusive.
 * For each section of the target with score k (in between 0 to 11), say Alice and Bob have shot ak and bk arrows on that section respectively. If ak >= bk, then Alice takes k points. If ak < bk, then Bob takes k points.
 * However, if ak == bk == 0, then nobody takes k points.
 * For example, if Alice and Bob both shot 2 arrows on the section with score 11, then Alice takes 11 points. On the other hand, if Alice shot 0 arrows on the section with score 11 and Bob shot 2 arrows on that same section, then Bob takes 11 points.
 *
 * You are given the integer numArrows and an integer array aliceArrows of size 12, which represents the number of arrows Alice shot on each scoring section from 0 to 11. Now, Bob wants to maximize the total number of points he can obtain.
 *
 * Return the array bobArrows which represents the number of arrows Bob shot on each scoring section from 0 to 11. The sum of the values in bobArrows should equal numArrows.
 *
 * If there are multiple ways for Bob to earn the maximum total points, return any one of them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
 * Output: [0,0,0,0,1,1,0,0,1,2,3,1]
 * Explanation: The table above shows how the competition is scored.
 * Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
 * It can be shown that Bob cannot obtain a score higher than 47 points.
 * Example 2:
 *
 *
 * Input: numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
 * Output: [0,0,0,0,0,0,0,0,1,1,1,0]
 * Explanation: The table above shows how the competition is scored.
 * Bob earns a total point of 8 + 9 + 10 = 27.
 * It can be shown that Bob cannot obtain a score higher than 27 points.
 *
 */
public class MaximumPointsInArcheryCompetition {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-points-in-an-archery-competition/solution/java-by-weilai-mrcb4/
     */
    //max为当前最大得分数
    int max = 0;
    //list0用于记录当前最大分数的方法。
    List<Integer> list0;
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        list0 = new ArrayList<>();
        dfsMax(aliceArrows,0,numArrows,0,new ArrayList<>());//开始暴力尝试
        int[] arr = new int[aliceArrows.length];
        for (int i = 0;i < arr.length;i ++) {//将list0转化为数组
            arr[i] = list0.get(i);
        }
        return arr;
    }

    //arr：为题目中给的aliceArrows数组
    //value：为当前的得分数
    //num：为当前手中剩余的箭数
    //now: 为当前需要判断的区域（放在for循环里的）。
    //temp：为当前B射箭的方法。
    void dfsMax(int[] arr,int value,int num,int now,List<Integer> temp) {
        if (max < value) {//有得分更大的方法出现
            if (temp.size() == arr.length) {//长度必须和length相同
                max = value;
                list0 = new ArrayList<>(temp);
                if (num != 0) {//如果有剩余的箭，随便射到哪里都可以，这里就假设都射到0区域了
                    list0.set(0, list0.get(0) + num);
                }
            }
        }

        for (int i = now;i < arr.length;i ++) {//for循环从now开始
            if (num >= arr[i] + 1) {//手中剩余的箭满足能得分的条件
                List<Integer> temp0 = new ArrayList<>(temp);
                temp0.add(arr[i] + 1);
                dfsMax(arr,value + i,num - arr[i] - 1,i + 1,temp0);
            }
            temp.add(0);//当前位置不得分
            if (i == arr.length - 1) {//执行最后一位不得分的情况
                dfsMax(arr,value,num,i + 1,new ArrayList<>(temp));
            }
        }
    }
}
