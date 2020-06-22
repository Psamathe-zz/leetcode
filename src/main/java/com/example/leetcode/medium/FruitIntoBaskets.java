package com.example.leetcode.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 * [1,0,1,4,1,4,1,2,3]
 * 5
 */
public class FruitIntoBaskets {
    public static void main(String[] args) {
        int[] tree = new int[]{0,1,2,2};
        FruitIntoBaskets fruitIntoBaskets = new FruitIntoBaskets();
        int result = fruitIntoBaskets.totalFruit(tree);
        System.out.println(result);

    }
    public int totalFruit(int[] tree) {
        int result = 0;

        Set<Integer> myset = new HashSet<>();
        Queue<Integer> myqueue = new LinkedList<>();

        int lastValue = 0;

        for(int value : tree){
            if(!myset.contains(value) && myset.size()==2){
                if(myqueue.size()>result)
                    result = myqueue.size();
                int toRemove = getSetDiffValue(myset,lastValue);
                myset.remove(toRemove);

                while (myqueue.contains(toRemove)){
                    myqueue.poll();
                }
            }
            myset.add(value);
            myqueue.add(value);
            lastValue = value;
        }
        if(myqueue.size()>result)
            result = myqueue.size();

        return result;
    }

    int getSetDiffValue(Set<Integer> myset,int value){
        return myset.stream().filter(e -> e!=value).distinct().findFirst().orElse(0);
    }

    /**
     * faster solution
     */

    public int totalFruitV2(int[] tree) {
        int fruit1 = -1;
        int fruit2 = -1;
        int maxCount = -1;
        int count = -1;
        int consecutive = 0;
        int lastConsecutive = -1;

        int n = tree.length;
        if(n <= 2)
        {
            return n;
        }
        else
        {
            // in case, the first few elements might all be the same
            fruit1 = tree[0];

            int lastCheck = 1;

            fruit1 = tree[0];
            lastConsecutive = tree[0];
            count = 1;
            for(int i = lastCheck; i < n; i++)
            {
                int currentFruit = tree[i];
                if(currentFruit == fruit1 || currentFruit == fruit2)
                {
                    if(currentFruit == lastConsecutive)
                    {
                        consecutive++;
                    }
                    else
                    {
                        lastConsecutive = currentFruit;
                        consecutive = 1;
                    }
                    count++;
                }
                else
                {
                    if(fruit2 == -1)
                    {
                        lastConsecutive = currentFruit;
                        consecutive = 1;
                        fruit2 = tree[i];

                        count++;
                    }
                    else
                    {
                        fruit1 = tree[i-1];
                        fruit2 = tree[i];
                        if(count > maxCount)
                        {
                            maxCount = count;
                        }
                        count = consecutive+1;
                        consecutive = 1;
                        lastConsecutive = tree[i];
                    }
                }
            }
        }

        if(count > maxCount)
        {
            maxCount = count;
        }

        return maxCount;
    }

    /**
     * less memory
     */

    public int totalFruitV3(int[] tree) {
        // Longest subarray with 2 elements. Logic pattern "....aaabbbc..."
        int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
        for (int c :  tree) {
            cur = c == a || c == b ? cur + 1 : count_b + 1;
            count_b = c == b ? count_b + 1 : 1;
            if (b != c) {
                a = b;
                b = c;
            }
            res = Math.max(res, cur);
        }
        return res;
    }

}
