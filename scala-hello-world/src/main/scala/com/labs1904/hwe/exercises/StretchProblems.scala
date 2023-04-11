package com.labs1904.hwe.exercises

import scala.math.Ordering.Implicits.infixOrderingOps

object StretchProblems {

  /*
  Checks if a string is palindrome.
 */
  def isPalindrome(str: String): Boolean = {
    str.reverse == str
  }

  /*
For a given number, return the next largest number that can be created by rearranging that number's digits.
If no larger number can be created, return -1
 */
  def getNextBiggestNumber(num: Integer): Int = {
    if (num == num.toString.sorted.reverse.toInt) -1 else {
      val numHead = num.toString.head
      val numTail = num.toString.tail
      val numTailSortedFilteredHigh = numTail.sorted.filter(_>numHead)
      val numTailSortedFilteredLow = numTail.sorted.filter(_<numHead)
      val numTailSortedFilteredEqual = numTail.sorted.filter(_==numHead)
      var newHead = (numTailSortedFilteredHigh.head.toString).toInt
      if (numHead == newHead) {
        newHead = (numHead).toInt + newHead
      }
      val newTail = numTailSortedFilteredLow + numTailSortedFilteredEqual + numHead + numTailSortedFilteredHigh.tail
      val nextBiggestNumber = (newHead + newTail).toInt
      nextBiggestNumber
    }
  }

}
