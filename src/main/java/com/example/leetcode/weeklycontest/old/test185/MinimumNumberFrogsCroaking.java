package com.example.leetcode.weeklycontest.old.test185;

/**
 * Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple “croak” are mixed. Return the minimum number of different frogs to finish all the croak in the given string.
 *
 * A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of valid "croak" return -1.
 *
 * Example 1:
 *
 * Input: croakOfFrogs = "croakcroak"
 * Output: 1
 * Explanation: One frog yelling "croak" twice.
 * Example 2:
 *
 * Input: croakOfFrogs = "crcoakroak"
 * Output: 2
 * Explanation: The minimum number of frogs is two.
 * The first frog could yell "crcoakroak".
 * The second frog could yell later "crcoakroak".
 * Example 3:
 *
 * Input: croakOfFrogs = "croakcrook"
 * Output: -1
 * Explanation: The given string is an invalid combination of "croak" from different frogs.
 * Example 4:
 *
 * Input: croakOfFrogs = "croakcroa"
 * Output: -1
 */
public class MinimumNumberFrogsCroaking {

    public static void main(String[] args) {
        String croakOfFrogs = "crcoakroak";
        MinimumNumberFrogsCroaking minimumNumberFrogsCroaking = new MinimumNumberFrogsCroaking();
        int result = minimumNumberFrogsCroaking.minNumberOfFrogsV2(croakOfFrogs);
        System.out.println(result);
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int result = 1;

        if(croakOfFrogs == null || croakOfFrogs.length() == 0 || croakOfFrogs.length() % 5 != 0)
            return -1;

        int v = croakOfFrogs.length() / 5;

        int[][] metric = new int[5][v];
        int indexC = 0;
        int indexR = 0;
        int indexO = 0;
        int indexA = 0;
        int indexK = 0;
        int index = 0;

        for(char c : croakOfFrogs.toCharArray()){
            if(c == 'c' && indexC< v){
                metric[0][indexC++] = index;
            } else if(c == 'r' && indexR< v){
                metric[1][indexR++] = index;
            } else if(c == 'o' && indexO< v){
                metric[2][indexO++] = index;
            } else if(c == 'a' && indexA< v){
                metric[3][indexA++] = index;
            } else if(c == 'k' && indexK< v){
                metric[4][indexK++] = index;
            } else{
                return -1;
            }
            index++;
        }


        for(int i=0; i< v; i++){
            for(int j=0; j < 4 ;j++){
                if(metric[j][i]>= metric[j+1][i])
                    return -1;
            }
        }
        for(int i=0; i< v-1; i++){
            for(int j = i+i; j<v;j++){
                if(metric[4][i]> metric[0][j] && j - i > result - 1){
                    result = j - i + 1;
                }
            }
        }

        return result;
    }


    /**
     * better solution
     * @param croakOfFrogs
     * @return
     */
    public int minNumberOfFrogsV2(String croakOfFrogs) {
        int ans = 0;
        int frogsAvailable = 0;
        int[] counts = new int[5];
        for (char c : croakOfFrogs.toCharArray()) {
            int x = charToInt(c);
            if (x == 0) {
                if (frogsAvailable > 0) frogsAvailable--;
                else ans++;
                counts[0]++;
            } else if (x == 4) {
                if (counts[x-1] == 0) return -1;
                counts[x-1]--;
                frogsAvailable++;
            } else {
                if (counts[x-1] == 0) return -1;
                counts[x-1]--;
                counts[x]++;
            }
        }
        for (int i : counts) if (i > 0) return -1;
        return ans;
    }

    int charToInt(char c) {
        if (c == 'c') return 0;
        if (c == 'r') return 1;
        if (c == 'o') return 2;
        if (c == 'a') return 3;
        if (c == 'k') return 4;
        return -1;
    }
}
