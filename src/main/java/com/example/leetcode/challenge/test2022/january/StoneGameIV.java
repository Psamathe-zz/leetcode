package com.example.leetcode.challenge.test2022.january;

import java.util.HashMap;
import java.util.Map;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * Example 3:
 *
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 *
 */
public class StoneGameIV {
    public static void main(String[] args) {

    }

    Map<Integer,Boolean> map = new HashMap<>();
    public boolean winnerSquareGame(int n) {
        if(map.containsKey(n))
            return map.get(n);
        int x = (int)Math.sqrt(n);
        boolean temp;
        for (int i = x; i >= 1 ; i--) {
            if(map.containsKey(n - i * i)) {
                temp = map.get(n - i * i);
                if(!temp) {
                    map.put(n,true);
                    return true;
                }
            } else {
                temp = winnerSquareGame(n - i * i);
                if(!temp){
                    map.put(n,true);
                    return true;
                }
            }
        }
        map.put(n,false);
        return false;
    }
}
