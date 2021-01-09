package com.example.leetcode.challenge.test2020.may.week5;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * te
 * execution
 */
public class EditDistance {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        EditDistance editDistance = new EditDistance();
        int result = editDistance.minDistance(word1,word2);
        System.out.println(result);
    }
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();

        int[][] map = new int[length1 + 1][length2 + 1];

        for (int i = 0; i <= length1; i++) {
            map[i][0] = i;
        }
        for (int j = 0; j <= length2; j++) {
            map[0][j] = j;
        }

        for (int i = 1; i <= length1; ++i) {
            for (int j = 1; j <= length2; ++j) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    map[i][j] = map[i - 1][j - 1];
                } else {
                    map[i][j] = Math.min(Math.min(map[i - 1][j - 1], map[i - 1][j]), map[i][j - 1]) + 1;
                }
            }
        }
        return map[length1][length2];
    }



    /**
     * faster solution
     * @param word1
     * @param word2
     * @return
     */
    char[] w1, w2;
    int[][] memo;

    public int minDistanceV1(String word1, String word2) {
        w1 = word1.toCharArray();
        w2 = word2.toCharArray();
        memo = new int[w1.length][w2.length];
        return f(w1.length - 1, w2.length - 1);
    }

    private int f(int i, int j) {
        if (i < 0)
            return j + 1;
        if (j < 0)
            return i + 1;
        if (memo[i][j] > 0)
            return memo[i][j];
        if (w1[i] == w2[j])
            return memo[i][j] = f(i - 1, j - 1);
        return memo[i][j] = 1 + Math.min(Math.min(f(i - 1, j), f(i, j - 1)), f(i - 1, j - 1));
    }

}
