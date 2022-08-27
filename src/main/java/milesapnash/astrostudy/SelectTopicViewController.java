package milesapnash.astrostudy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.MockAPI.getTopics;


public class SelectTopicViewController extends DataController {
  private final ObservableList<String> topics =
      FXCollections.observableArrayList(
          getTopics()
      );
  @FXML
  ComboBox<String> topicBox;
  private User currentUser;

  @Override
  <T> void parseData(T data) {
    currentUser = (User) data;
    topicBox.setItems(topics);
  }

  @FXML
  public void startTopicTest(ActionEvent event){
    List<Question> topicQuestions = List.of(Question.exampleQuestion());
    TestData testData = new TestData(currentUser, topicQuestions);
    switchScene(event, "test", testData);
  }
}
