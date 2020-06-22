package com.example.leetcode.medium;

public class SmallestSubtree {
    public static void main(String[] args) {
        char[] Input = new char[]{'0','1','3','#','2'};
        SmallestSubtree smallestSubtree = new SmallestSubtree();
        TreeNode root = smallestSubtree.createTree(Input);
        TreeNode treeNode = smallestSubtree.subtreeWithAllDeepest(root);
        System.out.println(treeNode);
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(helpGetDeepth(root.left) == helpGetDeepth(root.right))
            return root;
        else if(helpGetDeepth(root.left) > helpGetDeepth(root.right) )
            return subtreeWithAllDeepest(root.left);
        else
            return subtreeWithAllDeepest(root.right);
    }

    public int helpGetDeepth(TreeNode root){
        if(root == null)
            return 0;
        else return Math.max(helpGetDeepth(root.left) + 1,helpGetDeepth(root.right) + 1);
    }

    /**
     * faster solution
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepestV2(TreeNode root) {
        if (root == null)
            return root;
        dfs(root, 0);
        return treeLCA;
    }
    int maxdepth = 0;
    TreeNode treeLCA = null;
    private int dfs(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        int leftdepth = dfs(root.left, depth + 1);
        int rightdepth = dfs(root.right, depth + 1);
        // only need to update treeLCA if left and right have same depth and
        // pitfall maxdepth <= leftdepth not maxdepth < leftdepth
        if (leftdepth == rightdepth && maxdepth <= leftdepth) {
            maxdepth = leftdepth;
            treeLCA = root;
        }
        return Math.max(leftdepth, rightdepth);
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
