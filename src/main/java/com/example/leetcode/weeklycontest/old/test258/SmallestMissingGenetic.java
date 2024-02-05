package com.example.leetcode.weeklycontest.old.test258;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a family tree rooted at 0 consisting of n nodes numbered 0 to n - 1. You are given a 0-indexed integer array parents, where parents[i] is the parent for node i. Since node 0 is the root, parents[0] == -1.
 *
 * There are 105 genetic values, each represented by an integer in the inclusive range [1, 105]. You are given a 0-indexed integer array nums, where nums[i] is a distinct genetic value for node i.
 *
 * Return an array ans of length n where ans[i] is the smallest genetic value that is missing from the subtree rooted at node i.
 *
 * The subtree rooted at a node x contains node x and all of its descendant nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: parents = [-1,0,0,2], nums = [1,2,3,4]
 * Output: [5,1,1,1]
 * Explanation: The answer for each subtree is calculated as follows:
 * - 0: The subtree contains nodes [0,1,2,3] with values [1,2,3,4]. 5 is the smallest missing value.
 * - 1: The subtree contains only node 1 with value 2. 1 is the smallest missing value.
 * - 2: The subtree contains nodes [2,3] with values [3,4]. 1 is the smallest missing value.
 * - 3: The subtree contains only node 3 with value 4. 1 is the smallest missing value.
 * Example 2:
 *
 *
 * Input: parents = [-1,0,1,0,3,3], nums = [5,4,6,2,1,3]
 * Output: [7,1,1,4,2,1]
 * Explanation: The answer for each subtree is calculated as follows:
 * - 0: The subtree contains nodes [0,1,2,3,4,5] with values [5,4,6,2,1,3]. 7 is the smallest missing value.
 * - 1: The subtree contains nodes [1,2] with values [4,6]. 1 is the smallest missing value.
 * - 2: The subtree contains only node 2 with value 6. 1 is the smallest missing value.
 * - 3: The subtree contains nodes [3,4,5] with values [2,1,3]. 4 is the smallest missing value.
 * - 4: The subtree contains only node 4 with value 1. 2 is the smallest missing value.
 * - 5: The subtree contains only node 5 with value 3. 1 is the smallest missing value.
 * Example 3:
 *
 * Input: parents = [-1,2,3,0,2,4,1], nums = [2,3,4,5,6,7,8]
 * Output: [1,1,1,1,1,1,1]
 * Explanation: The value 1 is missing from all the subtrees.
 */
public class SmallestMissingGenetic {
    public static void main(String[] args) {

    }

    int n;
    int[] parent;
    int[] num;
    boolean[] exist;
    int[] ans;
    int p = 1;
    Map<Integer, List<Integer>> edges;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        n = nums.length;
        parent = parents;
        num = nums;
        exist = new boolean[n + 2];
        ans = new int[n];
        edges = new HashMap<>();
        for (int i = 0; i < n; i++) {
            edges.putIfAbsent(parents[i], new ArrayList<>());
            edges.get(parents[i]).add(i);
        }
        // 标记完后，计算出不含1的子树的答案都是1，此时含1的子树答案还是0
        dfsAbout1(0);
        // 计算含1的子树的答案是多少
        dfsAns(0);
        return ans;
    }
    boolean dfsAbout1(int root) {
        List<Integer> children = edges.get(root);
        boolean res = false;
        if (children != null) {
            for (int child : children) {
                res |= dfsAbout1(child);
            }
        }
        if (num[root] == 1) res = true;
        if (!res) {
            ans[root] = 1;
        }
        return res;
    }
    void dfsAns(int root) {
        List<Integer> children = edges.get(root);
        if (children != null) {
            // 先递归处理含1的子树
            for (int child : children) {
                if (ans[child] != 1) {
                    dfsAns(child);
                }
            }
            // 处理不含1的子树，记录当前子树内含有哪些整数
            for (int child : children) {
                if (ans[child] == 1) {
                    dfsAdd(child);
                }
            }
        }
        // 记录当前子树根节点的整数
        exist[num[root]] = true;
        // 查找当前缺失的第一个整数
        while (exist[p]) {
            p++;
        }
        ans[root] = p;
    }
    void dfsAdd(int root) {
        exist[num[root]] = true;
        List<Integer> children = edges.get(root);
        if (children != null) {
            for (int child : children) {
                dfsAdd(child);
            }
        }
    }

}
