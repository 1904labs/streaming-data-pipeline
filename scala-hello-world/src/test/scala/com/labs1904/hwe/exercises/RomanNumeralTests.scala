package com.labs1904.hwe.exercises

import org.scalatest.FunSpec

// NOTE - For simplicity sake, solve assuming old roman numeral style. 4 is IIII and NOT IV
class RomanNumeralTests extends FunSpec {
  describe("Converts roman numerals to integers - Working with Options"){
    it("Converts a single roman numeral to int"){
      val input = "X"
      val expected = Some(10)
      val actual = RomanNumeral.convertRomanToInt(input)

      assert(actual === expected)
    }

    it("Returns None if the roman numeral is invalid"){
      val input = "R"
      val expected = None
      val actual = RomanNumeral.convertRomanToInt(input)

      assert(actual === expected)
    }

    // NOTE - For simplicity sake, solve assuming old roman numeral style. 4 is IIII and NOT IV
    it("Converts a multi-letter roman numeral to a numeral"){
      val input = "CXX"
      val expected = Some(120)
      val actual = RomanNumeral.convertRomanToInt(input)

      assert(actual === expected)
    }

    it("Returns None if the roman numeral is not valid"){
      val input = "AXV"
      val expected = None
      val actual = RomanNumeral.convertRomanToInt(input)

      assert(actual === expected)
    }
  }

  describe("Converts a roman numeral to an integer - Working with Either"){
    it("Returns a Right[Int] when a valid roman numeral is passed in"){
      val input = "XV"
      val expected = Right(15)
      val actual = RomanNumeral.convertRomanToIntEither(input)

      assert(actual.isRight)
      assert(actual === expected)

    }

    it("Returns a Left[Exception] when an invalid roman numeral is passed in"){
      val input = "CXA"
      val actual = RomanNumeral.convertRomanToIntEither(input)

      assert(actual.isLeft)
    }
  }

  describe("Converts a number to a roman numeral - Working with Strings, Tuples, Numbers"){
    it("Converts a single roman numeral correctly"){
      val input = 5
      val expected = Some("V")

      val actual = RomanNumeral.convertIntToRoman(input)
      assert(actual === expected)
    }

    it("Converts a complex roman numeral"){
      val input = 1125
      val expected = Some("MCXXV")

      val actual = RomanNumeral.convertIntToRoman(input)
      assert(actual === expected)
    }
  }
}
