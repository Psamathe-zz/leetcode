package com.example.leetcode.biweeklycontest.contest95;

/**
 * Given four integers length, width, height, and mass, representing the dimensions and mass of a box, respectively, return a string representing the category of the box.
 *
 * The box is "Bulky" if:
 * Any of the dimensions of the box is greater or equal to 104.
 * Or, the volume of the box is greater or equal to 109.
 * If the mass of the box is greater or equal to 100, it is "Heavy".
 * If the box is both "Bulky" and "Heavy", then its category is "Both".
 * If the box is neither "Bulky" nor "Heavy", then its category is "Neither".
 * If the box is "Bulky" but not "Heavy", then its category is "Bulky".
 * If the box is "Heavy" but not "Bulky", then its category is "Heavy".
 * Note that the volume of the box is the product of its length, width and height.
 *
 *
 *
 * Example 1:
 *
 * Input: length = 1000, width = 35, height = 700, mass = 300
 * Output: "Heavy"
 * Explanation:
 * None of the dimensions of the box is greater or equal to 104.
 * Its volume = 24500000 <= 109. So it cannot be categorized as "Bulky".
 * However mass >= 100, so the box is "Heavy".
 * Since the box is not "Bulky" but "Heavy", we return "Heavy".
 * Example 2:
 *
 * Input: length = 200, width = 50, height = 800, mass = 50
 * Output: "Neither"
 * Explanation:
 * None of the dimensions of the box is greater or equal to 104.
 * Its volume = 8 * 106 <= 109. So it cannot be categorized as "Bulky".
 * Its mass is also less than 100, so it cannot be categorized as "Heavy" either.
 * Since its neither of the two above categories, we return "Neither".
 */
public class CategorizeBoxAccordingCriteria {
    public static void main(String[] args) {

    }

    public String categorizeBox(int length, int width, int height, int mass) {
        boolean isBulky = isBulky(length, width, height);
        boolean isHeavy = mass >= 100;

        if(isBulky && isHeavy)
            return "Both";
        else if(isBulky)
            return "Bulky";
        else if(isHeavy)
            return "Heavy";
        else
            return "Neither";
    }

    public boolean isBulky(int length, int width, int height) {
        int MOD1 = (int) Math.pow(10, 4);
        int MOD2 = (int) Math.pow(10, 9);
        long volume = (long)length *  width * height;
        return length >= MOD1 || width >= MOD1 || height >= MOD1 || volume >= MOD2;
    }
}
