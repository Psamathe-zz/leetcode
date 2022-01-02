package com.example.leetcode.challenge.test2021.december;

import com.example.leetcode.model.TreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 *
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/5275096.html
     * 这种方法的递归函数返回一个大小为2的一维数组 res，其中 res[0] 表示不包含当前节点值的最大值，res[1] 表示包含当前值的最大值
     * 那么在遍历某个节点时，
     * 首先对其左右子节点调用递归函数，分别得到包含与不包含左子节点值的最大值，和包含于不包含右子节点值的最大值，
     * 则当前节点的 res[0] 就是左子节点两种情况的较大值加上右子节点两种情况的较大值，res[1] 就是不包含左子节点值的最大值加上不包含右子节点值的最大值，和当前节点值之和，返回即可，参见代码如下：
     * @param root
     * @return
     */

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0],res[1]);
    }

    int[] dfs(TreeNode root) {
        if (null == root) {
            return new int[2];
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }
}
