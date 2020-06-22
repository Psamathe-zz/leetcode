package com.example.leetcode.easy;


import java.util.*;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */
public class SymmetricTree {

    public static void main(String[] args) {
        SymmetricTree symmetricTree = new SymmetricTree();
        char[] treeArr1 = new char[]{'1','2','2','3','4','4','3'};
        char[] treeArr2 = new char[]{'1','2','2','#','4','#','4'};

        TreeNode tree1 = symmetricTree.createTree(treeArr1);
        TreeNode tree2 = symmetricTree.createTree(treeArr2);


        System.out.println(symmetricTree.isSymmetric(tree1) + "----true");
        System.out.println(symmetricTree.isSymmetricByIterator(tree1.left, tree1.right) + "----true");


        System.out.println(symmetricTree.isSymmetric(tree2) + "----false");
        System.out.println(symmetricTree.isSymmetricByIterator(tree2.left, tree2.right) + "----false");
    }
    public boolean isSymmetric(TreeNode root) {

        if(root == null)
            return false;
        return isSymmetric(root.left,root.right);

    }

    public boolean isSymmetric (TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * less memory
     * @param root
     * @return
     */

    public boolean isSymmetricV2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return q.isEmpty();
    }

    public boolean isSymmetricByIterator (TreeNode left, TreeNode right) {
        Stack<TreeNode> leftStack = new Stack<TreeNode>();
        Stack<TreeNode> rightStack = new Stack<TreeNode>();
        leftStack.push(left);
        rightStack.push(right);
        while (leftStack.size() > 0 && rightStack.size() > 0) {
            TreeNode leftNode = leftStack.pop();
            TreeNode rightNode = rightStack.pop();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }
            leftStack.push(leftNode.left);
            leftStack.push(leftNode.right);
            rightStack.push(rightNode.right);
            rightStack.push(rightNode.left);
        }
        return true;
    }
    public List<Integer> help(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();


        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                list.add(node.val);
                pNode = node.right;
            }
        }

        return list;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode createTree (char[] treeArr) {
        TreeNode[] tree = new TreeNode[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {
            if (treeArr[i] == '#') {
                tree[i] = null;
                continue;
            }
            tree[i] = new TreeNode(treeArr[i]-'0');
        }
        int pos = 0;
        for (int i = 0; i < treeArr.length && pos < treeArr.length-1; i++) {
            if (tree[i] != null) {
                tree[i].left = tree[++pos];
                if (pos < treeArr.length-1) {
                    tree[i].right = tree[++pos];
                }
            }
        }
        return tree[0];
    }

}
