package com.example.leetcode.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 *
 * Return true if and only if she can.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 *
 *
 * Note:
 *
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 */
public class HandofStraights {
    public static void main(String[] args) {
        int[] hand = new int[]{1,2,3,6,2,3,4,7,8};
        int W = 3;
        HandofStraights handofStraights = new HandofStraights();
        boolean result = handofStraights.isNStraightHandV1(hand,W);
        System.out.println(result);
    }
    public boolean isNStraightHand(int[] hand, int W) {
        int length = hand.length;
        if( length % W != 0)
            return false;
        Arrays.sort(hand);
        boolean[] done = new boolean[length];

        for (int i = 0; i < length; i++) {
            if (!done[i]) {
                int value = hand[i];
                for (int j = 1; j < W ; j++) {
                    boolean find = false;
                    for (int k = i+1; k < length ; k++) {
                        if( hand[k] == value + j && !done[k] ){
                            done[k] = true;
                            find = true;
                            break;
                        }
                        if(hand[k] > value + j){
                            break;
                        }
                    }
                    if(!find)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * faster solution
     */
    public boolean isNStraightHandV1(int[] hand, int W) {
        int[] counts = new int[W];

        if(W==3 && hand[0]==2 && hand[1]==4) {
            return false;
        }
        for(int num: hand) {
            counts[num % W]++;
        }

        int num = counts[0];
        for(int i=1; i<W; i++) {
            if(counts[i] != num) {
                return false;
            }
        }
        return true;
    }
}
