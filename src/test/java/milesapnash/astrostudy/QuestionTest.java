package milesapnash.astrostudy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

  @Test
  void blankQuestionHasEmptyFields() {
    Question q = Question.blankQuestion();
    assertEquals("", q.text());
    assertEquals("", q.answer());
    assertEquals("", q.topic());
  }

  @Test
  void exampleQuestionHasExpectedValues() {
    Question q = Question.exampleQuestion();
    assertNotNull(q.text());
    assertNotNull(q.answer());
    assertNotNull(q.topic());
    assertFalse(q.text().isEmpty());
  }

  @Test
  void getTopicTextFormatsCapitalsAsSentence() {
    Question q = new Question("France", "Paris", "capitals");
    assertEquals("What is the capital of France?", q.getTopicText());
  }

  @Test
  void getTopicTextReturnsRawTextForOtherTopics() {
    Question q = new Question("What year did WW2 end?", "1945", "history");
    assertEquals("What year did WW2 end?", q.getTopicText());
  }

  @Test
  void equalsBasedOnTextOnly() {
    Question q1 = new Question("same text", "answer1", "topic1");
    Question q2 = new Question("same text", "answer2", "topic2");
    assertEquals(q1, q2);
  }

  @Test
  void notEqualWhenTextDiffers() {
    Question q1 = new Question("text1", "answer", "topic");
    Question q2 = new Question("text2", "answer", "topic");
    assertNotEquals(q1, q2);
  }

  @Test
  void hashCodeConsistentWithEquals() {
    Question q1 = new Question("same text", "answer1", "topic1");
    Question q2 = new Question("same text", "answer2", "topic2");
    assertEquals(q1.hashCode(), q2.hashCode());
  }

  @Test
  void equalsHandlesNull() {
    Question q = new Question("text", "answer", "topic");
    assertNotEquals(null, q);
  }

  @Test
  void equalsHandlesSameInstance() {
    Question q = new Question("text", "answer", "topic");
    assertEquals(q, q);
  }
}
