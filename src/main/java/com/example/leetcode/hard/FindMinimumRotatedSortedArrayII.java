package com.example.leetcode.hard;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class FindMinimumRotatedSortedArrayII {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4040438.html
     *
     * 当数组中存在大量的重复数字时，就会破坏二分查找法的机制，将无法取得 O(lgn) 的时间复杂度，又将会回到简单粗暴的 O(n)，
     * 比如这两种情况：{2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2} 和 {2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2}，
     * 可以发现，当第一个数字和最后一个数字，还有中间那个数字全部相等的时候，二分查找法就崩溃了，因为它无法判断到底该去左半边还是右半边。
     * 这种情况下，将右指针左移一位（或者将左指针右移一位），略过一个相同数字，这对结果不会产生影响，因为只是去掉了一个相同的，
     * 然后对剩余的部分继续用二分查找法，在最坏的情况下，比如数组所有元素都相同，时间复杂度会升到 O(n)，参见代码如下：
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0, right = (int)nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
            else
                --right;
        }
        return nums[right];
    }
}
