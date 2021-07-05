package com.example.leetcode.weeklycontest.test247;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are an ant tasked with adding n new rooms numbered 0 to n-1 to your colony. You are given the expansion plan as a 0-indexed integer array of length n, prevRoom, where prevRoom[i] indicates that you must build room prevRoom[i] before building room i, and these two rooms must be connected directly. Room 0 is already built, so prevRoom[0] = -1. The expansion plan is given such that once all the rooms are built, every room will be reachable from room 0.
 *
 * You can only build one room at a time, and you can travel freely between rooms you have already built only if they are connected. You can choose to build any room as long as its previous room is already built.
 *
 * Return the number of different orders you can build all the rooms in. Since the answer may be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: prevRoom = [-1,0,1]
 * Output: 1
 * Explanation: There is only one way to build the additional rooms: 0 → 1 → 2
 * Example 2:
 *
 *
 * Input: prevRoom = [-1,0,0,1,2]
 * Output: 6
 * Explanation:
 * The 6 ways are:
 * 0 → 1 → 3 → 2 → 4
 * 0 → 2 → 4 → 1 → 3
 * 0 → 1 → 2 → 3 → 4
 * 0 → 1 → 2 → 4 → 3
 * 0 → 2 → 1 → 3 → 4
 * 0 → 2 → 1 → 4 → 3
 *
 *
 * Constraints:
 *
 * n == prevRoom.length
 * 2 <= n <= 105
 * prevRoom[0] == -1
 * 0 <= prevRoom[i] < n for all 1 <= i < n
 * Every room is reachable from room 0 once all the rooms are built.
 */
public class CountWaysBuildRooms {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/count-ways-to-build-rooms-in-an-ant-colony/solution/javazu-he-shu-dfs-by-thedesalizes-orfn/
     */
    Map<Integer, List<Integer>> sub = new HashMap<>();
    int mod = (int) 1e9 + 7;


    public int waysToBuildRooms(int[] prevRoom) {
        initializeFactorial((int) 1e5 + 1, mod);
        for (int i = 0; i < prevRoom.length; i++) {
            sub.put(i, new ArrayList<>());
        }

        for (int i = 1; i < prevRoom.length; i++) {
            sub.get(prevRoom[i]).add(i);
        }

        return (int) find(0)[1];
    }

    private long[] find(int i) {
        if (sub.get(i).size() == 0) {
            return new long[]{1, 1};
        }
        int num = 1;
        long ans = 1;
        List<Integer> ss = new ArrayList<>();
        for (int s : sub.get(i)) {
            long[] f = find(s);
            num += f[0];
            ans *= f[1];
            ans %= mod;
            ss.add((int) f[0]);
        }
        int k = num - 1;
        for (int s : ss) {
            ans *= binomialCoefficient(k, s, mod);
            ans %= mod;
            k -= s;
        }
        return new long[]{num, ans};
    }

    static long[] fac;
    static long[] inv;

    static void initializeFactorial(int n, int mod) {
        fac = new long[n + 1];
        inv = new long[n + 1];
        long[] v = new long[fac.length];
        fac[0] = inv[0] = 1;
        for (int i = 1; i < fac.length; i++) {
            v[i] = i == 1 ? 1 : v[i - mod % i] * (mod / i + 1);
            v[i] %= mod;
            fac[i] = fac[i - 1] * i;
            fac[i] %= mod;
            inv[i] = inv[i - 1] * v[i];
            inv[i] %= mod;
        }
    }

    static int binomialCoefficient(int n, int k, int m) {
        long res = fac[n] * inv[k];
        res %= m;
        res *= inv[n - k];
        return (int) (res % m);
    }

}
