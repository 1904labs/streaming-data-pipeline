package com.labs1904.hwe.exercises

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
    val s = i.toString.split("");
    val finalIndex = s.length - 1;
    val nextFinalIndex = s.length - 2;

    for (Int: j = 0; j <= s.length; j++)

  }

}
