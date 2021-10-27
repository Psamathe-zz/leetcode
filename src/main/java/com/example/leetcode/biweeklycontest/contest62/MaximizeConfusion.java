package com.example.leetcode.biweeklycontest.contest62;


/**
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
 *
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 *
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
 *
 *
 *
 * Example 1:
 *
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
 * There are four consecutive 'T's.
 * Example 2:
 *
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
 * Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
 * In both cases, there are three consecutive 'F's.
 * Example 3:
 *
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
 * Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT".
 * In both cases, there are five consecutive 'T's.
 *
 */
public class MaximizeConfusion {
    public static void main(String[] args) {
        MaximizeConfusion maximizeConfusion = new MaximizeConfusion();
        maximizeConfusion.maxConsecutiveAnswers("TTFTTFTT",1);
    }

    /**
     * https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/solution/java-hua-dong-chuang-kou-by-fei-xiao-r-vslp/
     * @param answerKey
     * @param k
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int left = 0,right = 0;
        int num = 0;
        int length = answerKey.length();
        int count =k;
        while(right < length){
            if(answerKey.charAt(right) == 'F' && count >= 0)
                count--;
            if(count == -1) {
                num = Math.max(num,right-left);
                if(answerKey.charAt(left) == 'F')
                    count=1;
                left++;
            }else right++;
        }
        num = Math.max(num,right-left);
        count = k;
        left = 0;right = 0;
        while(right < length){
            if(answerKey.charAt(right) == 'T' && count>=0)
                count--;
            if(count == -1) {
                num = Math.max(num,right-left);
                if(answerKey.charAt(left) == 'T')
                    count=1;
                left++;
            } else
                right++;
        }
        return Math.max(num,right-left);
    }
}
