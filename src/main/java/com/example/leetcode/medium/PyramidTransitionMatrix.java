package com.example.leetcode.medium;

import java.util.*;

/**
 * We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.
 *
 * We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.
 *
 * We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
 *
 * Return true if we can build the pyramid all the way to the top, otherwise false.
 *
 * Example 1:
 *
 * Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
 * Output: true
 * Explanation:
 * We can stack the pyramid like this:
 *     A
 *    / \
 *   G   E
 *  / \ / \
 * B   C   D
 *
 * We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.
 *
 *
 * Example 2:
 *
 * Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
 * Output: false
 * Explanation:
 * We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 *          C
 *  A  A  B  A
 * Constraints:
 *
 * bottom will be a string with length in range [2, 8].
 * allowed will have length in range [0, 200].
 * Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 * "CCC"
 * ["CBB","ACB","ABD","CDB","BDC","CBC","DBA","DBB","CAB","BCB","BCC","BAA","CCD","BDD","DDD","CCA","CAA","CCC","CCB"]
 */
public class PyramidTransitionMatrix {
    public static void main(String[] args) {
        String bottom = "CCC";
        List<String> allowed = new ArrayList<>(Arrays.asList("CBB","ACB","ABD","CDB","BDC","CBC","DBA","DBB","CAB","BCB","BCC","BAA","CCD","BDD","DDD","CCA","CAA","CCC","CCB"));

        PyramidTransitionMatrix pyramidTransitionMatrix = new PyramidTransitionMatrix();
        boolean result = pyramidTransitionMatrix.pyramidTransition(bottom,allowed);
        System.out.println(result);
    }


    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();
        for(String str : allowed){
            String key = str.substring(0,2);
            if(map.containsKey(key)){
                map.get(key).add(str.charAt(2));
            } else {
                Set<Character> set = new HashSet<>();
                set.add(str.charAt(2));
                map.put(key,set);
            }
        }
        return help(bottom, "", map);
    }

    public boolean help(String cur, String above, Map<String, Set<Character>> map){
        if (cur.length() == 2 && above.length() == 1)
            return true;
        if (above.length() == cur.length() - 1)
            return help(above, "", map);
        int pos = above.length();
        String base = cur.substring(pos, pos + 2);
        if (map.containsKey(base)) {
            for (char ch : map.get(base)) {
                if (help(cur, above + ch, map)) {
                    return true;
                }
            }
        }
        return false;
    }
}
