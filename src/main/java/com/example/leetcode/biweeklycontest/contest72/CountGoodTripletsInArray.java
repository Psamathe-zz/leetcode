package com.example.leetcode.biweeklycontest.contest72;

/**
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].
 *
 * A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
 *
 * Return the total number of good triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * Output: 1
 * Explanation:
 * There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
 * Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.
 * Example 2:
 *
 * Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * Output: 4
 * Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).
 */
public class CountGoodTripletsInArray {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/count-good-triplets-in-an-array/solution/deng-jie-zhuan-huan-shu-zhuang-shu-zu-by-xmyd/
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTriplets(int[] nums1, int[] nums2) {
        var n = nums1.length;
        var p = new int[n];
        for (var i = 0; i < n; ++i)
            p[nums1[i]] = i;
        var ans = 0L;
        var tree = new int[n + 1];
        for (var i = 1; i < n - 1; ++i) {
            for (var j = p[nums2[i - 1]] + 1; j <= n; j += j & -j) // 将 p[nums2[i-1]]+1 加入树状数组
                ++tree[j];
            var y = p[nums2[i]];
            var less = 0;
            for (var j = y; j > 0; j &= j - 1) // 计算 less
                less += tree[j];
            ans += (long) less * (n - 1 - y - (i - less));
        }
        return ans;
    }
}
