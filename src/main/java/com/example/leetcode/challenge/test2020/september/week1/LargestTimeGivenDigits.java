package com.example.leetcode.challenge.test2020.september.week1;


/**
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 *
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 *
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4]
 * Output: "23:41"
 * Example 2:
 *
 * Input: [5,5,5,5]
 * Output: ""
 *
 *
 * Note:
 *
 * A.length == 4
 * 0 <= A[i] <= 9
 */
public class LargestTimeGivenDigits {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/13123589.html
     * @param A
     * @return
     */
    public String largestTimeFromDigits(int[] A) {
        String res = "";
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i == j || i == k || j == k)
                        continue;
                    String hour = String.valueOf(A[i]) + String.valueOf(A[j]);
                    String minute = String.valueOf(A[k]) + String.valueOf(A[6 - i - j - k]);
                    String t = hour + ":" + minute;
                    if (hour.compareTo("23") <= 0  && minute.compareTo("59") <= 0 && res.compareTo(t) < 0)
                        res = t;
                }
            }
        }
        return res;
    }

    /**
     * faster solution
     * @param A
     * @return
     */
    public String largestTimeFromDigitsV1(int[] A) {
        String ans = "";
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i == j || i == k || j == k) continue; // avoid duplicate among i, j & k.
                    StringBuilder h = new StringBuilder().append(A[i]).append(A[j])
                            , m = new StringBuilder().append(A[k]).append(A[6 - i - j - k])
                            , t = h.append(":").append(m); // hour, minutes, & time.
                    if (h.toString().compareTo("24") < 0
                            && m.toString().compareTo("60") < 0
                            && ans.compareTo(t.toString()) < 0) ans = t.toString(); // hour < 24; minute < 60; update result.
                }
            }
        }
        return ans;
    }
}
