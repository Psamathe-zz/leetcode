package com.example.leetcode.challenge.november.week2;


/**
 * There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?
 *
 * Answer this question, and write an algorithm for the general case.
 *
 *
 *
 * General case:
 *
 * If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.
 *
 *
 *
 * Note:
 *
 * A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
 * After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.
 * Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
 */
public class PoorPigs {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/7664088.html
     * 如果我们让第一头猪第一次喝1，2，3桶，第二次喝4，5，6桶，而让第二头猪第一次喝1，4，7桶，第二次喝2，5，8桶，
     * 我们可以根据猪的死亡情况来确定是哪一桶的问题，实际上就把猪被毒死的那个节点当作了二维数组的横纵坐标来定位毒桶的位置，巧妙吧～
     * 更巧妙的是，如果再增加一头猪，实际上是给数组增加一个维度，变成了一个三维数组，那么三只猪，测两次，可以测27桶，叼不叼。
     * 这道题让我们求最少用多少猪来测，那么就是求数组的维度，我们知道了数组的总个数，所以要尽量增加数组的长宽，尽量减少维度。
     * 这里，数组的长宽其实都是测试的次数+1，所以我们首先要确定能测的次数，通过总测试时间除以毒发时间，再加上1就是测试次数。
     * 有了数组长宽m，那么如果有x只猪，能测的桶数为m的x次方，现在我们给定了桶数N，要求x，就log一下就行，然后用个换底公式，就可以求出x的值了，参见代码如下：
     * @param buckets
     * @param minutesToDie
     * @param minutesToTest
     * @return
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int)Math.ceil(Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1));
    }
}
