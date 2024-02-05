package com.example.leetcode.weeklycontest.old.test276;

/**
 * You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
 *
 * The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.
 *
 * For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
 * If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
 * If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
 * Return the maximum points you can earn for the exam.
 *
 *
 *
 * Example 1:
 *
 * Input: questions = [[3,2],[4,3],[4,4],[2,5]]
 * Output: 5
 * Explanation: The maximum points can be earned by solving questions 0 and 3.
 * - Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
 * - Unable to solve questions 1 and 2
 * - Solve question 3: Earn 2 points
 * Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
 * Example 2:
 *
 * Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * Output: 7
 * Explanation: The maximum points can be earned by solving questions 1 and 4.
 * - Skip question 0
 * - Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
 * - Unable to solve questions 2 and 3
 * - Solve question 4: Earn 5 points
 * Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
 *
 * [[21,5],[92,3],[74,2],[39,4],[58,2],[5,5],[49,4],[65,3]]
 * [[21,2],[1,2],[12,5],[7,2],[35,3],[32,2],[80,2],[91,5],[92,3],[27,3],[19,1],[37,3],[85,2],[33,4],[25,1],[91,4],[44,3],[93,3],[65,4],[82,3],[85,5],[81,3],[29,2],[25,1],[74,2],[58,1],[85,1],[84,2],[27,2],[47,5],[48,4],[3,2],[44,3],[60,5],[19,2],[9,4],[29,5],[15,3],[1,3],[60,2],[63,3],[79,3],[19,1],[7,1],[35,1],[55,4],[1,4],[41,1],[58,5]]
 */
public class SolvingQuestionsWithBrainpower {
    public static void main(String[] args) {
        SolvingQuestionsWithBrainpower solvingQuestionsWithBrainpower = new SolvingQuestionsWithBrainpower();
        solvingQuestionsWithBrainpower.mostPoints(new int[][]{
                {21,5},
                {92,3},
                {39,4},
                {58,2},
                {5,5},
                {49,4},
                {65,3},
        });
    }

    /**
     * https://leetcode-cn.com/problems/solving-questions-with-brainpower/solution/dao-xu-dp-by-endlesscheng-2qkc/
     * @param questions
     * @return
     */
    public long mostPoints(int[][] questions) {
        int length = questions.length;
        long[] preSolve = new long[length + 1];
        for (int i = 0; i < length; i++) {
            preSolve[i + 1] = Math.max(preSolve[i+1], preSolve[i]);
            int j = Math.min(i + questions[i][1] + 1, length);
            preSolve[j] = Math.max(preSolve[j], preSolve[i] + questions[i][0]);
        }

        return preSolve[length];
    }
}
