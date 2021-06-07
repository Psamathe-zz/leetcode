package com.example.leetcode.challenge.test2021.june.week1;

import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * Example 2:
 *
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 * Example 3:
 *
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 * Example 4:
 *
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 */
public class OpenLock {
    public static void main(String[] args) {

    }
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet();
        for (String d: deadends)
            dead.add(d);

        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node == null) {
                depth++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals(target)) {
                return depth;
            } else if (!dead.contains(node)) {
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * faster solution
     * @param deadends
     * @param target
     * @return
     */
    public int openLockV1(String[] deadends, String target) {
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";
        if (!deads.add(start)) {
            return -1;
        }
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        begin.add("0000");
        end.add(target);
        int level = 0;
        Set<String> temp;
        char[][] nextTable = {{'1', '9'}, {'2', '0'}, {'3', '1'}, {'4', '2'}, {'5', '3'}, {'6','4'}, {'7', '5'}, {'8', '6'}, {'9', '7'}, {'0', '8'}};
        while(!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
                temp = begin;
                begin = end;
                end = temp;
            }
            temp = new HashSet<>();
            for(String cur : begin) {
                if(end.contains(cur)) {
                    return level;
                }
                char[] input = cur.toCharArray();
                for (int i = 0; i < input.length; i++) {
                    char old = input[i];
                    for (char nextChar: nextTable[old - '0']) {
                        input[i] = nextChar;
                        String next = new String(input);
                        if (end.contains(next)) {
                            return level + 1;
                        }
                        if (deads.add(next)) {
                            temp.add(next);
                        }
                        input[i] = old;
                    }
                }
            }
            level++;
            begin = temp;
        }
        return -1;
    }
}
