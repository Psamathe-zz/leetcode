package com.example.leetcode.challenge.test2020.november.week2;


import java.util.HashSet;

/**
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 *
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 *
 * Example:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 *
 *
 * Note:
 *
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 */
public class ValidSquare {
    public static void main(String[] args) {
        int[] p1 = new int[]{0,0};
        int[] p2 = new int[]{1,1};
        int[] p3 = new int[]{1,0};
        int[] p4 = new int[]{0,1};
        ValidSquare validSquare = new ValidSquare();
        boolean res = validSquare.validSquare(p1,p2,p3,p4);
        System.out.println(res);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/6914746.html#:~:text=A%20valid%20square%20has%20four,Input%20points%20have%20no%20order.
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> s = new HashSet<>();
        s.add(helper(p1,p2));
        s.add(helper(p1,p3));
        s.add(helper(p1,p4));
        s.add(helper(p2,p3));
        s.add(helper(p2,p4));
        s.add(helper(p3,p4));
        return !s.contains(0) && s.size() == 2;
    }

    public int helper(int[] p1,int[] p2){
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
