package com.example.leetcode.weeklycontest.old.test283;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of integers nums. Perform the following steps:
 *
 * Find any two adjacent numbers in nums that are non-coprime.
 * If no such numbers are found, stop the process.
 * Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
 * Repeat this process as long as you keep finding two adjacent non-coprime numbers.
 * Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary order will lead to the same result.
 *
 * The test cases are generated such that the values in the final array are less than or equal to 108.
 *
 * Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [6,4,3,2,7,6,2]
 * Output: [12,7,6]
 * Explanation:
 * - (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [12,3,2,7,6,2].
 * - (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [12,2,7,6,2].
 * - (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [12,7,6,2].
 * - (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,6].
 * There are no more adjacent non-coprime numbers in nums.
 * Thus, the final modified array is [12,7,6].
 * Note that there are other ways to obtain the same resultant array.
 * Example 2:
 *
 * Input: nums = [2,2,1,1,3,3,3]
 * Output: [2,1,1,3]
 * Explanation:
 * - (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3,3].
 * - (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3].
 * - (2, 2) are non-coprime with LCM(2, 2) = 2. Now, nums = [2,1,1,3].
 * There are no more adjacent non-coprime numbers in nums.
 * Thus, the final modified array is [2,1,1,3].
 * Note that there are other ways to obtain the same resultant array.
 */
public class ReplaceNonCoprimeNumbersInArray {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/replace-non-coprime-numbers-in-array/solution/li-yong-zhan-mo-ni-gocpythonjava-by-endl-bnbv/
     * @param nums
     * @return
     */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> s = new ArrayList<Integer>();
        s.add(nums[0]);
        for (var i = 1; i < nums.length; ++i) {
            s.add(nums[i]);
            while (s.size() > 1) {
                var x = s.get(s.size() - 1);
                var y = s.get(s.size() - 2);
                var g = gcd(x, y);
                if (g == 1) break;
                s.remove(s.size() - 1);
                s.set(s.size() - 1, x / g * y);
            }
        }
        return s;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
