package com.example.leetcode.weeklycontest.test183;

/**
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/discuss/565694/JavaC-100100-or-Creative-Counting-or-O(n)-or-1-ms
 *
 * Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:
 *
 * If the current number is even, you have to divide it by 2.
 *
 * If the current number is odd, you have to add 1 to it.
 *
 * It's guaranteed that you can always reach to one for all testcases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1101"
 * Output: 6
 * Explanation: "1101" corressponds to number 13 in their decimal representation.
 * Step 1) 13 is odd, add 1 and obtain 14.
 * Step 2) 14 is even, divide by 2 and obtain 7.
 * Step 3) 7 is odd, add 1 and obtain 8.
 * Step 4) 8 is even, divide by 2 and obtain 4.
 * Step 5) 4 is even, divide by 2 and obtain 2.
 * Step 6) 2 is even, divide by 2 and obtain 1.
 * Example 2:
 *
 * Input: s = "10"
 * Output: 1
 * Explanation: "10" corressponds to number 2 in their decimal representation.
 * Step 1) 2 is even, divide by 2 and obtain 1.
 * Example 3:
 *
 * Input: s = "1"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of characters '0' or '1'
 * s[0] == '1'
 */
public class NumberofStepsReduceNumber {

    public static void main(String[] args) {
        NumberofStepsReduceNumber numberofStepsReduceNumber = new NumberofStepsReduceNumber();
        int result = numberofStepsReduceNumber.numStepsVfaster("1101");
        System.out.println(result);
    }





    public int numSteps(String s) {
        if(s.equals("1")){
            return 0;
        } else if(s.charAt(s.length()-1) == '1'){
            return numSteps(reformat(s)) + 1;
        } else {
            return numSteps(s.substring(0,s.length()-1)) + 1;
        }
    }
    public String reformat(String s){
        char[] temp = new char[s.length() +1];
        int i = s.length()-1;
        boolean reset = false;
        while(i>=0){
            if(s.charAt(i) == '1') {
                if(reset == false){
                    temp[i+1] = '0';
                } else {
                    temp[i+1] = '1';
                }
            }
            else {
                if(reset == false){
                    temp[i+1] = '1';
                    reset = true;
                } else {
                    temp[i+1] = '0';
                }
            }

            i--;
        }
        if(reset == false)
            temp[i+1] = '1';
        return String.valueOf(temp).trim();

    }



    /**
     * version for short string
     * @param s
     * @return
     */
    public int numStepsVsimplme(String s) {
        int index = 0;
        long i = Long.valueOf(s,2);
        while (i != 1){
            if((i & 1) % 2 == 0){
                i = i>>1;
            } else {
                i = i+1;
            }
            index++;
        }
        return index;
    }

    /**
     * version faster
     * @param s
     * @return
     */
    public int numStepsVfaster(String s) {
        int res = 0;
        int carry = 0;
        for (int i = s.length() - 1; i >0; i--) {
            res++;
            if (s.charAt(i) - '0' + carry == 1) {
                res++;
                carry = 1;
            }
        }

        return res + carry;
    }
}
