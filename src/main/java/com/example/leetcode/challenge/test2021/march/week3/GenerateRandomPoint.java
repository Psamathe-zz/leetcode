package com.example.leetcode.challenge.test2021.march.week3;


import java.util.Random;

/**
 * Given the radius and x-y positions of the center of a circle, write a function randPoint which generates a uniform random point in the circle.
 *
 * Note:
 *
 * input and output values are in floating-point.
 * radius and x-y position of the center of the circle is passed into the class constructor.
 * a point on the circumference of the circle is considered to be in the circle.
 * randPoint returns a size 2 array containing x-position and y-position of the random point, in that order.
 * Example 1:
 *
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1,0,0],[],[],[]]
 * Output: [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]]
 * Example 2:
 *
 * Input:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[10,5,-7.5],[],[],[]]
 * Output: [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has three arguments, the radius, x-position of the center, and y-position of the center of the circle. randPoint has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class GenerateRandomPoint {
    public static void main(String[] args) {

    }

    Random r;
    double radius;
    double x_center;
    double y_center;
    public GenerateRandomPoint(double radius, double x_center, double y_center) {
        r=new Random();
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        double randomX = r.nextDouble() * 2 - 1;
        double randomY = r.nextDouble() * 2 - 1;
        double x = x_center + radius * randomX;
        double y = y_center + radius * Math.sqrt(1 - Math.pow(randomX,2)) * randomY;
        return new double[]{x,y};
    }

    public double[] randPointV1() {
        r.nextDouble();
        double radRand = this.radius * Math.sqrt(r.nextDouble());;   // 半径范围：0~this.radius
        double angle = 2 * Math.PI * r.nextDouble(); // 偏转角范围：）~2PI

        return new double[]{radRand*Math.cos(angle)+this.x_center, radRand * Math.sin(angle)+this.y_center};
    }

}
