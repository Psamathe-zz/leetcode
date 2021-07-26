package com.example.leetcode.challenge.test2021.July.week3;


/**
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * You are given a string dominoes representing the initial state where:
 *
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 *
 *
 *
 * Example 1:
 *
 * Input: dominoes = "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Example 2:
 *
 *
 * Input: dominoes = ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 *
 *
 * Constraints:
 *
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] is either 'L', 'R', or '.'.
 */
public class PushDominoes {
    public static void main(String[] args) {
        PushDominoes pushDominoes = new PushDominoes();
        String res = pushDominoes.pushDominoes("RR.L");
        System.out.println(res);
    }

    public String pushDominoes(String dominoes) {
        int length = dominoes.length();
        char[] chars = dominoes.toCharArray();
        char[] res= new char[length];
        int[] left = new int[length];
        int[] right = new int[length];
        for (int i = 0; i < length; i++) {
            if(chars[i] == 'R'){
                right[i] = length;
            } else if(chars[i] == 'L'){
                right[i] = 0;
            } else {
                if(i > 0 && right[i - 1] > 0)
                    right[i] = right[i - 1] - 1;
            }
        }

        for (int i = length - 1; i >= 0 ; i--) {
            if(chars[i] == 'L'){
                left[i] = length;
            } else if(chars[i] == 'R'){
                left[i] = 0;
            } else {
                if(i < length - 1 && left[i+1] > 0){
                    left[i] = left[i+1] - 1;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if(left[i] > right[i]){
                res[i] = 'L';
            } else if(left[i] < right[i]){
                res[i] = 'R';
            } else
                res[i] = '.';
        }
        return String.valueOf(res);
    }
}
