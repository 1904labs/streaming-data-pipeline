package com.labs1904.hwe.exercises

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

     */

    var offset = 0
    val s = i.toString.toSeq

    def findInsertIndex(i: Integer): Integer = {
      val indexOfRight = s.length - (1 + offset)
      val indexOfLeft = s.length - (2 + offset)
      val rightIsBigger = s(indexOfRight) > s(indexOfLeft)
      if (rightIsBigger)
        return indexOfLeft
      else
        offset = offset + 1
        findInsertIndex(i)
    }

    val insertIndex = findInsertIndex(i)
    val numsSorted = s.takeRight(i.toString.length - insertIndex).sorted
    val zIndex = numsSorted.lastIndexOf(s(insertIndex) + 2)
    val z = s(zIndex)
    val sReordered = s.take(insertIndex).toString() + z + numsSorted.take(zIndex - 1) + numsSorted.drop(zIndex)
    println(sReordered.toInt)
    sReordered.toInt
  }

    // To Do:
    // test various inputs
    // add in -1, etc

  getNextBiggestNumber(23541)

}
