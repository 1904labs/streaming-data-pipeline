package com.labs1904.hwe.exercises

import scala.annotation.tailrec
import scala.math.abs

object StretchProblems extends App {

  /*
  Checks if a string is palindrome.
 */
  def isPalindrome(s: String): Boolean = {
    s == s.toList.reverse.mkString("")
  }

  /*
For a given number, return the next largest number that can be created by rearranging that number's digits.
If no larger number can be created, return -1
 */
  def getNextBiggestNumber(i: Integer): Int = {
    //TODO: Implement me!

    /*
    // Example:
    //// Positive numbers
    23541 -> 24135
    starting from the right, find the first case where a right digit (x) is bigger than the digit to its left (y)
      x = 5, y = 3
    find the smallest digit to right of y that is bigger than x (if none, use x); call this z
      z = 4
    put z to the left of y
      24
    sort everything to the right of z smallest to largest
      135
    put them together
      24135

    //// Negative numbers:
    -53124 -> -52431
    still need to implement the code for this
    doesn't work right for negatives with more than one digit
    need to strip the negative sign I think, then add it back in?

     */

    if (abs(i) / 10 < 1) {
      -1
    }
    else if (i.toString.length == 2)
      if (i.toString.reverse.toInt > i)
        i.toString.reverse.toInt
      else
        -1
    else {
      var offset = 0
      val s = i.toString.toSeq

      @tailrec def findInsertIndex(i: Integer): Integer = {
        val indexOfRight = s.length - (1 + offset)
        val indexOfLeft = s.length - (2 + offset)
        if (offset == s.length - 2)
          return -1
        if (s(indexOfRight) > s(indexOfLeft))
          return indexOfLeft
        else
          offset = offset + 1
        findInsertIndex(i)
      }

      val insertIndex = findInsertIndex(i)

      if (insertIndex == -1)
        return -1

      val rightNumsSorted = s.takeRight(i.toString.length - insertIndex).sorted
      val numToMoveIndex = rightNumsSorted.lastIndexOf(s(insertIndex)) + 1
      val numToMove = rightNumsSorted(numToMoveIndex)
      val leftPart = s.take(insertIndex).toString()
      val rightPart = rightNumsSorted.take(numToMoveIndex) + rightNumsSorted.takeRight(rightNumsSorted.length - numToMoveIndex - 1).toString()
      val nextBiggestNumber = leftPart + numToMove + rightPart
      nextBiggestNumber.toInt
    }
  }

//  getNextBiggestNumber(-121)

}
