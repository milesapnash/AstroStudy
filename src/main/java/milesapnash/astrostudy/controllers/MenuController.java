package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import milesapnash.astrostudy.MockAPI;
import milesapnash.astrostudy.Question;
import milesapnash.astrostudy.TestData;
import milesapnash.astrostudy.User;

import java.util.List;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MenuController implements DataController {
  private User currentUser;

  @FXML
  private Label userLabel;

  @Override
  public <T> void parseData(T data) {
    currentUser = (User) data;
    userLabel.setText("Welcome, " + currentUser.toString());
    MockAPI.buildQuestionMap();
  }

  @FXML
  void toStart(ActionEvent event) {
    switchScene(event, "main");
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
    List<Question> quickQuestions = MockAPI.getRandomQuestions(10);
    TestData testData = new TestData(currentUser, quickQuestions);
    switchScene(event, "input-test", testData);
  }
}
