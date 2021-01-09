package com.example.leetcode.challenge.test2020.november.week3;


/**
 * There is a special square room with mirrors on each of the four walls.  Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.
 *
 * The square room has walls of length p, and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.
 *
 * Return the number of the receptor that the ray meets first.  (It is guaranteed that the ray will meet a receptor eventually.)
 *
 *
 *
 * Example 1:
 *
 * Input: p = 2, q = 1
 * Output: 2
 * Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
 *
 * Note:
 *
 * 1 <= p <= 1000
 * 0 <= q <= p
 */
public class MirrorReflection {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/10646040.html
     * p为奇数，q为奇数时，到达接收器1。
     * p为奇数，q为偶数时，到达接收器0。
     * p为偶数，q为奇数时，到达接收器2。
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection(int p, int q) {
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0)
            return 2;
        if (q % 2 == 0)
            return 0;
        return 1;
    }
}
