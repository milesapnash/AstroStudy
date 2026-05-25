package milesapnash.astrostudy.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TestViewControllerTest {

  // --- Levenshtein distance ---

  @Test
  void identicalStringsHaveZeroDistance() {
    assertEquals(0, TestViewController.levenshteinDistance("hello", "hello"));
  }

  @Test
  void emptyStringsHaveZeroDistance() {
    assertEquals(0, TestViewController.levenshteinDistance("", ""));
  }

  @Test
  void distanceFromEmptyStringIsLength() {
    assertEquals(5, TestViewController.levenshteinDistance("", "hello"));
    assertEquals(5, TestViewController.levenshteinDistance("hello", ""));
  }

  @Test
  void singleCharacterDifference() {
    assertEquals(1, TestViewController.levenshteinDistance("cat", "bat"));
  }

  @Test
  void singleInsertion() {
    assertEquals(1, TestViewController.levenshteinDistance("cat", "cats"));
  }

  @Test
  void singleDeletion() {
    assertEquals(1, TestViewController.levenshteinDistance("cats", "cat"));
  }

  @ParameterizedTest
  @CsvSource({
      "kitten, sitting, 3",
      "sunday, saturday, 3",
      "paris, paris, 0",
      "paris, pares, 1",
      "london, londn, 1",
  })
  void knownDistances(String a, String b, int expected) {
    assertEquals(expected, TestViewController.levenshteinDistance(a, b));
  }

  @Test
  void distanceIsSymmetric() {
    int d1 = TestViewController.levenshteinDistance("abc", "xyz");
    int d2 = TestViewController.levenshteinDistance("xyz", "abc");
    assertEquals(d1, d2);
  }

  @Test
  void fuzzyMatchAcceptsCloseAnswer() {
    int distance = TestViewController.levenshteinDistance("helsinki", "helsink");
    assertTrue(distance <= 1, "One-character typo should be within tolerance");
  }

  @Test
  void fuzzyMatchRejectsFarAnswer() {
    int distance = TestViewController.levenshteinDistance("helsinki", "berlin");
    assertTrue(distance > 1, "Completely different answer should exceed tolerance");
  }

  // --- costOfSubstitution ---

  @Test
  void sameCharacterCostsZero() {
    assertEquals(0, TestViewController.costOfSubstitution('a', 'a'));
  }

  @Test
  void differentCharacterCostsOne() {
    assertEquals(1, TestViewController.costOfSubstitution('a', 'b'));
  }

  // --- min ---

  @Test
  void minReturnsSingleValue() {
    assertEquals(5, TestViewController.min(5));
  }

  @Test
  void minReturnsSmallestOfThree() {
    assertEquals(1, TestViewController.min(3, 1, 2));
  }

  @Test
  void minHandlesNegatives() {
    assertEquals(-5, TestViewController.min(0, -5, 3));
  }

  @Test
  void minOfEmptyReturnsMaxValue() {
    assertEquals(Integer.MAX_VALUE, TestViewController.min());
  }
}
