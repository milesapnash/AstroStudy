package milesapnash.astrostudy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.Pair;

import java.util.ArrayList;
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
    List<Question> topicQuestions = new ArrayList<>();

    Pair<User, List<Question>> testData = new Pair<>(currentUser, topicQuestions);
    switchScene(event, "test", testData);
  }
}
