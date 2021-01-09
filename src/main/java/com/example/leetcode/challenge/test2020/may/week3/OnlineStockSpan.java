package com.example.leetcode.challenge.test2020.may.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards)
 * for which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 *
 *
 *
 * Example 1:
 *
 * Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * Output: [null,1,1,1,2,1,4,6]
 * Explanation:
 * First, S = StockSpanner() is initialized.  Then:
 * S.next(100) is called and returns 1,
 * S.next(80) is called and returns 1,
 * S.next(60) is called and returns 1,
 * S.next(70) is called and returns 2,
 * S.next(60) is called and returns 1,
 * S.next(75) is called and returns 4,
 * S.next(85) is called and returns 6.
 *
 * Note that (for example) S.next(75) returned 4, because the last 4 prices
 * (including today's price of 75) were less than or equal to today's price.
 *
 *
 * Note:
 *
 * Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
 * There will be at most 10000 calls to StockSpanner.next per test case.
 * There will be at most 150000 calls to StockSpanner.next across all test cases.
 * The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
 */
public class OnlineStockSpan {
    public static void main(String[] args) {
        int price = 100;
        OnlineStockSpan obj = new OnlineStockSpan();
        int param_1 = obj.nextV1(100);
        int param_2 = obj.nextV1(80);
        int param_3 = obj.nextV1(60);
        int param_4 = obj.nextV1(70);
        int param_5 = obj.nextV1(60);
        int param_6 = obj.nextV1(75);
        int param_7 = obj.nextV1(85);
        System.out.println(param_1);
    }

    List<Integer> list;
    public void OnlineStockSpanOld() {
        list = new ArrayList<>();
    }

    public int next(int price) {
        list.add(price);
        int length = list.size();
        int i;
        for(i = length - 1; i >= 0 && list.get(i) <= price; i --){

        }
        return length - i - 1;
    }

    /**
     * faster solution
     */
    private int[] pricesStack;
    private int[] spansStack;
    private int top;

    public OnlineStockSpan() {
        pricesStack = new int[10000];
        spansStack = new int[10000];
        top = -1;
    }

    public int nextV1(int price) {
        int span = 1;
        while (0 <= top && pricesStack[top] <= price) {
            span += spansStack[top--];
        }
        top++;
        pricesStack[top] = price;
        spansStack[top] = span;
        return span;
    }

    /**
     * less memory
     */
    private List<Integer> listless = new ArrayList<>();
    private List<Integer> prev = new ArrayList<>();

    public int nextV2(int price) {
        listless.add(price);
        int i = listless.size() - 2;
        while (!prev.isEmpty() && i >= 0 && listless.get(i) <= price) i = prev.get(i);
        prev.add(i);
        return listless.size() - 1 - i;
    }
}
