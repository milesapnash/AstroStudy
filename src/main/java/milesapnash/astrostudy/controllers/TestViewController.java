package milesapnash.astrostudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import milesapnash.astrostudy.MockAPI;
import milesapnash.astrostudy.Question;
import milesapnash.astrostudy.TestData;
import milesapnash.astrostudy.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class TestViewController implements DataController {
  private List<Question> questions;
  private Question currentQuestion;
  private final List<Boolean> results = new ArrayList<>();
  private User currentUser;
  private int currentIndex = 1;

  @FXML
  Label numberLabel;
  @FXML
  Label questionLabel;
  @FXML
  Label answerLabel;
  @FXML
  TextField inputTextField;
  @FXML
  Button skipButton;
  @FXML
  Button nextQuestionButton;

  @Override
  public <T> void parseData(T data) {
    TestData testData = (TestData) data;
    currentUser = testData.user();
    questions = testData.questions();

    if (!questions.isEmpty()){
      setupQuestion();
    }
  }

  public void setupQuestion(){
    setVisibility(true);
    currentQuestion = questions.get(currentIndex - 1);

    questionLabel.setText(MockAPI.getTopicText(currentQuestion));
    answerLabel.setText(currentQuestion.answer());
    inputTextField.setText("");

    numberLabel.setText(currentIndex + "/" + questions.size());

    inputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (levenshteinDistance(inputTextField.getText().toLowerCase(), currentQuestion.answer().toLowerCase()) < 1){
        questionAnswered();
        }
    });
  }

  private void setVisibility(Boolean userInput){
    skipButton.setVisible(userInput);
    inputTextField.setVisible(userInput);
    nextQuestionButton.setVisible(!userInput);
    answerLabel.setVisible(!userInput);
  }

  private int levenshteinDistance(String x, String y){
    final int[][] dp = new int[x.length() + 1][y.length() + 1];
    for (int i = 0; i <= x.length(); i++) {
      for (int j = 0; j <= y.length(); j++) {
        if (i == 0) {
          dp[i][j] = j;
        }
        else if (j == 0) {
          dp[i][j] = i;
        }
        else {
          dp[i][j] = min(dp[i - 1][j - 1]
                  + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
              dp[i - 1][j] + 1,
              dp[i][j - 1] + 1);
        }
      }
    }
    return dp[x.length()][y.length()];
  }

  public static int costOfSubstitution(char a, char b) {
    return a == b ? 0 : 1;
  }

  public static int min(int... numbers) {
    return Arrays.stream(numbers)
        .min().orElse(Integer.MAX_VALUE);
  }


  private void questionAnswered(){
    setVisibility(false);

    answerLabel.setTextFill(Paint.valueOf("#90ee90"));
    results.add(true);
  }

  @FXML
  public void questionSkipped(ActionEvent event){
    setVisibility(false);

    answerLabel.setTextFill(Paint.valueOf("red"));
    results.add(false);
  }

  @FXML
  public void toNext(ActionEvent event){
    if (currentIndex == questions.size()){
      MockAPI.sendResults(results, currentUser);
      switchScene(event, "menu", currentUser);
    } else {
      currentIndex++;
      setupQuestion();
    }
  }
}
