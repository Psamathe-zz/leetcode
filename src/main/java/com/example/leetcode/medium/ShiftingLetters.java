package com.example.leetcode.medium;

/**
 * We have a string S of lowercase letters, and an integer array shifts.
 *
 * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 *
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 *
 * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
 *
 * Return the final string after all such shifts to S are applied.
 *
 * Example 1:
 *
 * Input: S = "abc", shifts = [3,5,9]
 * Output: "rpl"
 * Explanation:
 * We start with "abc".
 * After shifting the first 1 letters of S by 3, we have "dbc".
 * After shifting the first 2 letters of S by 5, we have "igc".
 * After shifting the first 3 letters of S by 9, we have "rpl", the answer.
 * Note:
 *
 * 1 <= S.length = shifts.length <= 20000
 * 0 <= shifts[i] <= 10 ^ 9
 * nput
 * "bad"
 * [10,20,30]"mkgfzkkuxownxvfvxasy"
 * [505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513]
 */
public class ShiftingLetters {
    public static void main(String[] args) {
        String S = "mkgfzkkuxownxvfvxasy";
        int[] shifts = new int[]{505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513};
        ShiftingLetters shiftingLetters = new ShiftingLetters();
        String result = shiftingLetters.shiftingLetters(S,shifts);
        System.out.println(result);

    }
    public String shiftingLetters(String S, int[] shifts) {
        int length = shifts.length;
        int[] finalShifts = new int[length];

        char[] chars = S.toCharArray();
        for(int i = 0; i < length;i++){
            finalShifts[i] = chars[i] - 'a';
            for (int j = 0; j <= i; j++) {
                finalShifts[j] += shifts[i];
                finalShifts[j] = finalShifts[j] % 26;
            }
        }

        for (int i = 0; i < length; i++) {
            chars[i] = (char) ('a' + finalShifts[i]);
        }

        return String.valueOf(chars);
    }

    /**
     * faster solution
     * @param S
     * @param shifts
     * @return
     */
    public String shiftingLettersV1(String S, int[] shifts) {
        char[] arr = S.toCharArray();
        int shift = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            shift = (shift + shifts[i]) % 26;
            arr[i] = (char)((arr[i] - 'a' + shift) % 26 + 'a');
        }
        return new String(arr);
    }
}
