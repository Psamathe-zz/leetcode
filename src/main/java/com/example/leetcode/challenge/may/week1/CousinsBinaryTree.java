package com.example.leetcode.challenge.may.week1;


import java.util.*;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 *
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 *
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 *
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * Example 3:
 *
 *
 *
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 */
public class CousinsBinaryTree {

    public static void main(String[] args) {
        char[] nodes = new char[]{'1','2','3','#','4','#','5'};
        int x = 4;
        int y = 5;
        CousinsBinaryTree cousinsBinaryTree = new CousinsBinaryTree();
        TreeNode root = cousinsBinaryTree.createTree(nodes);
        boolean result  = cousinsBinaryTree.isCousinsV2(root,x,y);
        System.out.println(result);
    }

    public boolean isCousins(TreeNode root, int x, int y) {

        int deepthX = deepth(root,x);
        int deepthY = deepth(root,y);
        if(deepthX != deepthY)
            return false;
        else{
            TreeNode parentX = getParent(root,x);
            TreeNode parentY = getParent(root,y);
            if(parentX.val == parentY.val)
                return false;
        }

        return true;
    }

    public int deepth(TreeNode root, int x){
        if(root == null)
            return 200;
        else if(root.val == x)
            return 1;
        else
            return Math.min(deepth(root.left,x),deepth(root.right,x)) + 1;
    }

    public TreeNode getParent(TreeNode root, int x){
        if(root == null)
            return null;
        else if((root.left != null && root.left.val == x) || (root.right != null && root.right.val == x))
            return root;
        else {
            return getParent(root.left,x) != null ? getParent(root.left,x) : getParent(root.right,x);
        }

    }


    /**
     * faster solution
     * @param root
     * @param x
     * @param y
     * @return
     *
     * 一层一层遍历
     */
    public boolean isCousinsV2(TreeNode root, int x, int y) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(true){
            Set<Integer> l =new HashSet<>();
            int nodeSize= queue.size();
            if(nodeSize==0)
                break;
            while(nodeSize>0){
                TreeNode node= queue.peek();
                l.add(node.val);
                queue.remove();
                if((node.right!=null&&node.left!=null)&&
                        ((node.right.val==x&&node.left.val==y)
                                ||(node.right.val==y&&node.left.val==x))){
                    return false;
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
                nodeSize--;
            }
            if(l.contains(x)&&l.contains(y)){
                return true;
            }
        }

        return false;
    }


    /**
     * less memory
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousinsV3(TreeNode root, int x, int y) {

        if (root == null) {
            return false;
        }

        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        map.put(root, null);

        while (!queue.isEmpty()) {

            TreeNode firstCousin = null;
            TreeNode secondCousin = null;

            int size = queue.size();

            while (size-- > 0) {

                TreeNode node = queue.poll();

                if (node.val == x) {
                    firstCousin = node;
                } else if (node.val == y) {
                    secondCousin = node;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                    map.put(node.left, node);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    map.put(node.right, node);
                }
            }

            if (firstCousin != null && secondCousin != null) {
                return map.get(firstCousin) != map.get(secondCousin);
            }

            if (firstCousin != null || secondCousin != null) {
                return false;
            }
        }

        return false;
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
