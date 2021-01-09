package com.example.leetcode.challenge.test2020.August.week4;

/**
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 *
 * Do NOT use system's Math.random().
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: [7]
 * Example 2:
 *
 * Input: 2
 * Output: [8,4]
 * Example 3:
 *
 * Input: 3
 * Output: [8,1,10]
 *
 *
 * Note:
 *
 * rand7 is predefined.
 * Each testcase has one argument: n, the number of times that rand10 is called.
 *
 *
 * Follow up:
 *
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 */
public class ImplementRand10 {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/9727206.html
     * @return
     */
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40)
                return num % 10 + 1;
        }
    }

    int rand7(){
        return 1;
    }
}
