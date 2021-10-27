package com.example.leetcode.challenge.test2021.august.week4;

import java.util.Stack;

/**
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
 *
 *
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 *
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
 *
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid.
 *
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 *
 *
 *
 * Example 1:
 *
 * Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 * Example 2:
 *
 * Input: preorder = "1,#"
 * Output: false
 * Example 3:
 *
 * Input: preorder = "9,#,#,1"
 * Output: false
 * "1,#,#,#,#"
 * "#,7,6,9,#,#,#"
 */
public class VerifyPreorderSerialization {
    public static void main(String[] args) {
        VerifyPreorderSerialization verifyPreorderSerialization = new VerifyPreorderSerialization();
        boolean res = verifyPreorderSerialization.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        System.out.println(res);
    }

    public boolean isValidSerialization(String preorder) {
        int count = 0;
        String[] split = preorder.split(",");
        String str;
        for (int i = 0; i < split.length - 1; i++) {
            str = split[i];
            if(str.equals("#")){
                if(count == 0)
                    return false;
                count--;
            } else {
                count++;
            }
        }

        return count == 0 && split[split.length - 1].equals("#");
    }

    /**
     * faster solution
     * @param preorder
     * @return
     */
    public boolean isValidSerializationV1(String preorder) {
        String[] array = preorder.split(",");
        int degree = 1;
        for(String val: array) {
            degree--;
            if(degree < 0)
                return false;
            if(!val.equals("#")) degree += 2;
        }
        return degree == 0;
    }
}
