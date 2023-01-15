package com.example.leetcode.challenge.test2022.november;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 *
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 *
 *
 * Example 1:
 *
 * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * Example 2:
 *
 * Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 * Example 3:
 *
 * Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * Output: 3
 */
public class MinimumGeneticMutation {
    public static void main(String[] args) {

    }

    public int minMutation(String start, String end, String[] bank) {
        if(start.equals(end)) {
            return 0;
        }

        Set<String> bankSet = new HashSet<>();
        for(String b : bank) {
            bankSet.add(b);
        }

        char[] strArr = new char[]{'A', 'C', 'G', 'T'};

        int cnt = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        visited.add(start);

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                String cur = q.poll();
                if(cur.equals(end)) {
                    return cnt;
                }

                char[] curArray = cur.toCharArray();
                for(int i = 0; i < curArray.length; ++i) {
                    char tmp = curArray[i];
                    for(char c : strArr) {
                        curArray[i] = c;
                        String newStr = new String(curArray);
                        if(!visited.contains(newStr) && bankSet.contains(newStr)) {
                            q.offer(newStr);
                            visited.add(newStr);
                        }
                    }
                    curArray[i] = tmp;
                }
            }
            ++cnt;
        }

        return -1;
    }
}
