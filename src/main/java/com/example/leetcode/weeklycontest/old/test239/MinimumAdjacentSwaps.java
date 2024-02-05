package com.example.leetcode.weeklycontest.old.test239;


/**
 * You are given a string num, representing a large integer, and an integer k.
 *
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num. There can be many wonderful integers. However, we only care about the smallest-valued ones.
 *
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 * Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.
 *
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 * Example 2:
 *
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4th smallest wonderful number is "21111". To get this number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 * Example 3:
 *
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1st smallest wonderful number is "00132". To get this number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 *
 *
 * Constraints:
 *
 * 2 <= num.length <= 1000
 * 1 <= k <= 1000
 * num only consists of digits.
 */
public class MinimumAdjacentSwaps {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/solution/leetcodedi-31ti-de-bian-xing-xian-zhao-c-0b8b/
     */
    int result = 0;
    public int getMinSwaps(String num, int k) {
        int len = num.length();
        int[] intnum = new int[len];
        int[] beginnum = new int[len];//起始数据
        for(int i = 0; i < num.length(); i++){
            intnum[i] = num.charAt(i)-'0';
            beginnum[i] = num.charAt(i)-'0';
        }
        for(int i = 0; i < k; i++){
            intnum = nextPermutation(intnum);
        }
        int[] knum = intnum;//第k个妙数
        for(int i = 0; i < len; i++){
            if(beginnum[i]!=knum[i]){
                int j = i+1;
                while(beginnum[j]!=knum[i]){
                    j++;
                }
                //找到相同数据，开始交换
                while(j != i){
                    swap(beginnum, j-1, j);//只能两两交换
                    result++;
                    j--;
                }
            }
        }
        return result;
    }
    //寻找下一个妙数
    public int[] nextPermutation(int[] nums) {
        int len = nums.length;
        for(int i = len-1; i > 0; i--){
            if(nums[i] > nums[i-1]){
                //nums[i-1]处的元素要进行位置调换
                int j = len-1;
                while(nums[j] <= nums[i-1]) {
                    j--;
                }
                //从i到j都比nums[i-1]大
                //nums[i-1]和nums[j]先调换位置
                swap(nums, i-1, j);
                //反转nums[i-1]之后的所有元素
                j = len-1;
                while(i<j){
                    swap(nums, i++, j--);
                }
                break;
            }
        }
        return nums;
    }
    //交换nums数组第i和第j处的元素
    public void swap(int[] nums, int i, int j){
        int m = nums[i];
        nums[i] = nums[j];
        nums[j] = m;
    }

}
