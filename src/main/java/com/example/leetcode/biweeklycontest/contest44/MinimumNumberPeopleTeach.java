package com.example.leetcode.biweeklycontest.contest44;


import java.util.*;

/**
 * On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.
 *
 * You are given an integer n, an array languages, and an array friendships where:
 *
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
 * You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.
 *
 * Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 *
 *
 * Example 1:
 *
 * Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 * Output: 1
 * Explanation: You can either teach user 1 the second language or user 2 the first language.
 * Example 2:
 *
 * Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
 * Output: 2
 * Explanation: Teach the third language to users 1 and 2, yielding two users to teach.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 500
 * languages.length == m
 * 1 <= m <= 500
 * 1 <= languages[i].length <= n
 * 1 <= languages[i][j] <= n
 * 1 <= u​​​​​​i < v​​​​​​i <= languages.length
 * 1 <= friendships.length <= 500
 * All tuples (u​​​​​i, v​​​​​​i) are unique
 * languages[i] contains only unique values
 */
public class MinimumNumberPeopleTeach {
    public static void main(String[] args) {
        int n = 3;
        int[][] languages = new int[][]{
                {2},
                {1,3},
                {1,2},
                {3}
        };
        int[][] friendships = new int[][]{
                {1,4},
                {1,2},
                {3,4},
                {2,3}
        };
        MinimumNumberPeopleTeach minimumNumberPeopleTeach = new MinimumNumberPeopleTeach();
        minimumNumberPeopleTeach.minimumTeachings(n,languages, friendships);
    }

    public int minimumTeachingsV0(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        int k = friendships.length;
        ArrayList<BitSet> sets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            sets.add(new BitSet(n));
            for (int l : languages[i]) {
                sets.get(i).set(l-1);
            }
        }
        ArrayList<int[]> badEdges = new ArrayList<>();
        for (int[] f : friendships) {
            BitSet overlap = new BitSet(n);
            overlap.or(sets.get(f[0]-1));
            overlap.and(sets.get(f[1]-1));
            if (overlap.cardinality() == 0) {
                badEdges.add(f);
            }
        }
        int best = m;
        for (int l = 1; l <= n; l++) {
            HashSet<Integer> learn = new HashSet<>();
            for (int[] f : badEdges) {
                BitSet set1 = sets.get(f[0]-1);
                BitSet set2 = sets.get(f[1]-1);
                if (!set1.get(l-1)) {
                    learn.add(f[0]);
                }
                if (!set2.get(l-1)) {
                    learn.add(f[1]);
                }
            }
            best = Math.min(best, learn.size());
        }
        return best;
    }
    /**
     *https://leetcode-cn.com/problems/minimum-number-of-people-to-teach/solution/javaban-mo-ni-fa-qiu-jie-by-zixu-wang-u740/
     * @param n
     * @param languages
     * @param friendships
     * @return
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int nums_people = languages.length;

        // 统计每个人会什么语言
        ArrayList<HashSet<Integer>> know_list = new ArrayList();
        // 顺便统计一下总共有哪些语言（因为不出现的语言不需要考虑，得全教一遍）
        HashSet<Integer> total_languages = new HashSet();
        know_list.add(new HashSet());
        for(int[] a:languages){
            HashSet<Integer> set = new HashSet();
            for(int b:a){
                set.add(b);
                total_languages.add(b);
            }
            know_list.add(set);
        }

        // 统计有哪些friendships不能沟通，放入list中
        ArrayList<int[]> unmatched = new ArrayList();
        for(int[] pair:friendships){
            block:{
                int x = pair[0], y = pair[1];
                // a_know---a会的语言，b_know---b会的语言
                HashSet<Integer> a_know = know_list.get(x), b_know = know_list.get(y);
                for(int a:a_know){
                    // 如果b和a有共同语言，直接退出
                    if(b_know.contains(a)){
                        break block;
                    }
                }
                // 双方无共同语言
                unmatched.add(new int[]{x, y});
            }
        }

        // 遍历每一种语言
        // 我们只遍历出现过的语言即可，不然一次没出现的得全教一遍
        int ret = 501;
        for(int i:total_languages){
            // 新学这门语言的用户
            HashSet<Integer> already_learn = new HashSet();

            int count = 0;
            for(int[] pair:unmatched){
                int x = pair[0], y = pair[1];

                // 如果x还不会这门语言，教给他
                if(!already_learn.contains(x) && !know_list.get(x).contains(i)){
                    already_learn.add(x);
                    count++;
                }
                // 如果y还不会这门语言，教给他
                if(!already_learn.contains(y) && !know_list.get(y).contains(i)){
                    already_learn.add(y);
                    count++;
                }
            }

            ret = Math.min(count, ret);
        }

        return ret;
    }
}
