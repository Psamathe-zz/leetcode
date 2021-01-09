package com.example.leetcode.challenge.test2020.september.week2;

public class CompareVersionNumbers {
    public static void main(String[] args) {
        CompareVersionNumbers compareVersionNumbers = new CompareVersionNumbers();
        compareVersionNumbers.compareVersion("1","1.1");
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4244123.html
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        int n1 = version1.length(), n2 = version2.length();
        int i = 0, j = 0, d1 = 0, d2 = 0;
        while (i < n1 || j < n2) {
            while (i < n1 && version1.charAt(i)!= '.') {
                d1 = d1 * 10 + version1.charAt(i++) - '0';
            }
            while (j < n2 && version2.charAt(j)!= '.') {
                d2 = d2 * 10 + version2.charAt(j++) - '0';
            }


            if (d1 > d2) return 1;
            else if (d1 < d2) return -1;
            d1 = d2 = 0;
            ++i; ++j;
        }
        return 0;
    }
}
