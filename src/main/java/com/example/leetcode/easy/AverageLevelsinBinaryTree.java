package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 */
public class AverageLevelsinBinaryTree {



    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        double sum = 0;
        int number = 1;
        int index = 0;
        int sub = 0;

        do{
            sum = 0;
            index = 0;
            sub = 0;
            while (index < number){
                TreeNode node = queue.poll();
                sum+=node.val;
                index++;
                if(node.left != null) {
                    queue.add(node.left);
                    sub++;
                }
                if(node.right != null) {
                    queue.add(node.right);
                    sub++;
                }
            }
            result.add(sum/number);
            number = sub;
        }while (sub!=0);

        return result;
    }


    public List<Double> averageOfLevelsV2(TreeNode root) {
        Queue<TreeNode> queue =new LinkedList<TreeNode>();
        List<Double> res = new ArrayList<Double>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            double sum=0;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                sum+=node.val;
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }

            }
            res.add(sum/size);
        }
        return res;
    }


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
