package com.example.leetcode.easy;

/**
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 *
 * For example, with A = "abcd" and B = "cdabcdab".
 *
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
 *
 * Note:
 * The length of A and B will be between 1 and 10000.
 * "abcabcabcabc"
 * "abac"
 */
public class RepeatedStringMatch {
    public static void main(String[] args) {
        String A = "abcd";
        String B = "cdabcdab";
        RepeatedStringMatch repeatedStringMatch = new RepeatedStringMatch();
        int res = repeatedStringMatch.repeatedStringMatch(A,B);
        System.out.println(res);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/7631434.html
     *
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch(String A, String B) {
        String t = A;
        for (int i = 1; i <= B.length() / A.length() + 2; ++i) {
            if (t.indexOf(B) >= 0)
                return i;
            t += A;
        }
        return -1;
    }

    public int repeatedStringMatchV1(String A, String B) {
        int n1 = A.length(), n2 = B.length(), cnt = 1;
        String t = A;
        while (t.length() < n2) {
            t += A;
            ++cnt;
        }
        if (t.indexOf(B) >=0) return cnt;
        t += A;
        return (t.indexOf(B) >=0) ? cnt + 1 : -1;
    }
}
