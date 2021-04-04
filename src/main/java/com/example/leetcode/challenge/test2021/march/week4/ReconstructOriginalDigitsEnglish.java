package com.example.leetcode.challenge.test2021.march.week4;


/**
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 *
 * Note:
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
 * Input length is less than 50,000.
 * Example 1:
 * Input: "owoztneoer"
 *
 * Output: "012"
 * Example 2:
 * Input: "fviefuro"
 *
 * Output: "45"
 */
public class ReconstructOriginalDigitsEnglish {
    public static void main(String[] args) {
        ReconstructOriginalDigitsEnglish reconstructOriginalDigitsEnglish = new ReconstructOriginalDigitsEnglish();
        reconstructOriginalDigitsEnglish.originalDigits("owoztneoer");
    }

    /**
     * https://www.cnblogs.com/grandyang/p/5996239.html
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        int[] nums = new int[10];
        int[] counts = new int[26];
        for (char c : s.toCharArray())
            ++counts[c - 'a'];
        nums[0] = counts['z' - 'a'];
        nums[2] = counts['w' - 'a'];
        nums[4] = counts['u' - 'a'];
        nums[6] = counts['x' - 'a'];
        nums[8] = counts['g' - 'a'];
        nums[1] = counts['o' - 'a'] - nums[0] - nums[2] - nums[4];
        nums[3] = counts['h' - 'a'] - nums[8];
        nums[5] = counts['f' - 'a'] - nums[4];
        nums[7] = counts['s' - 'a'] - nums[6];
        nums[9] = counts['i' - 'a'] - nums[6] - nums[8] - nums[5];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums[i]; ++j) {
                stringBuilder.append(i);
            }
        }
        return stringBuilder.toString();
    }
}
