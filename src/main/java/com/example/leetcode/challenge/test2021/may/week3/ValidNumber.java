package com.example.leetcode.challenge.test2021.may.week3;

public class ValidNumber {
    public static void main(String[] args) {

    }

    /**
     * https://www.jianshu.com/p/cc310eb4df37
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if (s == null)
            return false;
        s = s.toLowerCase().trim();
        boolean eSeen = false, numberBeforeESeen = false, numberAfterESeen = false;
        boolean pointSeen = false;
        int pt = 0;
        while (pt < s.length() ) {
            char ch = s.charAt(pt);
            if (Character.isDigit(ch)) {
                if (eSeen) numberAfterESeen = true;
                else numberBeforeESeen = true;
            } else if (ch == '.') {
                if (eSeen || pointSeen) return false;
                pointSeen = true;
            } else if (ch == '+' || ch == '-') {
                if (pt != 0 && s.charAt(pt - 1) != 'e') {
                    return false;
                }
            } else if (ch == 'e' || ch == 'E') {
                if (eSeen || (!numberBeforeESeen)) return false;
                eSeen = true;
            } else return false;
            pt++;
        }
        return numberBeforeESeen && (eSeen ? numberAfterESeen : true);
    }
}
