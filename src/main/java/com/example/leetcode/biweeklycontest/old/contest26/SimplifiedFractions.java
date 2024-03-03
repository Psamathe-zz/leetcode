package com.example.leetcode.biweeklycontest.old.contest26;

import java.util.*;

/**
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is less-than-or-equal-to n.
 * The fractions can be in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: ["1/2"]
 * Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
 * Example 2:
 *
 * Input: n = 3
 * Output: ["1/2","1/3","2/3"]
 * Example 3:
 *
 * Input: n = 4
 * Output: ["1/2","1/3","1/4","2/3","3/4"]
 * Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
 * Example 4:
 *
 * Input: n = 1
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 */
public class SimplifiedFractions {
    public static void main(String[] args) {
        int n = 4;
        SimplifiedFractions simplifiedFractions = new SimplifiedFractions();
        List<String> result = simplifiedFractions.simplifiedFractions(n);
        System.out.println(result);
    }

    public List<String> simplifiedFractions(int n) {
        List<Match> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            for (int j = 1; j < i; j++) {
                Match newMatch = new Match(j,i);
                if(!list.contains(newMatch))
                    list.add(newMatch);
            }
        }
        for(Match match : list){
            result.add(match.up + "/" + match.sub);
        }
        return result;
    }

    public class Match{
        int up;
        int sub;

        public Match(int up, int sub) {
            this.up = up;
            this.sub = sub;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Match match = (Match) o;
            return sub * match.up  == up * match.sub;
        }

        @Override
        public int hashCode() {
            return Objects.hash(up, sub);
        }
    }


    /**
     * faster solution
     */
    private static final Map<Integer, List<String>> cache = new HashMap<>();

    public List<String> simplifiedFractionsV1(int n) {
        if (n == 1) {
            return new ArrayList<>();
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        List<String> result = new ArrayList<>(simplifiedFractions(n - 1));
        result.add("1/" + n);
        for (int i = 2; i < n; i++) {
            if (gcd(n, i) == 1) {
                result.add(i + "/" + n);
            }
        }
        cache.put(n, result);
        return result;
    }

    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
