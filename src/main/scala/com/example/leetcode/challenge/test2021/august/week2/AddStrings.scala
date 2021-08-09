package com.example.leetcode.challenge.test2021.august.week2

object AddStrings extends App {

  println(addStrings("11","123"))
  def addStrings(num1: String, num2: String): String = {
    val sb = new StringBuilder()
    val length1 = num1.length
    val length2 = num2.length
    var val1 = 0
    var val2 = 0
    var value = 0
    var next = 0
    for( index <- 0 until Math.max(length1,length2) ){
      val1 = if(index < length1) num1(length1 - 1 - index).asDigit  else 0
      val2 = if(index < length2) num2(length2 - 1 - index).asDigit else 0
      value = (next + val1 + val2) % 10
      next = (next + val1 + val2) / 10
      sb.append(value)
    }
    if(next > 0) sb.append(next)
    sb.reverse.toString()
  }


  def addStringsV1(num1: String, num2: String): String = {
    val outputString = new StringBuilder
    var (num1Index, num2Index) = (num1.length-1, num2.length-1)
    var result, carryOver = 0

    while (num1Index >= 0 || num2Index >= 0) {
      val x = if (num1Index < 0) 0 else num1(num1Index).asDigit
      val y = if (num2Index < 0) 0 else num2(num2Index).asDigit

      result = (x + y + carryOver) % 10 //If addition gives a two digit number, get the last digit for 'result'
      outputString += result.toString.head //append the 'result' value to outputString
      carryOver = (x + y + carryOver) / 10 //If addition gives a two digit number, get the first digit for 'carryOver'

      num1Index -= 1
      num2Index -= 1
    }

    if (carryOver > 0) //If 'carryOver' is left after coming out of the loop, add it as well
      outputString += carryOver.toString.head

    outputString.reverse.toString //reverse the string and return
  }
}
