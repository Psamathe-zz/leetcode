package com.example.leetcode.medium;

/**
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 *
 * Note:
 *
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 */
public class GrumpyBookstoreOwner {
    public static void main(String[] args) {
        int[] customers = new int[]{3};
        int[] grumpy = new int[]{1};
        int X = 1;
        GrumpyBookstoreOwner grumpyBookstoreOwner = new GrumpyBookstoreOwner();
        grumpyBookstoreOwner.maxSatisfied(customers,grumpy,X);
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int length = customers.length;
        int[] satisfied = new int[length];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if(grumpy[i]==0)
                sum += customers[i];
            satisfied[i] = customers[i] * grumpy[i];
        }
        int count = 0;
        for (int i = 0; i < X; i++) {
            count += satisfied[i];
        }
        int max = count;
        for (int i = X; i < length; i++) {
            count += satisfied[i];
            count -= satisfied[i-X];
            max = Math.max(max,count);
        }
        return sum + max;
    }
}
