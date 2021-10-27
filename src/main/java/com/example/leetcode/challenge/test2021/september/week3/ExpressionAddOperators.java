package com.example.leetcode.challenge.test2021.september.week3;


import java.util.*;

/**
 * Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 */
public class ExpressionAddOperators {

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        expressionAddOperators.addOperators("12412", -45);
    }

    /**
     * https://leetcode-cn.com/problems/expression-add-operators/solution/java-di-gui-jie-fa-by-don-vito-corleone-2gft/
     * @param num
     * @param target
     * @return
     */
    List<String> res;
    String num;
    int target;
    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        this.num = num;
        this.target = target;
        helper("", 0, 0, 0);
        return res;
    }

    void helper(String cur, int start, long sum, long mul) {
        if (sum == target && start == num.length()) {
            res.add(cur);
            return;
        }

        for (int i = start; i < num.length(); i ++) {
            if (num.charAt(start) == '0' && i != start) break;
            String s = num.substring(start, i + 1);
            long val = Long.valueOf(s);
            if (start == 0) {
                helper(cur + s, i + 1, sum + val, val);
            } else {
                helper(cur + "+" + s, i + 1, sum + val, val);
                helper(cur + '-' + s, i + 1, sum - val, -val);
                helper(cur + "*" + s, i + 1, sum - mul + val * mul, val * mul);
            }

        }
    }


    /**
     * faster solution
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperatorsV1(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num.length() < 1) {
            return res;
        }
        if (target == Integer.MIN_VALUE) return res;
        StringBuilder cur = new StringBuilder();
        cur.append(num.charAt(0));
        dfs(num, 1, num.charAt(0) - '0', 1, target, cur, res);
        return res;
    }
    private void dfs(String num, int idx, int prevNum, int prevMulti, int remainSum, StringBuilder cur, List<String> res) {
        if (idx == num.length()) {
            remainSum -= prevNum * prevMulti;
            if (remainSum == 0) {
                res.add(cur.toString());
            }
            return;
        }
        int next = num.charAt(idx) - '0';
        int size = cur.length();
        // first 0s to be processed
        // case 1, prev + num[idx]
        if (prevNum != 0) {
            cur.append(next);
            int x = prevNum > 0 ? next : (-next);
            dfs(num, idx + 1, prevNum * 10 + x, prevMulti, remainSum, cur, res);
            cur.setLength(size);
        }
        // case 2, *
        cur.append('*');
        cur.append(next);
        dfs(num, idx + 1, next, prevMulti * prevNum, remainSum, cur, res);
        cur.setLength(size);
        // case 3, remainSum - prevNum * prevMulti, +, -,
        cur.append('+');
        cur.append(next);
        dfs(num, idx + 1, next, 1, remainSum - prevNum * prevMulti, cur, res);
        cur.setLength(size);
        cur.append('-');
        cur.append(next);
        dfs(num, idx + 1, -next, 1, remainSum - prevNum * prevMulti, cur, res);
        cur.setLength(size);
    }

}
