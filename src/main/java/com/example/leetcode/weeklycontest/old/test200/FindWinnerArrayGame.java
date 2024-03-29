package com.example.leetcode.weeklycontest.old.test200;

/**
 * Given an integer array arr of distinct integers and an integer k.
 *
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]). In each round of the game, we compare arr[0] with arr[1],
 * the larger integer wins and remains at position 0 and the smaller integer moves to the end of the array. The game ends when an integer wins k consecutive rounds.
 *
 * Return the integer which will win the game.
 *
 * It is guaranteed that there will be a winner of the game.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,3,5,4,6,7], k = 2
 * Output: 5
 * Explanation: Let's see the rounds of the game:
 * Round |       arr       | winner | win_count
 *   1   | [2,1,3,5,4,6,7] | 2      | 1
 *   2   | [2,3,5,4,6,7,1] | 3      | 1
 *   3   | [3,5,4,6,7,1,2] | 5      | 1
 *   4   | [5,4,6,7,1,2,3] | 5      | 2
 * So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.
 * Example 2:
 *
 * Input: arr = [3,2,1], k = 10
 * Output: 3
 * Explanation: 3 will win the first 10 rounds consecutively.
 * Example 3:
 *
 * Input: arr = [1,9,8,2,3,7,6,4,5], k = 7
 * Output: 9
 * Example 4:
 *
 * Input: arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
 * Output: 99
 */
public class FindWinnerArrayGame {
    public static void main(String[] args) {
        int[] arr = new int[]{2,1,3,5,4,6,7};
        int k = 2;
        FindWinnerArrayGame findWinnerArrayGame = new FindWinnerArrayGame();
        findWinnerArrayGame.getWinner(arr,k);
    }

    public int getWinner(int[] arr, int k) {
        int length = arr.length;
        boolean before;
        boolean after;
        int res = arr[0];
        int maxK = k;
        for (int i = 0; i < length; i++) {
            before = false;
            after = false;
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[i]){
                    before = true;
                    break;
                }
            }
            if(i != 0)
                maxK = k-1;
            for (int j = i+1,count = 0; j < length && count < maxK; j++,count++) {
                if(arr[j] > arr[i]) {
                    after = true;
                    break;
                }
            }
            if(!before && ! after) {
                res = arr[i];
                break;
            }
        }
        return res;
    }


    /**
     * faster solution
     * @param arr
     * @param k
     * @return
     */
    public int getWinnerV1(int[] arr, int k) {
        if (k == 1) return Math.max(arr[0], arr[1]);
        int n = arr.length, cnt = 0, cur = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > cur) {
                cnt = 1;
                cur = arr[i];
            } else {
                cnt++;
                if (cnt == k)
                    return cur;
            }
        }
        return cur;
    }
}
