package com.labs1904.hwe.exercises

import scala.collection.mutable.ListBuffer

object StretchProblems {

  /*
  Checks if a string is palindrome.
 */
  def isPalindrome(s: String): Boolean = {
    s == s.reverse
  }

  /*
For a given number, return the next largest number that can be created by rearranging that number's digits.
If no larger number can be created, return -1
 */
  def getNextBiggestNumber(i: Integer): Int = {
    val iAsList = i.toString.map(_.asDigit).toList;
    val length = iAsList.length;

    def negativeOneChecker(list: List[Int]): Int = {
      if (list.head >= list(1)){

      }
    }

  }




}
