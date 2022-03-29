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
    0
    /*
    // figure out pattern with numbers
    111 > -1
    112 > 121
    113 > 131
    212 > 221
    213 > 231
    489236592 > 489236952

    // figure out pattern with words:
    // start from the back
    // I'll refer to the last digit's place as 1, the second to last as 2, etc.
    // swap the last two digits (1 and 2)
    // if number is bigger, return it
    // if not, swap 2 and 3 (leaving 1 and 2 unswapped)
    // if number is bigger, return it
    // etc.
    // use recursion

     */

    val seq = i.toString.toSeq
    val right = seq(seq.length - 1)
    val left = seq(seq.length - 2)
    val seqSwapped = seq.take(seq.length - 2) + right.toString + left.toString
    if (seqSwapped.mkString("").toInt > seq.mkString("").toInt) {
      println(seqSwapped.mkString("").toInt)
      seqSwapped.mkString("").toInt
    } else {
      println(-1)
      -1
    }

    // To Do:
    // only works for last two digits - need to make it recursive
    // very ugly right now - figure out more elegant way

  }

  getNextBiggestNumber(548413)

}
