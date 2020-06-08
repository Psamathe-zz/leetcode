package com.example.leetcode.medium;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] result = dailyTemperatures.dailyTemperatures(T);
        System.out.println(result);
    }


    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if(T[j] > T[i]){
                    result[i] = j-i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * faster solution
     * @param T
     * @return
     */
    public int[] dailyTemperaturesV1(int[] T) {
        // Stack<Integer> stack=new Stack<>();
        // int[] res=new int[T.length];
        // for(int i=T.length-1;i>=0;i--)
        // {
        //     while(!stack.isEmpty() && T[stack.peek()]<=T[i])
        //         stack.pop();
        //     if(!stack.isEmpty())
        //         res[i]=stack.peek()-i;
        //     stack.push(i);
        // }
        // return res;
        final int[] res=new int[T.length];
        for(int i=T.length-2;i>=0;i--){
            for(int j=i+1;true;j+=res[j])
            {
                if(T[j]>T[i]){
                    res[i]=j-i;
                    break;
                }
                else if(res[j]==0)
                    break;
            }
        }
        return res;
    }
}
