package com.farrellw.hwe.exercises

import org.scalatest.FunSpec

class MiscellaneousTests extends FunSpec{
  describe("Cats age"){
    it("Returns the cat age from a human age when passed a Some"){
      val input: Option[Int] = Some(4)
      val expected: Option[Int] = Some(16)

      val actual = Miscellaneous.catsAge(input)
      assert(expected === actual)
    }

    it("Returns a None when passed a None"){
      val input: Option[Int] = None
      val expected: Option[Int] = None

      val actual = Miscellaneous.catsAge(input)
      assert(expected === actual)
    }

  }

  describe("Minimum - Working with Options"){
    it("Returns the minimum number from a list"){
      val input: List[Option[Int]] = List(Some(1000), None, Some(2000), Some(500), None)
      val expected: Option[Int] =  Some(500)

      val actual = Miscellaneous.minimum(input)
      assert(expected === actual)
    }

    it("Returns None if the list is empty"){
      val input: List[Option[Int]] = List()
      val expected: Option[Int] =  None

      val actual = Miscellaneous.minimum(input)
      assert(expected === actual)
    }

    it("Returns None if the list of options only contains None"){
      val input: List[Option[Int]] = List(None, None, None, None)
      val expected: Option[Int] =  None

      val actual = Miscellaneous.minimum(input)
      assert(expected === actual)
    }
  }

  describe("Acceptable Password - Working with Either"){
    it("Accepts a password with one lowercase and uppercase letter that meets the length requirement."){
      val input = "PASSword123"
      val expected = Right("PASSword123")

      val actual = Miscellaneous.acceptablePassword(input)
      assert(actual === expected)
      assert(actual.isRight)
    }

    it("Returns an exception if the password doesn't contain an uppercase letter"){
      val input = "password123"

      val actual = Miscellaneous.acceptablePassword(input)
      assert(actual.isLeft)
    }

    it("Returns an exception if the password doesn't contain a lowercase letter"){
      val input = "PASSSWORD123"

      val actual = Miscellaneous.acceptablePassword(input)
      assert(actual.isLeft)
    }

    it("Returns an exception if the password isn't of a correct length"){
      val input = "pass"
      val actual = Miscellaneous.acceptablePassword(input)

      assert(actual.isLeft)
    }
  }

  describe("isPalindrome - Working with Strings"){
    it("Returns true if a word is spelled the same forward and backward"){
      val input = "hannah"
      val expected = true

      val actual = Miscellaneous.isPalindrome(input)

      assert(actual === expected)
    }

    it("Returns false if a word is not spelled the same forward and backward"){
      val input = "Hello"
      val expected = false

      val actual = Miscellaneous.isPalindrome(input)

      assert(actual === expected)
    }
  }

}
