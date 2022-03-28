package com.labs1904.hwe.exercises

import org.scalatest.FunSpec

class StretchProblemsTests extends FunSpec {

  describe("isPalindrome - Working with Strings"){
    it("Returns true if a word is spelled the same forward and backward"){
      val input = "hannah"
      val expected = true

      val actual = StretchProblems.isPalindrome(input)

      assert(actual === expected)
    }

    it("Returns false if a word is not spelled the same forward and backward"){
      val input = "Hello"
      val expected = false

      val actual = StretchProblems.isPalindrome(input)

      assert(actual === expected)
    }
  }
  describe("Testing NextBiggestNumber - Working with numbers, strings, and lists") {
    it("should return the next biggest number for straightforward examples") {
      assert(StretchProblems.getNextBiggestNumber(12) === 21)
      assert(StretchProblems.getNextBiggestNumber(123) === 132)
      assert(StretchProblems.getNextBiggestNumber(67809) === 67890)
    }

    it("should return -1 for straightforward examples") {
      assert(StretchProblems.getNextBiggestNumber(21) === -1)
      assert(StretchProblems.getNextBiggestNumber(54321) === -1)
    }

    it("should work for 52210") {
      assert(StretchProblems.getNextBiggestNumber(52210) === -1)
    }

    it("should work for 95701") {
      assert(StretchProblems.getNextBiggestNumber(95701) === 95710)
    }

    it("should work for 71305") {
      assert(StretchProblems.getNextBiggestNumber(71305) === 71350)
    }

    it("should work for 6358") {
      assert(StretchProblems.getNextBiggestNumber(6358) === 6385)
    }

    it("should work for 25437") {
      assert(StretchProblems.getNextBiggestNumber(25437) === 25473)
    }

    it("should work for 49893") {
      assert(StretchProblems.getNextBiggestNumber(49893) === 49938)
    }

    it("should work for 76778") {
      assert(StretchProblems.getNextBiggestNumber(76778) === 76787)
    }

    it("should work for 2372") {
      assert(StretchProblems.getNextBiggestNumber(2372) === 2723)
    }

    it("should work for 45071") {
      assert(StretchProblems.getNextBiggestNumber(45071) === 45107)
    }

    it("should work for 31233") {
      assert(StretchProblems.getNextBiggestNumber(31233) === 31323)
    }

    it("should work for 50401") {
      assert(StretchProblems.getNextBiggestNumber(50401) === 50410)
    }

    it("should work for 57067") {
      assert(StretchProblems.getNextBiggestNumber(57067) === 57076)
    }

    it("should work for 40272") {
      assert(StretchProblems.getNextBiggestNumber(40272) === 40722)
    }

    it("should work for 54998") {
      assert(StretchProblems.getNextBiggestNumber(54998) === 58499)
    }

    it("should work for 22437") {
      assert(StretchProblems.getNextBiggestNumber(22437) === 22473)
    }
  }

}
