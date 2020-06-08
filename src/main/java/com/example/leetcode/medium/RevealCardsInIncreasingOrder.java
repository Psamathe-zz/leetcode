package com.example.leetcode.medium;

import java.util.*;

/**
 * In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.
 *
 * Initially, all the cards start face down (unrevealed) in one deck.
 *
 * Now, you do the following steps repeatedly, until all cards are revealed:
 *
 * Take the top card of the deck, reveal it, and take it out of the deck.
 * If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
 * If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
 * Return an ordering of the deck that would reveal the cards in increasing order.
 *
 * The first entry in the answer is considered to be the top of the deck.
 *
 *
 *
 * Example 1:
 *
 * Input: [17,13,11,2,3,5,7]
 * Output: [2,13,3,11,5,17,7]
 * Explanation:
 * We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.
 * After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
 * We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
 * We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
 * We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
 * We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
 * We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
 * We reveal 13, and move 17 to the bottom.  The deck is now [17].
 * We reveal 17.
 * Since all the cards revealed are in increasing order, the answer is correct.
 *  2 3 5 7 10 11 13 17
 *  2 10 5 7 3 11 13 17
 *  2 10 3 7 5 11 13 17
 *  2 10 3 13 5 11 7 17
 *
 *  2 3 5 7 11 13 17
 *  13 17
 *  11 17 13
 *  7 13 11 17
 *  5 17 7 13 11
 *  3 11 5 17 7 13
 *  2 13 3 11 5 17 7
 * Note:
 *
 * 1 <= A.length <= 1000
 * 1 <= A[i] <= 10^6
 * A[i] != A[j] for all i != j
 */
public class RevealCardsInIncreasingOrder {
    public static void main(String[] args) {
        int[] deck = new int[]{17,13,11,2,3,5,7,10};
        RevealCardsInIncreasingOrder revealCardsInIncreasingOrder = new RevealCardsInIncreasingOrder();
        revealCardsInIncreasingOrder.deckRevealedIncreasing(deck);
    }

    public int[] deckRevealedIncreasing(int[] deck) {
        int[] result = new int[deck.length];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(deck);
        int index = deck.length - 1;
        while (index >= 0){
            if(!queue.isEmpty()) {
                int tempValue = queue.poll();
                queue.add(tempValue);
            }
            queue.add(deck[index]);
            index--;
        }
        index = deck.length - 1;
        while (index >= 0){
            result[index] = queue.poll();
            index--;
        }

        return result;
    }

    /**
     * less memory
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasingV1(int[] deck) {
        Arrays.sort(deck);
        Deque<Integer> q = new ArrayDeque<>();
        int n = deck.length;
        for (int i = 0; i < n; i++) {
            q.offer(i);
        }

        int[] res = new int[n];
        for (int i : deck) {
            res[q.pollFirst()] = i;
            if (!q.isEmpty()) {
                q.offerLast(q.pollFirst());
            }
        }

        return res;
    }
}
