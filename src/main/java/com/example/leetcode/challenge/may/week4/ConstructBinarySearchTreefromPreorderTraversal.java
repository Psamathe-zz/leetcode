package com.example.leetcode.challenge.may.week4;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.
 * left has a value < node.val, and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 *
 * It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.
 *
 * Example 1:
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    public static void main(String[] args) {
        int[] preorder = new int[]{8,5,1,7,10,12};
        ConstructBinarySearchTreefromPreorderTraversal constructBinarySearchTreefromPreorderTraversal = new ConstructBinarySearchTreefromPreorderTraversal();
        constructBinarySearchTreefromPreorderTraversal.bstFromPreorderV1(preorder);
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = help(preorder,0,preorder.length - 1);
        return root;
    }

    public TreeNode help(int[] preorder,int start,int end){
        if(start >= preorder.length)
            return null;
        TreeNode root = new TreeNode(preorder[start]);
        int leftIndex = -1;
        int rightIndex = -1;
        boolean findLeft = false;
        boolean findRight = false;
        for(int i = start + 1; i <= end; i++){
            if(preorder[i] < preorder[start] && ! findLeft){
                findLeft = true;
                leftIndex = i;
            }
            if(preorder[i] > preorder[start] && ! findRight){
                findRight = true;
                rightIndex = i;
            }
        }
        if(findLeft)
            root.left = help(preorder,leftIndex,rightIndex==-1?end:rightIndex-1);
        if(findRight)
            root.right = help(preorder,rightIndex,end);
        return root;
    }

    /**
     * less memory
     */
    int preIndex =0;
    public TreeNode bstFromPreorderV0(int[] preorder) {
        return constructBST(preorder,Integer.MAX_VALUE);
    }

    TreeNode constructBST(int[] preOrder,int val){
        if (preIndex==preOrder.length || val<preOrder[preIndex])
            return null;
        TreeNode root=new TreeNode(preOrder[preIndex]);
        preIndex++;
        root.left=constructBST(preOrder,root.val);
        root.right=constructBST(preOrder,val);
        return root;
    }

    /**
     * less memory
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorderV1(int[] preorder) {
        TreeNode root = null;
        for (int n : preorder) {
            root = insert(root, n);
        }
        return root;
    }

    private TreeNode insert(TreeNode t, int val) {
        if (t == null)
            return new TreeNode(val);
        if (val > t.val) {
            t.right = insert(t.right, val);
        } else {
            t.left = insert(t.left, val);
        }
        return t;
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
