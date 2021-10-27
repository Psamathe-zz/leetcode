package com.example.leetcode.challenge.test2021.august.week2

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * Example 2:
 *
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * Example 3:
 *
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 *
 * "11011"
 *
 */
object FlipStringToMonotoneIncreasing extends App {

  println(minFlipsMonoIncr("11011"))

  def minFlipsMonoIncr(s: String): Int = {
    var length = s.size
    var left: Array[Int] = new Array[Int](length+1)
    var right: Array[Int] = new Array[Int](length+1)
    var count1 = 0
    var count0 = 0
    for( index <- 0 until length){
      if(s(index).asDigit == 1){
        count1 += 1
      }
      left(index+1) = count1

      if(s(length - 1 - index).asDigit == 0){
        count0 += 1
      }
      right(length - 1 - index) = count0
    }
    var res = Integer.MAX_VALUE
    for( index <- 0 to length){
      res = Math.min(res, Math.abs(left(index) + right(index)))
    }
    res
  }

}
