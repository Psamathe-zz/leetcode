package com.example.leetcode.challenge.test2021.may.week3;


/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 *
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *
 *
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 * Note:
 *
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 */
public class BinaryTreeCameras {
    public static void main(String[] args) {

    }

    /**
     * https://zhuanlan.zhihu.com/p/65035022
     * 状态0：节点没有被监控
     * 状态1：节点被监控了但是节点上并没有摄像头
     * 状态2：节点被监控了同时节点上安装了摄像头
     */
    int res;
    public int minCameraCover(TreeNode root) {
        res = 0;
        if(helper(root) == 0)
            res += 1;
        return res;
    }

    public int helper(TreeNode cur){
        if(cur == null)
            return 1;
        int left = helper(cur.left);
        int right = helper(cur.right);
        if (left == 0 || right == 0) {
            res += 1;
            return 2;
        } else if(left == 1 && right == 1)
            return 0;
        else
            return 1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
}
