package com.example.leetcode.weeklycontest.test217;


/**
 * You are given an integer array nums of even length n and an integer limit. In one move, you can replace any integer from nums with another integer between 1 and limit, inclusive.
 *
 * The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number. For example, the array [1,2,3,4] is complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
 *
 * Return the minimum number of moves required to make nums complementary.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined elements are changed).
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
 * Example 2:
 *
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change any number to 3 since 3 > limit.
 * Example 3:
 *
 * Input: nums = [1,2,1,2], limit = 2
 * Output: 0
 * Explanation: nums is already complementary.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 105
 * 1 <= nums[i] <= limit <= 105
 * n is even.
 */
public class MinimumMoves {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-moves-to-make-array-complementary/solution/javaonde-chai-fen-shu-zu-by-liusandao/
     * 有n个数字，即n/2个数对，每个数字的取值范围是1~limit，所以显然一个数对的和的取值范围是2~2 * limit。
     * 我们用一个数组arr[]来记录将所有数对和转化成某一个数需要的操作次数，其中arr[i]表示将所有数对和转化成i需要的次数。
     * 接下来我们举几个例子寻找一下规律：
     * 假设数组是1,3,4,2。limit = 5，则第一个数对为(1,2)
     * 我们找出每一个数对的最大值max和最小值min。
     *
     * 如图中数对，将该数对和转化成3所需要的操作次数显然是0，接下来还要讨论转化次数为1和转化次数为2的情况。
     * 显然转化1次能取到的最小值是min + 1，能取到的最大值是max + limit，那么在这范围之外的就是需要转化次数2次。
     * 所以对于每一对数对：分如下几种情况
     * 在[2, min]这个区间，arr[i] += 2;
     * 在[min + 1, min + max]区间，arr[i] += 1;
     * 在min + max上，arr[i] += 0;
     * 在[min + max + 1, max + limit]区间上，arr[i] += 1;
     * 在[max + limit + 1, limit + limit]区间上，arr[i] += 2;
     *
     *
     * 对于上述的操作，是典型的区间加减，需要用到差分数组，详见leetcode370题。
     * 大体思路是用一个diff[]数组来记录，其中diff[i]表示arr[i] - arr[i - 1]的值。
     * 对于上面的例子，参考下图(其中index是数对和的下标，value是arr数组的值，diff是diff数组的值)
     *
     *
     * 作者：liusandao
     * 链接：https://leetcode-cn.com/problems/minimum-moves-to-make-array-complementary/solution/javaonde-chai-fen-shu-zu-by-liusandao/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param limit
     * @return
     */
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[limit * 2 + 1];
        for (int i = 0; i < nums.length / 2; i++) {
            int max = Math.max(nums[i], nums[nums.length - i - 1]);
            int min = Math.min(nums[i], nums[nums.length - i - 1]);
            if (min == 1) {
                diff[2] += 1;
            } else{
                diff[2] += 2;
                diff[min + 1] -= 1;
            }
            diff[max + min] -= 1;
            if (max + min + 1 < diff.length) {
                diff[max + min + 1] += 1;
            }
            if (max + limit + 1 < diff.length) {
                diff[max + limit + 1] += 1;
            }
        }
        int now = 0, res = nums.length / 2;
        for (int i = 2; i < diff.length; i++) {
            now += diff[i];
            res = Math.min(res, now);
        }
        return res;
    }


    /**
     * easy to understand
     * @param nums
     * @param limit
     * @return
     */
    public int minMovesV1(int[] nums, int limit) {
        int[] sum = new int[1+2*limit+1];
        for(int i = 0, j = nums.length-1; i< j; i++, j--){
            int x = nums[i], y = nums[j];

            int le = Math.min(x, y)+1, ri = Math.max(x, y)+limit;
            sum[1]+= 2;
            sum[le]--;
            sum[x+y]--;
            sum[x+y+1]++;
            sum[ri+1]++;
        }
        int ans = Integer.MAX_VALUE, total = 0;
        for(int i = 1; i<= 2*limit; i++){
            total += sum[i];
            ans = Math.min(ans, total);
        }
        return ans;
    }
}
