package com.example.leetcode.weeklycontest.old.test271;

/**
 * Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts amounti fruits at the position positioni. fruits is already sorted by positioni in ascending order, and each positioni is unique.
 *
 * You are also given an integer startPos and an integer k. Initially, you are at the position startPos. From any position, you can either walk to the left or right. It takes one step to move one unit on the x-axis, and you can walk at most k steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.
 *
 * Return the maximum total number of fruits you can harvest.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
 * Output: 9
 * Explanation:
 * The optimal way is to:
 * - Move right to position 6 and harvest 3 fruits
 * - Move right to position 8 and harvest 6 fruits
 * You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
 * Example 2:
 *
 *
 * Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
 * Output: 14
 * Explanation:
 * You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
 * The optimal way is to:
 * - Harvest the 7 fruits at the starting position 5
 * - Move left to position 4 and harvest 1 fruit
 * - Move right to position 6 and harvest 2 fruits
 * - Move right to position 7 and harvest 4 fruits
 * You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
 * Example 3:
 *
 *
 * Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
 * Output: 0
 * Explanation:
 * You can move at most k = 2 steps and cannot reach any position with fruits.
 */
public class MaximumFruitsHarvested {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-fruits-harvested-after-at-most-k-steps/solution/javahua-dong-chuang-kou-by-mei-yi-ge-min-g24c/
     * @param fruits
     * @param startPos
     * @param k
     * @return
     */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int l=0,r=0,sum=0,max=0;
        int leftBound=startPos-k,rightBound=startPos+k;
        for(;r<fruits.length;r++){
            if(fruits[r][0]>rightBound)
                break;
            if(fruits[r][0]<leftBound){
                l++;
            } else {
                sum+=fruits[r][1];
                while(Math.min(
                        2*(startPos-fruits[l][0])+(fruits[r][0]-startPos),
                        startPos-fruits[l][0]+2*(fruits[r][0]-startPos))>k){  //判断是否合理
                    sum-=fruits[l][1];
                    l++;
                }
                max=Math.max(sum,max);
            }
        }
        return max;
    }
}
