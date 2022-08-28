package milesapnash.astrostudy;

import java.util.*;

public class MockAPI {
  private static final Map<String, String> users = Map.of("MilesNash", "Passw0rd");
  private static final Map<String, ArrayList<Question>> questions =
      Map.of(
          "Telescopes", new ArrayList<>(List.of(
              new Question("Which EM wave has the greatest frequency?","Gamma", new String[]{"Radio", "Ultraviolet", "Infrared"}, "Telescopes", 1),
              new Question("Which of these are lenses NOT used for?","Mirrors", new String[]{"Glasses", "Cameras", "Projectors"},"Telescopes", 2))),
          "Star Classification", new ArrayList<>(List.of(
              new Question("StarC Q","A", new String[]{"B", "C", "D"}, "Star Classification", 3)))
      );

  public static boolean login(String username, String password){
    return (Objects.equals(password, users.get(username)));
  }

  public static ArrayList<Question> getAllTopicQuestions(String topic){
    return questions.get(topic);
  }

  public static List<String> getTopics(){
    return questions.keySet().stream().toList();
  }

  public static void sendResults(List<Boolean> results, User user){}

  public static void sendResetRequest(String email){}

  public static boolean deleteQuestion(String topic, int id){
    return questions.get(topic).remove(new Question(null,null,null,topic,id));
  }

  public static boolean addQuestion(Question question){
    return questions.get(question.topic()).add(question);
  }

  public static void editQuestion(Question question){
    ArrayList<Question> topicQuestions = questions.get(question.topic());
    topicQuestions.remove(new Question(null,null,null,question.topic(),question.id()));
    topicQuestions.add(question);
  }

  public static List<Question> getTopicQuestions(String topic, int maxQs){
    List<Question> topicQuestions = getAllTopicQuestions(topic);
    Collections.shuffle(topicQuestions);
    if (topicQuestions.size() > maxQs){
      return topicQuestions.stream().limit(maxQs).toList();
    } else {
      return topicQuestions;
    }
  }
}
