package com.example.leetcode.challenge.test2021.octobre;

import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 */
public class PathSumIII {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1};
        TreeNode root = TreeNode.convert(array);

        PathSumIII pathSumIII = new PathSumIII();
        pathSumIII.pathSum(root, 8);
    }

    int res;
    public int pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        res = 0;
        helper(list,root,sum);
        return res;
    }

    public void helper(List<Integer> list , TreeNode node,int sum){
        if(node == null)
            return;
        list = list.stream().map(e->e + node.val).collect(Collectors.toList());
        list.add(node.val);
        for (Integer val : list){
            if(val == sum)
                res++;
        }
        helper(list,node.left,sum);
        helper(list,node.right,sum);
    }

    /**
     * faster solution
     */
    public int cnt = 0;
    public int pathSumV1(TreeNode root, int targetSum) {
        helper1(root,targetSum,new ArrayList<>());
        return cnt;
    }
    public void helper1(TreeNode node,int targetSum,List<Integer> list) {
        if(node == null)
            return;
        list.add(node.val);
        helper1(node.left,targetSum,list);
        helper1(node.right,targetSum,list);
        int sum = 0;
        for(int i=list.size() - 1;i>=0;i--){
            sum+=list.get(i);
            if(sum == targetSum){
                cnt++;
            }
        }
        list.remove(list.size() - 1);
    }
}
