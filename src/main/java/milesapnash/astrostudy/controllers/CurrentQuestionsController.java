package milesapnash.astrostudy.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import milesapnash.astrostudy.*;

import java.util.List;
import java.util.Objects;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.MockAPI.getTopics;

public class CurrentQuestionsController extends DataController {

  private final ObservableList<String> topics =
      FXCollections.observableArrayList(
          getTopics()
      );
  private User currentUser;
  private List<Question> currentQuestions;

  @FXML
  ComboBox<String> topicBox;
  @FXML
  ListView<String> questionList;
  @FXML
  Button newQuestion;
  @FXML
  Button editQuestion;
  @FXML
  Button deleteQuestion;

  @Override
  public <A> void parseData(A data) {
    currentUser = (User) data;

    topicBox.setItems(topics);
    topicBox.setButtonCell(new ListCell(){
      @Override
      protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item==null){
          setStyle("-fx-text-fill: derive(-fx-control-inner-background,-30%)");
        } else {
          setStyle("-fx-text-fill: -fx-text-inner-color");
          setText(item.toString());
        }
      }
    });

    questionList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      editQuestion.visibleProperty().set(true);
      deleteQuestion.visibleProperty().set(true);
    });

    topicBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> reloadQuestions());
  }

  private void editQuestion(ActionEvent event, Question question){
    final String topic = topicBox.getSelectionModel().getSelectedItem();
    if (!Objects.equals(topic, "")){
      switchScene(event, "edit-question", new TopicData(currentUser, question, topic));
    }
  }

  private void reloadQuestions(){
    questionList.getSelectionModel().clearSelection();
    newQuestion.visibleProperty().set(true);
    questionList.visibleProperty().set(true);
    editQuestion.visibleProperty().set(false);
    deleteQuestion.visibleProperty().set(false);

    ObservableList<String> questions = FXCollections.observableArrayList();
    currentQuestions = MockAPI.getAllTopicQuestions(topicBox.getValue());

    for (Question q : currentQuestions){
      questions.add(q.text());
    }
    questionList.setItems(questions);
  }

  @FXML
  public void toNew(ActionEvent event){
    editQuestion(event, Question.blankQuestion());
  }

  @FXML
  public void toEdit(ActionEvent event){
    editQuestion(event, currentQuestions.get(questionList.getSelectionModel().getSelectedIndex()));
  }

  @FXML
  public void delete(ActionEvent event){
    final String topic = topicBox.getSelectionModel().getSelectedItem();
    if (!Objects.equals(topic, "")){
      MockAPI.deleteQuestion(topic, currentQuestions.get(questionList.getSelectionModel().getSelectedIndex()).id());
    }
    reloadQuestions();
  }

  @FXML
  public void toMenu(ActionEvent event){
    switchScene(event, "menu", currentUser);
  }
}
