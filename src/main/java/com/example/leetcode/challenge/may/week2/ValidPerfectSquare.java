package com.example.leetcode.challenge.may.week2;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 14
 * Output: false
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        ValidPerfectSquare validPerfectSquare = new ValidPerfectSquare();
        boolean result = validPerfectSquare.isPerfectSquare(16);
        Math.sqrt(14);
        System.out.println(result);
    }


    /**
     * 我们先抛开这个问题不看，大家都知道等差数列的吧~好，我们就来看看一个等差数列，他的a0=1,，d=2，那么an=2*n-1.

     *     那么我们对这个等差数列求和会发生什么呢？如下：

     *  1 + 3 + 5 +  + 2n-1 = Math.pow(n,2)
     *
     * 我们突然发现，这个等差数列求和居然就正好n的平方，那么也就是说任何一个平方数都可以拆分成一个等差数列求和！
     *
     *
     *
     *     我们可以应用这个思想来解决上面的题目。有个需要注意的：这个等差数列初值为1，等差为2。
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if(num == 1)
            return true;
        int index = 1;
        while (num > 0 ){
            num -= 2 * index - 1;
            index++;
        }
        if(0 == num)
            return true;
        else
            return false;
    }


    /**
     * faster solution
     * @param num
     * @return
     */
    public boolean isPerfectSquareV2(int num) {
        long l = 1;
        long r = num;

        while (l <= r) {
            long mid = l - (l - r) / 2;

            if (mid * mid == num)
                return true;
            else if (mid * mid < num)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return false;
    }


    public boolean isPerfectSquareV3(int num) {
        long x=num;
        while(x * x > num){
            x=(x+num/x)/2;//牛顿法
        }
        return x * x == num;
    }
}
