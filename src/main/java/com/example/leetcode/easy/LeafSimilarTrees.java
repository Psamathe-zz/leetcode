package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {

    public static void main(String[] args) {
        char[] tree1 = new char[]{'1','2','3','4','5','6','7'};
        char[] tree2 = new char[]{'1','2','3','4','5','6','7'};
        LeafSimilarTrees leafSimilarTrees = new LeafSimilarTrees();
        TreeNode root1 = leafSimilarTrees.createTree(tree1);
        TreeNode root2 = leafSimilarTrees.createTree(tree2);
        boolean result = leafSimilarTrees.leafSimilar(root1,root2);
        System.out.println(result);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> tree1Leaf = new ArrayList<>();
        List<Integer> tree2Leaf = new ArrayList<>();

        findLeaf(root1,tree1Leaf);
        findLeaf(root2,tree2Leaf);
        if(tree1Leaf.size() != tree2Leaf.size())
            return false;
        else{
            for(int i = 0;i<tree1Leaf.size();i++){
                if(tree1Leaf.get(i) != tree2Leaf.get(i))
                    return false;
            }
        }
        return true;
    }

    public void findLeaf(TreeNode root,List<Integer> list){
        if(root.left == null && root.right == null)
            list.add(root.val);
        else if(root.left == null)
            findLeaf(root.right,list);
        else if(root.right == null)
            findLeaf(root.left,list);
        else {
            findLeaf(root.left,list);
            findLeaf(root.right, list);
        }
    }


    /**
     * better solution
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilarV2(TreeNode root1, TreeNode root2) {


        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        fill(root1, list1);
        fill(root2, list2);


        if (!list1.equals(list2)) {
            return false;
        }

        return true;
    }

    void fill(TreeNode root, ArrayList<Integer> list) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            list.add(root.val);
        }

        fill(root.left, list);
        fill(root.right, list);


    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        TreeNode(int x) { val = x; }
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
