package com.example.leetcode.challenge.June.week3;

import java.util.*;

/**
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
 *
 * Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
 *
 *
 *
 * Example 1:
 *
 * Input: "banana"
 * Output: "ana"
 * Example 2:
 *
 * Input: "abcd"
 * Output: ""
 *
 *
 * Note:
 *
 * 2 <= S.length <= 10^5
 * S consists of lowercase English letters.
 *
 * https://leetcode.com/submissions/detail/355716796/?from=/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3365/
 */
public class LongestDuplicateSubstring {
    public static void main(String[] args) {
        String S = "banana";
        LongestDuplicateSubstring longestDuplicateSubstring = new LongestDuplicateSubstring();
        String result = longestDuplicateSubstring.longestDupSubstring(S);
        System.out.println(result);
    }


    int mod = 999983;

    boolean cmp(String s, List<Integer> c, int x, int len) {
        for (int st : c) {
            boolean flag = true;
            for (int i = st, j = x; i < st + len; i++, j++)
                if (s.charAt(i) != s.charAt(j)) {
                    flag = false;
                    break;
                }
            if (flag)
                return true;
        }
        return false;
    }

    String check(String s, int len, int power) {
        Map<Integer, List<Integer>> listMap = new HashMap<>();
        int n = s.length();

        int hash = 0;
        for (int i = 0; i < len; i++)
            hash = (hash * 27 + s.charAt(i) - 'a' + 1) % mod;

        listMap.put(hash,new ArrayList<>());
        listMap.get(hash).add(0);

        for (int i = len; i < n; i++) {
            hash = (hash - power * (s.charAt(i - len) - 'a' + 1) % mod + mod) % mod;
            hash = (hash * 27 + s.charAt(i) - 'a' + 1) % mod;

            if (listMap.containsKey(hash) && cmp(s, listMap.get(hash), i - len + 1, len))
                return s.substring(i - len + 1, i + 1);
            if(listMap.containsKey(hash))
                listMap.get(hash).add(i - len + 1);
            else {
                listMap.put(hash,new ArrayList<>());
                listMap.get(hash).add(i - len + 1);
            }
        }

        return "";
    }

    String longestDupSubstring(String S) {
        int n = S.length();
        int l = 0, r = n - 1;

        int[] power = new int[n];
        Arrays.fill(power, 1);
        for (int i = 1; i < n; i++)
            power[i] = power[i - 1] * 27 % mod;

        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(S, mid + 1, power[mid]) == "") {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == 0)
            return "";
        return check(S, l, power[l - 1]);
    }


    /**
     *
     */
    Long MOD;
    public String longestDupSubstringV1(String S) {
        int N = S.length();
        int max = 0;
        String ans = "";
        MOD = 1000000007L;
        int l = 0, r = N - 1;
        //binary search

        while (l <= r) {
            int mid = l + (r - l) / 2;
            boolean breakout = false;
            Map<Long, List<Integer>> map = new HashMap<>(); // the List<Integer> store a list of indexes which have the same hashCode
            long prev = 0;
            long pow26 = calcPower26(mid);
            for (int i = 0; i + mid - 1 < N; i++) {
                long code = calc(S, i, mid, prev, pow26); // calculate hashcode
                if (map.containsKey(code)) {
                    for (int m : map.get(code)) { // iterate thru the list and verify the match to deal with hash collision.
                        if (S.substring(i, i + mid).equals(S.substring(m, m + mid))) {
                            breakout = true;
                            if (mid > max) {
                                max = mid;
                                ans = S.substring(i, i + mid);
                            }
                            break;
                        }
                    }
                }
                if (breakout) break;
                map.putIfAbsent(code, new ArrayList<>());
                map.get(code).add(i);
                prev = code; //log prev for next hashcode calculation.
            }
            if (breakout) l = mid + 1;
            else r = mid - 1;
        }
        return ans;
    }
    private long calc(String s, int start, int len, long prev, long pow26) {
        long ans = 0L;
        if (start == 0) { // first time calcuation
            for (int i = 0; i < len; i++) {
                ans *= 26L;
                ans += (long) (s.charAt(start + i) - 'a');
                ans %= MOD;
            }
        } else { // defer from previous result.
            ans = prev * 26L;
            ans += (long) (s.charAt(start + len - 1) - 'a');
            ans -= (s.charAt(start - 1) - 'a') * pow26;    // 在这里写了一个bug, 我直接用ans % pow26了。
        }
        ans %= MOD;
        if (ans < 0L) ans += MOD;
        return ans;
    }
    private long calcPower26 (int n) {
        if (n == 0) return 1L;
        if (n == 1) return 26L;
        long half = calcPower26( n / 2);
        long ans = (half * half) % MOD;
        ans *= calcPower26(n - (n / 2) * 2);
        return ans % MOD;
    }


    /**
     * faster solution
     */
    private String S;

    // T = O(N*K), S = O(N), where N is length of S and K avg depth of trie.
    public String longestDupSubstringV2(String S) {
        this.S = S;
        int maxLo = 0, maxLength = 0;
        TrieNode root = new TrieNode(0, 0);
        for (int i = 1; i + maxLength < S.length(); i++) {
            int len = addNew(root, i);
            if (len > maxLength) {
                maxLength = len;
                maxLo = i;
            }
        }
        return S.substring(maxLo, maxLo + maxLength);
    }

    private boolean isLeaf(TrieNode node) {
        return node.next == null;
    }

    private int getIndex(int i, int depth) {
        return S.charAt(i + depth) - 'a';
    }

    private int addNew(TrieNode node, int i) {
        int depth = node.depth;
        if (i + depth == S.length())
            return depth;
        if (isLeaf(node)) {
            node.next = new TrieNode[26];
            node.next[getIndex(node.i, node.depth)] = new TrieNode(node.i, depth + 1);
        }
        int c = getIndex(i, node.depth);
        TrieNode x = node.next[c];
        if (x == null) {
            node.next[c] = new TrieNode(i, depth + 1);
            return depth;
        }
        return addNew(x, i);
    }

    private static class TrieNode {
        private TrieNode[] next;
        private int i;
        private int depth;

        public TrieNode(int i, int depth) {
            this.i = i;
            this.depth = depth;
        }
    }

}
