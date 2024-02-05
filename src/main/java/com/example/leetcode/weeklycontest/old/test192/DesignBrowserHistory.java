package com.example.leetcode.weeklycontest.old.test192;

import java.util.ArrayList;
import java.util.List;

/**
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","facebook.com","leetcode.com"]
 * Expected:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 */
public class DesignBrowserHistory {
    public static void main(String[] args) {
        String homepage = "leetcode.com";
        String url = "google.com";
        DesignBrowserHistory obj = new DesignBrowserHistory(homepage);
        obj.visit(url);
        obj.visit("facebook.com");
        obj.visit("youtube.com");
        String param_2 = obj.back(1);
        String param_21 = obj.back(1);
        String param_3 = obj.forward(1);
        obj.visit("linkedin.com");
        String param_4 = obj.forward(2);
        String param_5 = obj.back(2);
        String param_6 = obj.back(7);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_3);
        System.out.println(param_3);
        System.out.println(param_3);
    }

    List<String> visitHistory;
    int current = -1;
    int end = -1;
    public DesignBrowserHistory(String homepage) {
        visitHistory = new ArrayList<>();
        visit(homepage);
    }

    public void visit(String url) {
        current++;
        if(visitHistory.size() <= current) {
            visitHistory.add(url);
        } else {
            visitHistory.set(current, url);
        }
        end = current;
    }

    public String back(int steps) {
        current = Math.max(current - steps,0);
        return visitHistory.get(current);
    }

    public String forward(int steps) {
        current = Math.min(current + steps , end);
        return visitHistory.get(current);
    }
}
