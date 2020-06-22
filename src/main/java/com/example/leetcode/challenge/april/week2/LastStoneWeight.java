package com.example.leetcode.challenge.april.week2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class LastStoneWeight {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2,7,4,1,8,1};
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        int result = lastStoneWeight.lastStoneWeightV2(nums1);
        System.out.println(result);

    }

    public int lastStoneWeight(int[] stones) {
        List<Integer> stonesList = Arrays.stream(stones).boxed().sorted().collect(Collectors.toList());
        while (stonesList.size() > 1){
            int first = stonesList.get(stonesList.size() - 1);
            int second = stonesList.get(stonesList.size() - 2);

            stonesList.remove(stonesList.size() - 1);
            stonesList.remove(stonesList.size() - 1);
            if(first>second){
                stonesList.add(first - second);
            }
            Collections.sort(stonesList);
        }
        return stonesList.size()==1?stonesList.get(0):0;
    }


    /**
     * faster version
     */
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    public int lastStoneWeightV2(int[] stones) {

        for(int i=0;i<stones.length;i++)
        {
            pq.add(stones[i]);
        }

        while(pq.size()!=1)
        {
            int x=pq.poll();
            int y=pq.poll();
            if(x!=y )
                if(x<y)
                    pq.add(y-x);
                else
                    pq.add(x-y);
            else
                pq.add(0);
        }

        int res= pq.poll();
        return res;
    }
}
