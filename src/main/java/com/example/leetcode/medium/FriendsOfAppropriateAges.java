package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 *
 * How many total friend requests are made?
 *
 * Example 1:
 *
 * Input: [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 *
 * Input: [20,30,100,110,120]
 * Output:
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 *
 * Notes:
 *
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 */
public class FriendsOfAppropriateAges {
    public static void main(String[] args) {
        int[] ages = new int[]{16,17,18};
        FriendsOfAppropriateAges friendsOfAppropriateAges = new FriendsOfAppropriateAges();
        int result = friendsOfAppropriateAges.numFriendRequests(ages);
        System.out.println(result);
    }

    public int numFriendRequests(int[] ages) {
        int result = 0;

        Arrays.sort(ages);
        int[] numInAge = new int[121];
        int[] sumInAge = new int[121];
        for (int age : ages)
            ++numInAge[age];
        for (int i = 1; i <= 120; ++i) {
            sumInAge[i] = numInAge[i] + sumInAge[i - 1];
        }
        for (int i = 15; i <= 120; ++i) {
            if (numInAge[i] == 0)
                continue;
            int cnt = sumInAge[i] - sumInAge[i /2 + 7];
            result += cnt * numInAge[i] - numInAge[i];
        }
        return result;
    }

    /**
     * faster solution
     * 我们可以来优化一下上面的解法，根据上面的分析，其实题目给的这三个条件可以归纳成一个条件，若A想加B的好友，那么B的年龄必须在 (A*0.5+7, A] 这个范围内，
     * 由于区间要有意义的话，A*0.5+7 < A 必须成立，解出 A > 14，那么A最小就只能取15了。意思说你不能加小于15岁的好友（青少年成长保护？？？）。
     * 我们的优化思路是对于每一个年龄，我们都只要求出上面那个区间范围内的个数，就是符合题意的。
     * 那么既然是求区域和，建立累加数组就是一个很好的选择了，首先我们先建立一个统计数组numInAge，范围是[0, 120]，
     * 用来统计在各个年龄点上有多少人，然后再建立累加和数组sumInAge。这个都建立好了以后，我们就可以开始遍历，由于之前说了不能加小于15的好友，
     * 所以我们从15开始遍历，如果某个年龄点没有人，直接跳过。然后就是统计出 (A*0.5+7, A] 这个范围内有多少人，可以通过累计和数组来快速求的，
     * 由于当前时间点的人可以跟这个区间内的所有发好友申请，而当前时间点可能还有多人，所以二者相乘，但由于我们但区间里还包括但当前年龄点本身，所以还要减去当前年龄点上的人数，参见代码如下：
     */
    public int numFriendRequestsV1(int[] ages) {
        int res = 0;
        int[] nums = new int[121], sum = new int[121];

        for (int age : ages)
            nums[age]++;
        for (int i = 1; i < 121; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        // (age > age/2 + 7 ==> age > 14)
        for (int i = 15; i < 121; i++) {
            int count = sum[i] - sum[i / 2 + 7];
            res += nums[i] * count - nums[i];
        }

        return res;
    }

}
