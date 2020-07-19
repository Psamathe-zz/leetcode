package com.example.leetcode.weeklycontest.test198;

import java.util.*;

/**
 * Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:
 *
 * The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
 * A substring that contains a certain character c must also contain all occurrences of c.
 * Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.
 *
 * Notice that you can return the substrings in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the conditions:
 * [
 *   "adefaddaccc"
 *   "adefadda",
 *   "ef",
 *   "e",
 *   "f",
 *   "ccc",
 * ]
 * If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
 * Example 2:
 *
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 */
public class MaximumNumberNonOverlappingSubstrings {
    public static void main(String[] args) {
        String s = "adefaddaccc";
        MaximumNumberNonOverlappingSubstrings maximumNumberNonOverlappingSubstrings = new MaximumNumberNonOverlappingSubstrings();
        List<String> res = maximumNumberNonOverlappingSubstrings.maxNumOfSubstrings(s);
        System.out.println(res);
    }

    int m = 26;
    int n;
    boolean[] seen;
    int[] newFirst;
    int[] newLast;
    String string;
    public List<String> maxNumOfSubstrings(String s) {
        n = s.length();
        string = s;
        seen = new boolean[m];
        newFirst = new int[m];
        newLast = new int[m];
        Arrays.fill(newFirst,n);
        Arrays.fill(newLast,-1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (!seen[c])
                newFirst[c] = i;
            newLast[c] = i;
            seen[c] = true;
        }

        update();
        int[] p = new int[m];
        for (int c = 0; c < m; c++)
            p[c] = c;
        p = Arrays.stream(p).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (newLast[x] != newLast[y])
                    return newLast[x] - newLast[y];

                return newLast[x] - newFirst[x] - (newLast[y] - newFirst[y]);
            }
        }).mapToInt(e->e).toArray();

        int st = 0;
        while (st < m && !seen[p[st]])
            st++;

        int lastPos = -1;
        List<String> ans = new ArrayList<>();
        for (; st < m; st++) {
            int c = p[st];
            if (newFirst[c] > lastPos) {
                ans.add(s.substring(newFirst[c], newLast[c] + 1));
                lastPos = newFirst[c];
            }
        }

        return ans;
    }
    void update() {
        int[] oldFirst = new int[m];
        Arrays.fill(oldFirst,n);
        int[] oldLast = new int[m];
        Arrays.fill(oldLast,-1);
        List<Set<Integer>> dep = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            dep.add(new HashSet<>());
        }
        for (int c = 0; c < m; c++) {
            if (!seen[c])
                continue;
            for (int i = newFirst[c]; i <= newLast[c]; i++)
                if (c != string.charAt(i) - 'a')
                    dep.get(c).add(string.charAt(i) - 'a');

            oldFirst[c] = newFirst[c];
            oldLast[c] = newLast[c];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int c = 0; c < m; c++)
            if (seen[c])
                queue.offer(c);

        while (!queue.isEmpty()) {
            int c = queue.poll();

            for (int d : dep.get(c)) {
                newFirst[c] = Math.min(newFirst[c], oldFirst[d]);
                newLast[c] = Math.max(newLast[c], oldLast[d]);
            }

            for (int i = newFirst[c]; i < oldFirst[c]; i++)
                if (c != string.charAt(i) - 'a')
                    dep.get(c).add(string.charAt(i) - 'a');

            for (int i = oldLast[c]; i < newLast[c]; i++)
                if (c != string.charAt(i) - 'a')
                    dep.get(c).add(string.charAt(i) - 'a');

            if (newFirst[c] < oldFirst[c] || newLast[c] > oldLast[c]) {
                oldFirst[c] = newFirst[c];
                oldLast[c] = newLast[c];
                queue.offer(c);
            }
        }
    }
}
