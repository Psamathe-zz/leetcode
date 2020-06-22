package com.example.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        int[][] envelopes = new int[][]{{20,48},{12,35},{32,16},{30,17},{35,23},{37,33},{14,10},{23,31},{8,7},{17,6},{6,19},{3,6},{22,34},{10,26},{16,46},{20,11},{20,28},{39,33},{44,47},{29,18},{8,25},{17,24},{43,27},{45,12},{40,29},{37,10},{49,20},{2,4},{33,1},{46,27},{39,24},{34,6},{15,15},{21,40},{7,30},{19,9},{11,39},{29,31},{28,37},{4,37},{8,36},{38,1},{48,46},{4,1},{43,29},{41,32},{19,23},{37,35},{31,9},{8,1},{34,30},{2,20},{49,21},{16,26},{38,12},{27,20},{43,7},{25,8},{17,36},{42,40},{45,14},{16,10},{19,47},{5,37},{21,7},{30,3},{42,40},{18,40},{35,41},{12,33},{4,15},{40,22},{4,29},{27,27},{41,40},{12,33},{37,41},{21,11},{5,24},{4,32}};
        RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();
        int result =  russianDollEnvelopes.maxEnvelopes1(envelopes);
        System.out.println(result);
    }

    public static int maxEnvelopes1(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if( index < 0 )
                index = -(index + 1);
            dp[index] = envelope[1];
            if( index == len )
                len++;
        }
        return len;
    }
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int max = 0;
        int dp [] = new int [envelopes.length];
        for(int i = 0;i<envelopes.length;i++){
            dp[i] = 1;
            for(int j = 0; j < i ; j++){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            if(max < dp[i])
                max = dp[i];
        }
        return max;
    }


    public int maxEnvelopesOld(int[][] envelopes) {
        if(envelopes.length == 0)
            return 0;
        List<Envelope> list = new ArrayList<>();
        for(int[] envelope : envelopes){
            list.add(new Envelope(envelope[0],envelope[1]));
        }
        int result = Integer.MIN_VALUE;

        for(Envelope envelope: list){
            List<Envelope> envelopeListNew = new ArrayList<>(list);
            envelopeListNew.remove(envelope);
            int value = help(envelopeListNew,envelope);
            if(value + 1 > result)
                result = value + 1;

            if(result == list.size())
                break;
        }

        return result;

    }

    public int help(List<Envelope> envelopeList,Envelope envelope){
        int result = Integer.MIN_VALUE;

        if(envelopeList.size() == 0)
            return 0;
        for(Envelope envelopeTemp : envelopeList){
            if(envelopeTemp.x>envelope.x && envelopeTemp.y>envelope.y){
                List<Envelope> envelopeListNew = new ArrayList<>(envelopeList);
                envelopeListNew.remove(envelopeTemp);
                int value = help(envelopeListNew,envelopeTemp);
                if( value + 1 > result)
                    result = value + 1;

                if(result == envelopeList.size())
                    break;
            }
        }
        return result== Integer.MIN_VALUE?0:result;
    }

    public class Envelope {
        int x;
        int y;

        public Envelope(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
