package milesapnash.astrostudy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MockAPITest {

  @BeforeAll
  static void setup() {
    MockAPI.buildQuestionMap();
  }

  // --- Authentication ---

  @Test
  void loginSucceedsWithDefaultCredentials() {
    int result = MockAPI.login("username", "password");
    assertEquals(0, result);
  }

  @Test
  void loginFailsWithWrongPassword() {
    int result = MockAPI.login("username", "wrong");
    assertEquals(-1, result);
  }

  @Test
  void loginFailsWithUnknownUser() {
    int result = MockAPI.login("nonexistent", "password");
    assertEquals(-1, result);
  }

  @Test
  void loginFailsWithNullUsername() {
    int result = MockAPI.login(null, "password");
    assertEquals(-1, result);
  }

  @Test
  void loginFailsWithNullPassword() {
    int result = MockAPI.login("username", null);
    assertEquals(-1, result);
  }

  @Test
  void registerNewUserSucceeds() {
    assertTrue(MockAPI.register("newuser_" + System.nanoTime(), "pass123"));
  }

  @Test
  void registerDuplicateUserFails() {
    String user = "dupuser_" + System.nanoTime();
    assertTrue(MockAPI.register(user, "pass1"));
    assertFalse(MockAPI.register(user, "pass2"));
  }

  @Test
  void registeredUserCanLogin() {
    String user = "logintest_" + System.nanoTime();
    MockAPI.register(user, "mypass");
    assertEquals(0, MockAPI.login(user, "mypass"));
  }

  // --- Question retrieval ---

  @Test
  void buildQuestionMapLoadsTopics() {
    List<String> topics = MockAPI.getTopics();
    assertNotNull(topics);
    assertFalse(topics.isEmpty());
    assertTrue(topics.contains("capitals"));
  }

  @Test
  void getAllTopicQuestionsReturnsQuestions() {
    List<Question> qs = MockAPI.getAllTopicQuestions("capitals");
    assertNotNull(qs);
    assertFalse(qs.isEmpty());
  }

  @Test
  void getAllTopicQuestionsReturnsNullForUnknownTopic() {
    assertNull(MockAPI.getAllTopicQuestions("nonexistent_topic"));
  }

  @Test
  void getTopicQuestionsRespectsMaxLimit() {
    List<Question> qs = MockAPI.getTopicQuestions("capitals", 5);
    assertNotNull(qs);
    assertTrue(qs.size() <= 5);
    assertFalse(qs.isEmpty());
  }

  @Test
  void getTopicQuestionsReturnsEmptyForUnknownTopic() {
    List<Question> qs = MockAPI.getTopicQuestions("nonexistent_topic", 5);
    assertNotNull(qs);
    assertTrue(qs.isEmpty());
  }

  @Test
  void getTopicQuestionsReturnsAllWhenMaxExceedsTotal() {
    List<Question> all = MockAPI.getAllTopicQuestions("capitals");
    List<Question> qs = MockAPI.getTopicQuestions("capitals", 10000);
    assertEquals(all.size(), qs.size());
  }

  @Test
  void getRandomQuestionsRespectsMaxLimit() {
    List<Question> qs = MockAPI.getRandomQuestions(3);
    assertNotNull(qs);
    assertTrue(qs.size() <= 3);
  }

  @Test
  void getRandomQuestionsContainsQuestionsFromMultipleTopics() {
    List<Question> qs = MockAPI.getRandomQuestions(200);
    long distinctTopics = qs.stream().map(Question::topic).distinct().count();
    assertTrue(distinctTopics > 1, "Expected questions from multiple topics");
  }

  @Test
  void questionsHaveValidFields() {
    List<Question> qs = MockAPI.getAllTopicQuestions("capitals");
    for (Question q : qs) {
      assertNotNull(q.text());
      assertNotNull(q.answer());
      assertFalse(q.text().isBlank());
      assertFalse(q.answer().isBlank());
      assertEquals("capitals", q.topic());
    }
  }
}
