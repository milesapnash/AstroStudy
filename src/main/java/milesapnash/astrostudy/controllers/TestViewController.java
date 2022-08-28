package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import milesapnash.astrostudy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class TestViewController extends DataController {
  private List<Question> questions;
  private final List<Boolean> results = new ArrayList<>();
  private final Button[] buttons = new Button[4];
  private boolean chosen = false;
  private User currentUser;
  private int answerIndex = 4;
  private int currentIndex = 1;

  @FXML
  Label numberLabel;
  @FXML
  Label questionLabel;
  @FXML
  Button answerButton1;
  @FXML
  Button answerButton2;
  @FXML
  Button answerButton3;
  @FXML
  Button answerButton4;
  @FXML
  Button progressButton;

  @Override
  public <T> void parseData(T data) {
    TestData testData = (TestData) data;
    currentUser = testData.user();
    questions = testData.questions();
    buttons[0] = answerButton1;
    buttons[1] = answerButton2;
    buttons[2] = answerButton3;
    buttons[3] = answerButton4;

    if (!questions.isEmpty()){
      setupQuestion();
    }
  }

  public void setupQuestion(){
    progressButton.setVisible(false);
    displayQuestion(questions.get(currentIndex - 1));
    numberLabel.setText(currentIndex + "/" + questions.size());
  }

  public void displayQuestion(Question question){
    questionLabel.setText(question.text());
    answerIndex = new Random().nextInt(4);
    int option = 0;

    for (int i = 0; i < 4; i++){
      if (i == answerIndex){
        buttons[i].setText(question.answer());
      } else {
        buttons[i].setText(question.options()[option]);
        option++;
      }
      buttons[i].setStyle("-fx-background-color: #ffffff");
    }
  }

  public void checkAnswer(int index){
    if (!chosen){
      if (index != answerIndex){
        buttons[index].setStyle("-fx-background-color: #ff0000");
      }
      buttons[answerIndex].setStyle("-fx-background-color: #00ff00");
      results.add(index == answerIndex);
      progressButton.setVisible(true);
      chosen = true;
    }
  }

  @FXML
  public void button1Pressed(ActionEvent event){checkAnswer(0);}

  @FXML
  public void button2Pressed(ActionEvent event){checkAnswer(1);}

  @FXML
  public void button3Pressed(ActionEvent event){checkAnswer(2);}

  @FXML
  public void button4Pressed(ActionEvent event){checkAnswer(3);}

  @FXML
  public void toNext(ActionEvent event){
    if (currentIndex == questions.size()){
      MockAPI.sendResults(results, currentUser);
      switchScene(event, "menu", currentUser);
    } else {
      currentIndex++;
      chosen = false;
      setupQuestion();
    }
  }
}
