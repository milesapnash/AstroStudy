package milesapnash.astrostudy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.List;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.MockAPI.getTopics;


public class CurrentQuestionsController extends DataController {
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
  ObservableList<String> topics =
      FXCollections.observableArrayList(
          getTopics()
      );
  private User currentUser;

  @FXML
  public void toEdit(ActionEvent event){
    // TODO: Pass question in
    toEdit(event, new Question(questionList.getSelectionModel().getSelectedItem(), "Answer", List.of("1","2","3"), topicBox.getValue()));
  }

  @FXML
  public void toNew(ActionEvent event){
    toEdit(event, Question.blankQuestion());
  }

  private void toEdit(ActionEvent event, Question question){
    switchScene(event, "edit-question", new TestData(currentUser, List.of(question)));
  }

  @FXML
  public void delete(ActionEvent event){
    // Question ID
    int id = 0;
    MockAPI.deleteQuestion(id);
  }

  @FXML
  public void toMenu(ActionEvent event){
    switchScene(event, "menu", currentUser);
  }

  @Override
  public <A> void parseData(A data) {
    currentUser = (User) data;

    topicBox.setItems(topics);

    questionList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      editQuestion.visibleProperty().set(true);
      deleteQuestion.visibleProperty().set(true);
    });

    topicBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
      questionList.getSelectionModel().clearSelection();
      newQuestion.visibleProperty().set(true);
      questionList.visibleProperty().set(true);
      editQuestion.visibleProperty().set(false);
      deleteQuestion.visibleProperty().set(false);
      ObservableList<String> questions = FXCollections.observableArrayList();
      List<Question> topicQs = MockAPI.getTopicQuestions(topicBox.getValue());

      for (Question q : topicQs){
        questions.add(q.text());
      }
      questionList.setItems(questions);
    });
  }
}
