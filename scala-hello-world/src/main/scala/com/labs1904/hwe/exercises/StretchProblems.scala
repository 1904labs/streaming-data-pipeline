package com.labs1904.hwe.exercises

import scala.util.control.Breaks.{break, breakable}

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
    // figure out pattern with numbers
    111 > -1
    112 > 121
    113 > 131
    212 > 221
    213 > 231
    489236592 > 489236952
    3421 > 4123

    // figure out pattern with words:
    // start from the back
    // I'll refer to the last digit's place as 1, the second to last as 2, etc.
    // swap the last two digits (1 and 2)
    // if number is bigger, return it
    // if not, swap 2 and 3 (leaving 1 and 2 unswapped)
    // if number is bigger, return it
    // etc.
    // use recursion
    // actually...incorrect algorithm. If I enter 3421, I want to return 4123, not 4321. Rethink.
     */

    // To Do:
    // fix algorithm
    // add in -1...but this may change the logic a fair bit..?
    // very ugly right now - figure out more elegant way

    def swap(offset: Integer, i: Integer): Integer = {
      val seq = i.toString.toSeq
      val right = seq(seq.length - (1 + offset))
      val left = seq(seq.length - (2 + offset))
      val seqSwapped = seq.take(seq.length - (2 + offset)) + right.toString + left.toString + seq.takeRight(offset)
      seqSwapped.mkString("").toInt
    }

    var offset = 0
    while (swap(offset = offset, i = i) < i) {
      offset += 1
    }
    println(swap(offset = offset, i = i))
    swap(offset = offset, i = i)

  }

  getNextBiggestNumber(3421)

}
