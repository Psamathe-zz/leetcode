package com.example.leetcode.weeklycontest.old.test228;

/**
 * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.
 *
 * You can perform the following operation at most maxOperations times:
 *
 * Take any bag of balls and divide it into two new bags with a positive number of balls.
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
 * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
 *
 * Return the minimum possible penalty after performing the operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [9], maxOperations = 2
 * Output: 3
 * Explanation:
 * - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
 * - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
 * The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
 * Example 2:
 *
 * Input: nums = [2,4,8,2], maxOperations = 4
 * Output: 2
 * Explanation:
 * - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
 * The bag with the most number of balls has 2 balls, so your penalty is 2 an you should return 2.
 * Example 3:
 *
 * Input: nums = [7,17], maxOperations = 2
 * Output: 7
 */
public class MinimumLimit {
    public static void main(String[] args) {

    }

    /**
     *
     作者：robothy
     链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag/solution/er-fen-fa-by-robothy-jmv6/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param maxOperations
     * @return
     */
    public int minimumSize(int[] nums, int maxOperations) {
        int l = 1, r = 1000_000_000, n = nums.length, mid, ans = r;
        while(l<=r){
            mid = (l+r)>>>1;
            if(check(nums, maxOperations, mid)){
                ans = mid;
                r = mid - 1;
            }else l = mid+1;
        }

        return ans;
    }

    boolean check(int[] nums, int max, int ans){
        for(int i=nums.length-1; i>=0; i--){
            // 分两种情况讨论，能够整除和不能够整除
            max -= (nums[i]%ans==0 ? (nums[i]/ans)-1 : (nums[i]/ans));
        }
        return max >= 0;
    }

}
