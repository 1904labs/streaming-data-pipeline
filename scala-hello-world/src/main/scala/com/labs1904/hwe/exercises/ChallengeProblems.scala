package com.labs1904.hwe.exercises

import scala.collection.mutable.ListBuffer

object ChallengeProblems {
  /*
    1. Define a function that takes in a String and returns that string with no changes
    Write your function below this comment - refer to Challenge Tests to have your test pass
    Params - String
    Returns - String
  */

  def sameString(s: String): String = {
    s
  }


  /*
  2. Write a function that returns "Hello World!" and takes in nothing as a parameter
  Params - None
  Returns - String
   */

  def helloWorld(): String = {
    "Hello World!"
  }

  /*
  3. Write a function that takes in a list and returns the total size of the list
  -Note - Use the .size method
  Params - List[Int]
  Returns - Int
   */

  def listSize(list: List[Int]): Int = {
    list.size
  }

  /*
  4. Write a function that takes in an int and adds an int that you create within the function and returns the addition of the two together
  Note - Your variable must be a val and must be equal to 25
  Params - Int
  Returns - Int
   */

  def sumInts(n: Int): Int = {
    n + 25
  }


  /*
   5. Write a function that takes in a list of strings, and return a list of strings where every letter is capitalized
   Hint - you can use .map here
   Params - List[String]
   Returns - List[String]
*/

  def upper(list: List[String]): List[String] = {
    list.map(x => x.toUpperCase())
  }

  /*
  6. Write a function that returns a new list, where only elements of the list passed in that are 0 or positive numbers are kept.
  Params - List[Int]
  Returns - List[Int]
   */

  def filterNegatives(list: List[Int]): List[Int] = {
    val filtered = new ListBuffer[Int]();
    list.foreach(x => if (x>=0){filtered += x})
    filtered.toList
  }

  /*
  7. Returns a new list, where only the elements passed in containing "car" are kept to the new list.
  Params - List[String]
  Returns - List[String]
 */

  def containsCar(list: List[String]): List[String] = {
    val filtered = new ListBuffer[String]();
    list.foreach(x => if (x.contains("car")){filtered += x})
    filtered.toList
  }

  /*
    8. Returns the sum of all numbers passed in.
    Params - List[Int]
    Returns - Int
   */

  def sumList(list: List[Int]): Int = {
    var sum = 0;
    list.foreach(x => sum = sum + x);
    sum
  }

  /*
  9. Write a function that takes in an integer with a cats age, and return the human age equivalent.
    A human year is equivalent to 4 cat years
    Params - Int
    Returns - Int
   */

  def catsAge(age: Int): Int = {
    age * 4
  }

  /*
  10. Same question as #9, but this time you are given a Option[Int]
    If an int is provided, returns a cats age for the human's age equivalent.
    If None is provided, return None
    A humanYear is equivalent to four catYears
    -Params - Option[Int]
    -Returns - Option[Int]
 */

  def catsAgeOption(age: Option[Int]): Option[Int] = {
    if (age.isDefined){
      val v1 = age.getOrElse(0) * 4;
      Some(v1)
    } else None
  }

  /*
  11. Write a function that takes in a list of ints, and return the minimum of the ints provided
  Params - List
  Returns - Int
   */

  def minimum(list: List[Int]): Int = {
    list.min
  }

  /*
  12. Same as question 11, but this time you are given a list of Option[Ints], returns the minimum of the Ints provided.
  If no ints are provided, return None.
 */

  def minimumOption(list: List[Option[Int]]): Option[Int] = {
    val filtered = new ListBuffer[Int]();
    list.foreach(x => if (x.isDefined){filtered += x.getOrElse(0)})
    if (filtered.isEmpty) {
      None
    } else Some(filtered.toList.min)
  }







}
