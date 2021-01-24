package com.example.leetcode.weeklycontest.test225;


/**
 * You are given two strings a and b that consist of lowercase letters. In one operation, you can change any character in a or b to any lowercase letter.
 *
 * Your goal is to satisfy one of the following three conditions:
 *
 * Every letter in a is strictly less than every letter in b in the alphabet.
 * Every letter in b is strictly less than every letter in a in the alphabet.
 * Both a and b consist of only one distinct letter.
 * Return the minimum number of operations needed to achieve your goal.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "aba", b = "caa"
 * Output: 2
 * Explanation: Consider the best way to make each condition true:
 * 1) Change b to "ccc" in 2 operations, then every letter in a is less than every letter in b.
 * 2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b is less than every letter in a.
 * 3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of one distinct letter.
 * The best way was done in 2 operations (either condition 1 or condition 3).
 * Example 2:
 *
 * Input: a = "dabadd", b = "cda"
 * Output: 3
 * Explanation: The best way is to make condition 1 true by changing b to "eee".
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 105
 * a and b consist only of lowercase letters.
 */
public class ChangeMinimumCharacters {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/solution/javajie-jue-jie-ti-si-lu-qing-xi-by-shix-9hyf/
     * @param a
     * @param b
     * @return
     */
    public int minCharacters(String a, String b) {
        int[] ac = new int[26];
        int[] bc = new int[26];
        for (char c : a.toCharArray()) {
            ac[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            bc[c - 'a']++;
        }
        //第三个条件，修改字符最少，改成同一个字符
        int ans = a.length() + b.length();
        for(int i=0; i<26; i++) {
            ans = Math.min(ans, a.length() + b.length() - ac[i] - bc[i]);
        }
        return Math.min(ans, Math.min(smaller(ac, bc), smaller(bc, ac)));
    }

    private int smaller(int[] a, int[] b) {
        //第一个第二个条件类似，封装到一个方法
        //因为字母总共就26个，所以枚举所有可能， 依次判断a中所有元素小于等于字符k,b中所有元素大于字符k，然后求出最小值即可
        int ans = Integer.MAX_VALUE;
        for(int i=0; i < 25; i++) {//因为z是最大的元素，所以不能让a中所有元素小于等于z，这一点需要排除，所以只循环到24
            int total = 0;//需要修改的元素的总数
            for(int j=i+1; j<26; j++) {//把a中素有大于i的元素统计起来，保证a中所有元素小于等于i，需要遍历a中所有元素
                total += a[j];
            }
            for(int j=0; j<=i; j++) {//把b中所有小于等于元素i的都统计起来，使调整后所有元素都大于i
                total += b[j];
            }
            ans = Math.min(ans, total);
        }
        return ans;
    }


    /**
     * faster solution
     * @param a
     * @param b
     * @return
     */
    public int minCharactersV0(String a, String b) {
        // Make all equal
        int[] countA = new int[26], countB = new int[26];
        for (char c: a.toCharArray()) countA[c-'a']++;
        for (char c: b.toCharArray()) countB[c-'a']++;

        int la = a.length(), lb = b.length();
        int min = la+lb;
        int suma = 0, sumb = 0;
        for (int i = 0; i < 26; i++) {
            if (i != 0) {
                min = Math.min(min, la-suma+sumb);
                min = Math.min(min, lb-sumb+suma);
            }
            suma += countA[i];
            sumb += countB[i];
            min = Math.min(min, la+lb-countA[i]-countB[i]);
        }
        return min;
    }
}
