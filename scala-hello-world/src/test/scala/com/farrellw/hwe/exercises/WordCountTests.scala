package com.farrellw.hwe.exercises

import org.scalatest.FunSpec

class WordCountTests extends FunSpec {
  describe("Word Count - Working with Strings, Lists, and Maps") {
    it("should be able to split a single sentence into words") {
      val sentence = "one two three"
      val expected = List[String]("one", "two", "three")

      val actual = WordCount.splitSentenceIntoWords(sentence)
      assert(actual === expected)
    }

    it("Given a sentence, should return a map showing how often a word occurred in that sentence ") {
      val sentence = "to seek out new life and new civilizations"
      val expected: Map[String, Int] = Map[String, Int](
        "to" -> 1,
        "seek" -> 1,
        "out" -> 1,
        "new" -> 2,
        "life" -> 1,
        "and" -> 1,
        "civilizations" -> 1
      )

      val actual = WordCount.sentenceWordCount(sentence)
      assert(actual === expected)
    }

    it("Given a list of sentences, should return a map showing count of each word") {
      val sentences: List[String] = List(
        "to explore strange new worlds",
        "to seek out new life and new civilizations",
        "to boldly go where no one has gone before"
      )

      val expected: Map[String, Int] = Map[String, Int](
        "to" -> 3,
        "explore" -> 1,
        "strange" -> 1,
        "new" -> 3,
        "worlds" -> 1,
        "seek" -> 1,
        "out" -> 1,
        "life" -> 1,
        "and" -> 1,
        "civilizations" -> 1,
        "boldly" -> 1,
        "go" -> 1,
        "where" -> 1,
        "no" -> 1,
        "one" -> 1,
        "has" -> 1,
        "gone" -> 1,
        "before" -> 1
      )

      val actual = WordCount.wordCount(sentences)
      assert(actual === expected)
    }
  }
}
