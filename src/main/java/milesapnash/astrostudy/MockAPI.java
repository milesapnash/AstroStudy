package milesapnash.astrostudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MockAPI {
  private static final HashMap<String, String> users = new HashMap<>(Map.of("username", "password"));
  private static final HashMap<String, List<Question>> questions = new HashMap<>();

  public static int login(String username, String password){
    final int userID = 0;
    if (Objects.equals(password, users.get(username))){
      return userID;
    } else {
      return -1;
    }
  }

  public static boolean register(String username, String password){
    if (users.get(username) == null){
      users.put(username, password);
      return true;
    } else {
      return false;
    }
  }

  public static void buildQuestionMap(){
    final String directoryName = "src/main/resources/milesapnash/astrostudy/questions/";
    final String splitOn = ",";
    final File[] topicFiles = new File(directoryName).listFiles();

    if (topicFiles != null){
      for (final File questionsFile : topicFiles) {
        final String fileName = questionsFile.getName();
        final String topic = fileName.substring(0, fileName.lastIndexOf('.'));
        final List<Question> topicQuestions = new ArrayList<>();

        try {
          String line;
          BufferedReader br = new BufferedReader(new FileReader(directoryName + fileName));
          while ((line = br.readLine()) != null){
            String[] questionText = line.split(splitOn);
            Question q = new Question(questionText[0], questionText[1], topic);
            topicQuestions.add(q);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }

        questions.put(topic, topicQuestions);
      }
    }
  }

  public static List<Question> getAllTopicQuestions(String topic){
    return questions.get(topic);
  }

  public static List<String> getTopics(){
    return questions.keySet().stream().toList();
  }

  public static void sendResults(List<Boolean> results, User user){}

  public static void sendResetRequest(String email){}

  public static void deleteQuestion(String topic, int id){
   // return questions.get(topic).remove(new Question(null,null,null,topic,id));
  }

  public static void addQuestion(Question question){
   // return questions.get(question.topic()).add(question);
  }

  public static void editQuestion(Question question){
//    ArrayList<Question> topicQuestions = questions.get(question.topic());
//    topicQuestions.remove(new Question(null,null,null,question.topic(),question.id()));
//    topicQuestions.add(question);
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

  public static String getTopicText(Question question){
    if (Objects.equals(question.topic(), "capitals")){
      return "What is the capital of " + question.text() + "?";
    }
    return question.text();
  }
}


// TODO:
//  REGISTER: Grey button until all fields valid - red fields if otherwise
//  QUICKTEST: Select 10 random qs from bank
//  TEST: Add timer? Add summary screen? Add user results?
//  VIEWQUESTIONS: Capitalise topic box + make Q/A GUI better
//  EDITQUESTION: Capitalise topic box + reimplement functionality
