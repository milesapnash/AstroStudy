package milesapnash.astrostudy.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import milesapnash.astrostudy.*;

import java.util.Objects;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.MockAPI.getTopics;

public class EditQuestionController extends DataController {

  private final ObservableList<String> topics =
      FXCollections.observableArrayList(
          getTopics()
      );
  private User currentUser;
  private Question currentQuestion;

  @FXML
  ComboBox<String> topicBox;
  @FXML
  TextField questionField;
  @FXML
  TextField answerField;
  @FXML
  TextField option1Field;
  @FXML
  TextField option2Field;
  @FXML
  TextField option3Field;

  @Override
  public <A> void parseData(A data) {
    TopicData topicData = (TopicData) data;
    currentUser = topicData.user();
    currentQuestion = topicData.question();

    topicBox.setItems(topics);
    topicBox.getSelectionModel().select(topicData.topic());

    setListener(questionField, currentQuestion.text());
    setListener(answerField, currentQuestion.answer());
    setListener(option1Field, currentQuestion.options()[0]);
    setListener(option2Field, currentQuestion.options()[1]);
    setListener(option3Field, currentQuestion.options()[2]);
  }

  private void setListener(TextField field, String original){
    field.setText(original);
    field.textProperty().addListener((observable, oldValue, newValue) -> {
      String text = field.getText();
      if (text.isEmpty()){
        field.setStyle("-fx-border-color: #ff0000");
      } else if (Objects.equals(text, original)) {
        field.setStyle("-fx-border-color: #ffffff");
      } else {
        field.setStyle("-fx-border-color: #ff8800");
      }
    });
  }

  @FXML
  public void toQuestions(ActionEvent event){
    switchScene(event, "current-questions", currentUser);
  }

  @FXML
  public void attemptUpdate(ActionEvent event){
    if (!(questionField.getText().isEmpty() || answerField.getText().isEmpty() || option1Field.getText().isEmpty() || option2Field.getText().isEmpty() || option3Field.getText().isEmpty())){
      if (currentQuestion.id() == -1){
        MockAPI.addQuestion(new Question(questionField.getText(), answerField.getText(), new String[]{option1Field.getText(), option2Field.getText(), option3Field.getText()},topicBox.getSelectionModel().getSelectedItem(), -1));
      } else {
        if (!Objects.equals(questionField.getText(), currentQuestion.text()) || !Objects.equals(answerField.getText(), currentQuestion.answer()) || !Objects.equals(option1Field.getText(), currentQuestion.options()[0])
         || !Objects.equals(option2Field.getText(), currentQuestion.options()[1]) || !Objects.equals(option3Field.getText(), currentQuestion.options()[2])){
          MockAPI.editQuestion(new Question(questionField.getText(), answerField.getText(), new String[]{option1Field.getText(), option2Field.getText(), option3Field.getText()},topicBox.getSelectionModel().getSelectedItem(), currentQuestion.id()));
        }
      }
      switchScene(event, "current-questions", currentUser);
    }
  }
}
