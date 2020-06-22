package com.example.leetcode.medium;


import java.util.*;
import java.util.stream.Collectors;

public class DeleteNodesAndReturnForest {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int[] to_delete = new int[]{1,5};
        DeleteNodesAndReturnForest deleteNodesAndReturnForest = new DeleteNodesAndReturnForest();
        TreeNode root = deleteNodesAndReturnForest.createTree(nums);
        List<TreeNode> result = deleteNodesAndReturnForest.delNodes(root,to_delete);
        for(TreeNode node:result ){
            System.out.println(node);
        }
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        List<Integer> toDelete = Arrays.stream(to_delete).boxed().collect(Collectors.toList());
        if(!toDelete.contains(root.val))
            result.add(root);
        help(root,toDelete,result,false);

        return result;
    }

    public void help(TreeNode root, List<Integer> toDelete,List<TreeNode> result,boolean parentDeleted){

        if(root == null)
            return;
        else{
            if(toDelete.contains(root.val)){
                help(root.left,toDelete,result,true);
                root.left = null;
                help(root.right,toDelete,result,true);
                root.right = null;
            } else {
                if(parentDeleted == true)
                    result.add(root);
                help(root.left,toDelete,result,false);
                help(root.right,toDelete,result,false);
            }

            if(root.left != null && toDelete.contains(root.left.val)){
                root.left = null;
            }
            if(root.right != null && toDelete.contains(root.right.val)){
                root.right = null;
            }

        }
    }



    public TreeNode createTree (int[] treeArr) {
        TreeNode[] tree = new TreeNode[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {

            tree[i] = new TreeNode(treeArr[i]);
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

    List<TreeNode> res = new ArrayList<>();

    /**
     * faster solution
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodesV2(TreeNode root, int[] to_delete) {
        if(root == null){
            return null;
        }

        if(to_delete.length == 0){
            return Arrays.asList(root);
        }

        Set<Integer> delSet = new HashSet<>();
        for(int i : to_delete){
            delSet.add(i);
        }

        if(!delSet.contains(root.val)){
            res.add(root);
        }

        processNode(root, delSet);

        return res;
    }

    private TreeNode processNode(TreeNode root, Set<Integer> delSet){
        if(root == null){
            return null;
        }

        TreeNode newLeft = processNode(root.left, delSet);
        TreeNode newRt = processNode(root.right, delSet);

        // Process Node
        root.left = newLeft;
        root.right = newRt;
        if(delSet.contains(root.val)){
            if(root.left != null){
                res.add(root.left);
            }
            if(root.right != null){
                res.add(root.right);
            }

            return null;
        }


        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
