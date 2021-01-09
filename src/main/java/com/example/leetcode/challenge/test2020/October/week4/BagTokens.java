package com.example.leetcode.challenge.test2020.October.week4;


import java.util.Arrays;

/**
 * You have an initial power of P, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).
 *
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 *
 * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
 * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 *
 * Return the largest possible score you can achieve after playing any number of tokens.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = [100], P = 50
 * Output: 0
 * Explanation: Playing the only token in the bag is impossible because you either have too little power or too little score.
 * Example 2:
 *
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
 * There is no need to play the 1st token since you cannot play it face up to add to your score.
 * Example 3:
 *
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 * Explanation: Play the tokens in this order to get a score of 2:
 * 1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
 * 2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
 * 3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
 * 4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.
 *
 *
 * Constraints:
 *
 * 0 <= tokens.length <= 1000
 * 0 <= tokens[i], P < 104
 */
public class BagTokens {
    public static void main(String[] args) {

    }

    //贪心+排序+break；（最常见的组合）；
    /*一句话：排列+增加score的多执行，减少score的少执行；*/
    public int bagOfTokensScore(int[] tokens, int P) {
        //特判；
        if(tokens.length==0 || P==0){
            return 0;
        }
        int left=0;
        int right=tokens.length-1;
        int score=0;
        Arrays.sort(tokens);//默认升序排列；
        while(left<=right){
            //用目前所拥有的P争取最多的score;
            while(left<=right && P>=tokens[left]){//while里又有一个left《=right的判断，又出现了；
                P-=tokens[left++];
                score++;
            }
            //因为程序到达这里，可能会有left>right的情况；
            //if(score==0 || left>right){//写成left>right是错的，因为这样就没有到达最优的score；
            //有等号的意思相当于是，我现在已经花钱增加score了，此时已经是score的最大值，如果你没有等号，则表示你仍然要进行 花一个score换P的操作，但是我们已经不需要P的了，因为这时如果执行了这个操作，那么就遍历完所有元素了，也就没有再增加score的操作了；
            if(score==0 || left>=right){
                //continue;
                break;
            }
             /*while(left<=right && score>0){
                P+=tokens[right--];
                score--;
             }*/ //这样就没有贪心的思想了；
            //花一个score获得当前一个最大的P；
            P+=tokens[right--];
            score--;
        }
        return score;
    }

    public int bagOfTokensScoreV1(int[] tokens, int P) {
        Arrays.sort(tokens);

        int res = 0;
        int lastIdx = tokens.length-1;
        // int max = 0;
        for (int i = 0; i <= lastIdx; i ++) {
            if (tokens[i] <= P) {
                res++;
                P -= tokens[i];
                // max = Math.max(max,res);
            } else if (res > 0 && i+1 < lastIdx) {
                // 当前有分才有翻牌的机会
                // 并且翻过来的牌子还要有机会用才可以，不然就只会减分了
                P += tokens[lastIdx];
                lastIdx--;
                i--;
                res --;
            }
        }
        return res;
    }


}
