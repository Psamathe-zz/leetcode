package com.example.leetcode.challenge.test2021.march.week5;


import java.util.ArrayList;
import java.util.List;

/**
 * You want to form a target string of lowercase letters.
 *
 * At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.
 *
 * On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.
 *
 * For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)
 *
 * If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.
 *
 * For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".
 *
 * Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: stamp = "abc", target = "ababc"
 * Output: [0,2]
 * ([1,0,2] would also be accepted as an answer, as well as some other answers.)
 * Example 2:
 *
 * Input: stamp = "abca", target = "aabcaca"
 * Output: [3,0,1]
 */
public class StampingTheSequence {
    public static void main(String[] args) {

    }

    /**
     * http://www.noteanddata.com/leetcode-936-Stamping-The-Sequence-java-solution-note.html
     * @param stamp
     * @param target
     * @return
     */
    public int[] movesToStamp(String stamp, String target) {
        boolean[] visited = new boolean[target.length()];
        char[] arr = target.toCharArray();
        List<Integer> list = new ArrayList<>();
        int matchCount = 0;
        while(matchCount != target.length() ) {
            boolean matched = false;
            for(int i = 0; i < arr.length && !matched; ++i) {
                if(visited[i]) continue;
                if(match(arr, i, stamp)) {
                    matched = true;
                    for(int j = 0; j < stamp.length(); ++j) {
                        if(arr[i+j] != '*') {
                            matchCount++;
                            arr[i+j] = '*';
                        }
                    }
                    visited[i] = true;
                    list.add(i);
                }

            }
            if(!matched) {
                break;
            }
        }

        if(matchCount != target.length())
            return new int[]{};

        int[] ret = new int[list.size()];
        for(int i = 0; i < list.size(); ++i) {
            ret[i] = list.get(list.size()-i-1);
        }
        return ret;
    }
    public boolean match(char[] arr, int index, String stamp) {
        int i = 0;
        for(; i < stamp.length() && i + index < arr.length; ++i) {
            if(arr[i + index] != stamp.charAt(i) && arr[i+index] != '*') {
                return false;
            }
        }
        return i == stamp.length();
    }
}
