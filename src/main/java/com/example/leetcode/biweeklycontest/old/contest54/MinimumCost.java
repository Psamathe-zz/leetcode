package com.example.leetcode.biweeklycontest.old.contest54;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a valid boolean expression as a string expression consisting of the characters '1','0','&' (bitwise AND operator),'|' (bitwise OR operator),'(', and ')'.
 *
 * For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))", and "1|(0&(1))" are valid expressions.
 * Return the minimum cost to change the final value of the expression.
 *
 * For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations so that the new expression evaluates to 0.
 * The cost of changing the final value of an expression is the number of operations performed on the expression. The types of operations are described as follows:
 *
 * Turn a '1' into a '0'.
 * Turn a '0' into a '1'.
 * Turn a '&' into a '|'.
 * Turn a '|' into a '&'.
 * Note: '&' does not take precedence over '|' in the order of calculation. Evaluate parentheses first, then in left-to-right order.
 *
 *
 *
 * Example 1:
 *
 * Input: expression = "1&(0|1)"
 * Output: 1
 * Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a '&' using 1 operation.
 * The new expression evaluates to 0.
 * Example 2:
 *
 * Input: expression = "(0&0)&(0&0&0)"
 * Output: 3
 * Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3 operations.
 * The new expression evaluates to 1.
 * Example 3:
 *
 * Input: expression = "(0|(1|0&1))"
 * Output: 1
 * Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
 * The new expression evaluates to 0.
 */
public class MinimumCost {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-cost-to-change-the-final-value-of-expression/solution/fan-zhuan-biao-da-shi-zhi-de-zui-shao-ca-s9ln/
     * @param expression
     * @return
     */
    public int minOperationsToFlip(String expression) {
        char[] exp = expression.toCharArray();//转为char[]简化代码
        Deque<Integer> num = new LinkedList<>();//表达式结果栈
        Deque<Integer> op = new LinkedList<>();//反转操作次数栈，与表达式结果栈中的元素一一对应
        Deque<Character> sign = new LinkedList<>();//符号栈
        for (char e : exp) {
            if (e == '(' || e == '&' || e == '|') {//除')'以外的符号直接入栈
                sign.offerFirst(e);
                continue;
            } else if (e == ')') {//遇到右括号，弹出上一左括号，触发后续计算
                sign.pollFirst();
            } else {//遇到数字，入栈
                num.offerFirst((int)(e - '0'));//数值
                op.offerFirst(1);//任意单个数值反转操作次数为1
            }
            //计算当前可处理的表达式部分
            if (num.size() > 1 && sign.peekFirst() != '(') {//栈中存在超过2个数字，且未被括号分隔，可处理
                int[] result = minOp(num.pollFirst(), num.pollFirst(), op.pollFirst(), op.pollFirst(), sign.pollFirst());//计算当前表达式的值和最小反转操作次数
                num.offerFirst(result[0]);
                op.offerFirst(result[1]);
            }
        }
        return op.peekFirst();//反转操作次数栈此时只剩1个元素，就是整个表达式反转的最小操作次数
    }

    //针对计算当前表达式中的2个元素，计算值和最小反转操作次数
    public int[] minOp(int num1, int num2, int op1, int op2, char sign) {
        if (sign == '&') {
            if (num1 == 1 && num2 == 1)//1&1,将其中一个1反转为0
                return new int[]{1, Math.min(op1, op2)};
            else if (num1 == 0 && num2 == 0)//0&0,将其中一个0反转为1,并将&反转为|
                return new int[]{0, Math.min(op1, op2) + 1};
            else//1&0,将&反转为|
                return new int[]{0, 1};
        } else {
            if (num1 == 0 && num2 == 0)//0|0，将其中一个0反转为1
                return new int[]{0, Math.min(op1, op2)};
            else if (num1 == 1 && num2 == 1)//1|1,将其中一个1反转为0，并将|反转为&
                return new int[]{1, Math.min(op1, op2) + 1};
            else//1|0,将|反转为&
                return new int[]{1, 1};
        }
    }
}
