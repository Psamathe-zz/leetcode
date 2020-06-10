package com.example.leetcode.hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * We run a preorder depth first search on the root of a binary tree.
 *
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)
 *
 * If a node has only one child, that child is guaranteed to be the left child.
 *
 * Given the output S of this traversal, recover the tree and return its root.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 * Example 2:
 *
 *
 *
 * Input: "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 *
 *
 * Example 3:
 *
 *
 *
 * Input: "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 *
 *
 * Note:
 *
 * The number of nodes in the original tree is between 1 and 1000.
 * Each node will have a value between 1 and 10^9.
 */
public class RecoverTreeFromPreorderTraversal {
    public static void main(String[] args) {
        String S = "1-2--3--4-5--6--7";
        RecoverTreeFromPreorderTraversal recoverTreeFromPreorderTraversal = new RecoverTreeFromPreorderTraversal();
        TreeNode result = recoverTreeFromPreorderTraversal.recoverFromPreorderV2(S);
        result.toString();
    }


    public TreeNode recoverFromPreorder(String S) {
        TreeNode result = help(S,1);
        return result;
    }

    public TreeNode help(String S,int level) {
        int length = S.length();

        String regexRoot = "(\\d+)";
        Pattern patternRoot = Pattern.compile(regexRoot);
        Matcher matcherRoot = patternRoot.matcher(S);
        int rootStart = 0;
        int rootEnd = S.length();

        if (matcherRoot.find()) {
            rootStart = matcherRoot.start();
            rootEnd = matcherRoot.end();
        }

        TreeNode root = new TreeNode(Integer.valueOf(S.substring(rootStart,rootEnd)));
        int leftStart = - 1;
        int leftEnd = S.length();
        int rightStart = - 1;
        int rightEnd = S.length();

        String target = String.join("", Collections.nCopies(level, "-"));
        String regex = "(\\d+)" + target + "(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        if(length > 1) {

            Matcher matcherLeft = pattern.matcher(S);
            if (matcherLeft.find()) {
                System.out.print("Start index: " + matcherLeft.start());
                System.out.print(" End index: " + matcherLeft.end());
                System.out.println(" Found: " + matcherLeft.group());
                leftStart = rootEnd + 1;

                Matcher matcherRight = pattern.matcher(S.substring(leftStart));
                if (matcherRight.find()) {
                    System.out.print("Start index: " + matcherRight.start());
                    System.out.print(" End index: " + matcherRight.end());
                    System.out.println(" Found: " + matcherRight.group());
                    String temp = S.substring(leftStart).substring(matcherRight.start(),matcherRight.end());
                    int index = temp.indexOf(target);
                    leftEnd = matcherRight.start() + leftStart + index;
                    rightStart = leftEnd + level;
                }
            }

        }

        if(leftStart != - 1) {
            root.left = help(S.substring(leftStart, leftEnd), level + 1);
        }
        if(rightStart != -1) {
            root.right = help(S.substring(rightStart, rightEnd), level + 1);
        }
        return root;
    }



    private int ptr;
    public TreeNode recoverFromPreorderV1(String S) {
        ptr = 0;
        return dfs(S.toCharArray(), 0);
    }

    private TreeNode dfs(char[] text, int d) {
        int depth = 0;
        int tmp = this.ptr;
        while (ptr < text.length && text[tmp] == '-') {
            tmp++;
            depth++;
        }
        if (depth != d) {
            return null;
        }
        this.ptr = tmp;
        int digit = 0;
        while (ptr < text.length && Character.isDigit(text[ptr])) {
            digit *= 10;
            digit += text[ptr] - '0';
            ptr++;
        }
        TreeNode node = new TreeNode(digit);
        node.left = dfs(text, d + 1);
        node.right = dfs(text, d + 1);
        return node;
    }

    /**
     * less memory
     * @param S
     * @return
     */
    public TreeNode recoverFromPreorderV2(String S) {
        int level, val;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length();) {
            for (level = 0; S.charAt(i) == '-'; i++) {
                level++;
            }
            for (val = 0; i < S.length() && S.charAt(i) != '-'; i++) {
                val = val * 10 + (S.charAt(i) - '0');
            }
            while (stack.size() > level) {
                stack.pop();
            }
            TreeNode node = new TreeNode(val);
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.add(node);
        }
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.pop();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val;}

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
