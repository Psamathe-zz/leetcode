package com.example.leetcode.weeklycontest.test260;

import java.util.*;

/**
 * You are given a string s that contains digits 0-9, addition symbols '+', and multiplication symbols '*' only, representing a valid math expression of single digit numbers (e.g., 3+5*2). This expression was given to n elementary school students. The students were instructed to get the answer of the expression by following this order of operations:
 *
 * Compute multiplication, reading from left to right; Then,
 * Compute addition, reading from left to right.
 * You are given an integer array answers of length n, which are the submitted answers of the students in no particular order. You are asked to grade the answers, by following these rules:
 *
 * If an answer equals the correct answer of the expression, this student will be rewarded 5 points;
 * Otherwise, if the answer could be interpreted as if the student used the incorrect order of operations, once or multiple times, this student will be rewarded 2 points;
 * Otherwise, this student will be rewarded 0 points.
 * Return the sum of the points of the students.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "7+3*1*2", answers = [20,13,42]
 * Output: 7
 * Explanation: As illustrated above, the correct answer of the expression is 13, therefore one student is rewarded 5 points: [20,13,42]
 * A student might have used this incorrect order of operations: 7+3=10, 10*1=10, 10*2=20. Therefore one student is rewarded 2 points: [20,13,42]
 * The points for the students are: [2,5,0]. The sum of the points is 2+5+0=7.
 * Example 2:
 *
 * Input: s = "3+5*2", answers = [13,0,10,13,13,16,16]
 * Output: 19
 * Explanation: The correct answer of the expression is 13, therefore three students are rewarded 5 points each: [13,0,10,13,13,16,16]
 * A student might have used this incorrect order of operations: 3+5=8, 8*2=16. Therefore two students are rewarded 2 points: [13,0,10,13,13,16,16]
 * The points for the students are: [5,0,0,5,5,2,2]. The sum of the points is 5+0+0+5+5+2+2=19.
 * Example 3:
 *
 * Input: s = "6+0*1", answers = [12,9,6,4,8,6]
 * Output: 10
 * Explanation: The correct answer of the expression is 6.
 * If a student had used some incorrect order of operations, the answer would also be 6.
 * By the rules of grading, the students will still be rewarded 5 points (as they got the correct answer), not 2 points.
 * The points for the students are: [0,0,5,0,0,5]. The sum of the points is 10.
 */
public class ScoreOfStudent {
    public static void main(String[] args) {
        ScoreOfStudent scoreOfStudent = new ScoreOfStudent();
        scoreOfStudent.scoreOfStudents("6+0*1", new int[]{12,9,6,4,8,6});
    }

    int res1;
    private Set<Integer>[][] map;
    public int scoreOfStudents(String s, int[] answers) {
        int res = 0;
        firstStep(s);
        int n = s.length();
        map = new Set[n][n];
        dfs(s.toCharArray(), 0, n - 1);
        Set<Integer> wrong = map[0][s.length() - 1];
        for (int val : answers){
            if(val == res1)
                res += 5;
            else if(wrong.contains(val))
                res += 2;
        }
        return res;
    }
    public void firstStep(String s){
        Deque<Integer> dequeVal = new LinkedList<>();
        Deque<String> dequeOp = new LinkedList<>();
        int val = 0;
        boolean isVal = false;
        for (char c : s.toCharArray()){
            if(c == '+' || c == '*'){
                if(isVal)
                    dequeVal.addLast(val);
                dequeOp.addLast(String.valueOf(c));
                val = 0;
            } else {
                isVal = true;
                val += c - '0';
            }
        }
        if(isVal)
            dequeVal.addLast(val);

        dequeVal.addLast(dequeVal.pollFirst());
        int size = dequeOp.size();
        while (size > 0){
            String op = dequeOp.pollFirst();
            if(op.equals("+")){
                dequeVal.addLast(dequeVal.pollFirst());
                dequeOp.addLast(op);
            } else {
                dequeVal.addLast(dequeVal.pollLast() * dequeVal.pollFirst());
            }
            size--;
        }

        res1 = 0;
        while (!dequeVal.isEmpty()){
            res1 += dequeVal.pollFirst();
        }
    }

    private Set<Integer> dfs(char[] chars, int l, int r) {
        // 避免重复计算
        if (map[l][r] != null) {
            return map[l][r];
        }
        // 存储这一区间的可能答案
        Set<Integer> val = new HashSet<>();
        // 枚举将区间分成左右两区间的分隔符位置('+' / '*')
        for (int i = l + 1; i < r; i++) {
            if (!Character.isDigit(chars[i])) {
                Set<Integer> left = dfs(chars, l, i - 1);
                Set<Integer> right = dfs(chars, i + 1, r);
                for (Integer a : left) {
                    for (Integer b : right) {
                        int result;
                        if (chars[i] == '+') {
                            result = a + b;
                        } else {
                            result = a * b;
                        }
                        // 根据题意，最大答案为1000剪枝
                        if (result <= 1000) {
                            val.add(result);
                        }
                    }
                }
            }
        }
        // 如果区间长度为1，显然无需分割，可能的答案就是自身
        if (l == r) {
            val.add(chars[l] - '0');
        }
        map[l][r] = val;
        return val;
    }


}
