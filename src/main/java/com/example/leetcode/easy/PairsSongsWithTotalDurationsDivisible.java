package com.example.leetcode.easy;

/**
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 *
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 *
 *
 * Note:
 *
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 * [439,407,197,191,291,486,30,307,11]
 * [14,161,302,232,270,428,155,64,499,400,25,349,434,427,5,265,20,346,463,10,1,163,189,345,390,212,498,281,308,482,447,217,318,239,374,449,298,213,2,230,5,500,300,390,139,484,464,477,111,88,93,198,181,113,393,283,383,205,42,292,335,392,384,268,361,462,413,176,118,111,143,242,166,286,177,52,126,342,415,302,210,48,369,148,209,87,212,53,296,95,97,45,467,47,373,404,43,439,19,64,289,218,268,460,238,456,490,155,429,218,301,225,228,237,453,267,259,327,427,169,176,322,216,451,29,327,404,177,225,44,248,174,287,326,441,354,110,4,226,324,331,158,454,469,354,383,336,211,133,500,233,330,471,327,426,478,195,232,163,312,126,51,161,248,433,348,464,206,480,478,98,354,304,424,338,382,431,379,194,488,6,494,394,469,430,1,207,307,172,47,269,472,415,163,309,68,213,175,343,187,15,232,429,389,373,143,146,88,58,320,116,82,482,125,343,329,115,116,183,389,112,294,74,89,62]
 */
public class PairsSongsWithTotalDurationsDivisible {
    public static void main(String[] args) {
        int[] time = new int[]{14,161,302,232,270,428,155,64,499,400,25,349,434,427,5,265,20,346,463,10,1,163,189,345,390,212,498,281,308,482,447,217,318,239,374,449,298,213,2,230,5,500,300,390,139,484,464,477,111,88,93,198,181,113,393,283,383,205,42,292,335,392,384,268,361,462,413,176,118,111,143,242,166,286,177,52,126,342,415,302,210,48,369,148,209,87,212,53,296,95,97,45,467,47,373,404,43,439,19,64,289,218,268,460,238,456,490,155,429,218,301,225,228,237,453,267,259,327,427,169,176,322,216,451,29,327,404,177,225,44,248,174,287,326,441,354,110,4,226,324,331,158,454,469,354,383,336,211,133,500,233,330,471,327,426,478,195,232,163,312,126,51,161,248,433,348,464,206,480,478,98,354,304,424,338,382,431,379,194,488,6,494,394,469,430,1,207,307,172,47,269,472,415,163,309,68,213,175,343,187,15,232,429,389,373,143,146,88,58,320,116,82,482,125,343,329,115,116,183,389,112,294,74,89,62};
        PairsSongsWithTotalDurationsDivisible pairsSongsWithTotalDurationsDivisible = new PairsSongsWithTotalDurationsDivisible();
        pairsSongsWithTotalDurationsDivisible.numPairsDivisibleBy60(time);
    }

    public int numPairsDivisibleBy60(int[] time) {
        int length = time.length;
        int[] count = new int[60];
        int res = 0;

        for (int i = 0; i < length; i++) {
            count[time[i]%60]++;
        }
        res += count(count[0]);
        res += count(count[30]);
        for (int i = 1; i < 30; i++) {
            res += count[i] * count[60-i];
        }

        return res;
    }

    public int count(int value){
        int res = 0;
        for (int i = 1; i < value ; i++) {
            res += i;
        }
        return res;
    }
}
