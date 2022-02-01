package com.labs1904.hwe.practice

import org.scalatest.FunSpec

class CollectionsTests extends FunSpec {
  describe("Map") {
    it("Uses .map to multiply everything by 3") {
      val input = List(1, 3, 5, 7, 9)
      val expected = List(3, 9, 15, 21, 27)


      val actual = Collections.timesThree(input)
      assert(actual === expected)
    }

    it("Uses .map to uppercase everything") {
      val input = List("Scala", "is", "dope")
      val expected = List("SCALA", "IS", "DOPE")

      val actual = Collections.upper(input)
      assert(expected === actual)
    }
  }

  describe("Filter"){
    it("Should take out negative numbers"){
      val input = List(-2, -1, 0, 1, 2)
      val expected = List(0, 1, 2)

      val actual = Collections.takeOutNegatives(input)
      assert(expected === actual)
    }

    it("keepStringsContainingCar"){
      val input = List("Racecar", "cardinal", "oriole")
      val expected = List("Racecar", "cardinal")

      val actual = Collections.keepStringsContainingCar(input)
      assert(expected === actual)
    }
  }

  describe("Flat Map") {
    it("Uses .flatten to combine nested arrays into one") {
      val input = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
      val expected = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

      val actual = Collections.flatten(input)

      assert(expected === actual)
    }

    it("Uses .flatmap to combine nested arrays into one while mulitplying by three") {
      val input = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
      val expected = List(3, 6, 9, 12, 15, 18, 21, 24, 27)

      val actual = Collections.flattenAndMultiply(input)
      assert(expected === actual)
    }


  }

  describe("Fold Left") {
    it("Uses fold left to sum all digits within a list") {
      val input = List(5, 6, 7, 8, 9)
      val expected = 35

      val actual = Collections.sumDigits(input)
      assert(expected === actual)
    }

    it("Implement the 'map' method using fold left") {
      val input = List(1, 3, 5, 7, 9)
      val expected = List(3, 9, 15, 21, 27)

      def multiplyByThree: Int => Int = (i: Int) => {
        i * 3
      }

      val actual = Collections.mapUsingFoldLeft(input, multiplyByThree)
      assert(expected === actual)
    }

    it("Implement 'flatmap' method using fold left") {
      val input = List(List("Give", "me", "one", "reason"), List("to", "stay", "here"), List("and", "I'll", "turn", "myself", "around"))
      val expected = List("GIVE", "ME", "ONE", "REASON", "TO", "STAY", "HERE", "AND", "I'LL", "TURN", "MYSELF", "AROUND")

      def uppercase: String => String = (s: String) => {
        s.toUpperCase
      }

      val actual = Collections.flatMapUsingFoldLeft(input, uppercase)
      assert(expected === actual)
    }
  }
}