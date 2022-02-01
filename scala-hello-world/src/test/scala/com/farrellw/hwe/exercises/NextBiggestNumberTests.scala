package com.farrellw.hwe.exercises

import org.scalatest.FunSpec

class NextBiggestNumberTests extends FunSpec{
  describe("Testing NextBiggestNumber - Working with numbers, strings, and lists") {
    it("should return the next biggest number for straightforward examples") {
      assert(NextBiggestNumber.getNextBiggestNumber(12) === 21)
      assert(NextBiggestNumber.getNextBiggestNumber(123) === 132)
      assert(NextBiggestNumber.getNextBiggestNumber(67809) === 67890)
    }

    it("should return -1 for straightforward examples") {
      assert(NextBiggestNumber.getNextBiggestNumber(21) === -1)
      assert(NextBiggestNumber.getNextBiggestNumber(54321) === -1)
    }

    it("should work for 52210") {
      assert(NextBiggestNumber.getNextBiggestNumber(52210) === -1)
    }

    it("should work for 95701") {
      assert(NextBiggestNumber.getNextBiggestNumber(95701) === 95710)
    }

    it("should work for 71305") {
      assert(NextBiggestNumber.getNextBiggestNumber(71305) === 71350)
    }

    it("should work for 6358") {
      assert(NextBiggestNumber.getNextBiggestNumber(6358) === 6385)
    }

    it("should work for 25437") {
      assert(NextBiggestNumber.getNextBiggestNumber(25437) === 25473)
    }

    it("should work for 49893") {
      assert(NextBiggestNumber.getNextBiggestNumber(49893) === 49938)
    }

    it("should work for 76778") {
      assert(NextBiggestNumber.getNextBiggestNumber(76778) === 76787)
    }

    it("should work for 2372") {
      assert(NextBiggestNumber.getNextBiggestNumber(2372) === 2723)
    }

    it("should work for 45071") {
      assert(NextBiggestNumber.getNextBiggestNumber(45071) === 45107)
    }

    it("should work for 31233") {
      assert(NextBiggestNumber.getNextBiggestNumber(31233) === 31323)
    }

    it("should work for 50401") {
      assert(NextBiggestNumber.getNextBiggestNumber(50401) === 50410)
    }

    it("should work for 57067") {
      assert(NextBiggestNumber.getNextBiggestNumber(57067) === 57076)
    }

    it("should work for 40272") {
      assert(NextBiggestNumber.getNextBiggestNumber(40272) === 40722)
    }

    it("should work for 54998") {
      assert(NextBiggestNumber.getNextBiggestNumber(54998) === 58499)
    }

    it("should work for 22437") {
      assert(NextBiggestNumber.getNextBiggestNumber(22437) === 22473)
    }
  }
}
