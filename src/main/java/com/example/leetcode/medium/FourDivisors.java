package com.example.leetcode.medium;

/**
 * Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.
 *
 * If there is no such integer in the array, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [21,4,7]
 * Output: 32
 * Explanation:
 * 21 has 4 divisors: 1, 3, 7, 21
 * 4 has 3 divisors: 1, 2, 4
 * 7 has 2 divisors: 1, 7
 * The answer is the sum of divisors of 21 only.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 */
public class FourDivisors {
    public static void main(String[] args) {
        int[] nums = new int[]{16,4,21,7};
        FourDivisors fourDivisors = new FourDivisors();
        int result = fourDivisors.sumFourDivisorsV1(nums);
        System.out.println(result);
    }

    public int sumFourDivisors(int[] nums) {
        int result = 0;

        for(int value : nums){
            int count = 2;
            int temp = 1 + value;
            for(int i = 2; i <= value/2; i++){
                if( value % i == 0){
                    count++;
                    temp += i;
                }
                if(count > 4)
                    break;
            }
            if(count == 4)
                result += temp;
        }
        return result;

    }

    /**
     * faster solution
     * @param nums
     * @return
     */
    public int sumFourDivisorsV1(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result += getFourDivisors(num);
        }
        return result;

    }

    private int getFourDivisors(int num) {
        int sqrt = (int)Math.ceil(Math.sqrt(num));
        if (sqrt * sqrt == num)
            return 0;

        int incr = num % 2 == 0 ? 1 : 2;
        int res = num + 1;
        boolean divisorFound = false;
        for (int i = 1 + incr ; i < sqrt ; i += incr) {
            if (num % i == 0) {
                if (divisorFound)
                    return 0;
                res += i + num / i;
                divisorFound = true;
            }
        }
        return divisorFound ? res : 0;
    }
}
