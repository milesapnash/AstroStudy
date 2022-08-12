package milesapnash.astrostudy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MockAPI {
  private static final Map<String, String> users = Map.of("MilesNash", "Passw0rd");
  private static final Map<String, List<Question>> questions =
      Map.of("Telescopes", List.of(new Question("QUESTION",
          "ANSWER", List.of("Option 1", "Option 2", "Option 3"),
          "Telescopes"), Question.blankQuestion()));
  private static final List<String> topics = List.of("Telescopes");

  public static boolean login(String username, String password){
    return (Objects.equals(password, users.get(username)));
  }

  public static List<Question> getTopicQuestions(String topic){
    return questions.get(topic);
  }

  public static List<String> getTopics(){
    return topics;
  }

}
