package com.labs1904.hwe

import org.scalatest.FunSpec

class AppTests extends FunSpec {
  describe("greeting") {
    it("should return the name prepended with the greeting") {
      val name = "Tim"
      val expected = "Hello Tim"

      val actual = App.greeting(name)
      assert(actual === expected)
    }
  }
}