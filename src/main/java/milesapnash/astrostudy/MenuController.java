package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MenuController extends DataController {

  @FXML
  private Label userLabel;
  private User currentUser;

  @FXML
  void toStart(ActionEvent event) {
    switchScene(event, "main");
  }

  @Override
  <T> void parseData(T data) {
    currentUser = (User) data;
    userLabel.setText("Welcome, " + currentUser.toString());
  }

  @FXML
  void toCurrentQuestions(ActionEvent event){
    switchScene(event, "current-questions", currentUser);
  }

  @FXML
  void toTopicTest(ActionEvent event){
      switchScene(event, "select-topic", currentUser);
  }

  @FXML
  void startQuickTest(ActionEvent event){
    List<Question> quickQuestions = new ArrayList<>();
    Pair<User, List<Question>> testData = new Pair<>(currentUser, quickQuestions);
    switchScene(event, "test", testData);
  }
}
