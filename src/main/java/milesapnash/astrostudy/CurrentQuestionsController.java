package milesapnash.astrostudy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
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

  public static void questionEdit(ActionEvent event, Question question){
    final Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
    try {
      final FXMLLoader loader = new FXMLLoader(AstroStudyApplication.class.getResource("edit-question-view.fxml"));
      final Parent root = loader.load();
      final Scene scene = new Scene(root);
      final URL url = AstroStudyApplication.class.getResource("application.css");

      if (url == null) {
        System.out.println("CSS Resource not found.");
        System.exit(-1);
      }

      EditQuestionController menuController = loader.getController();
      menuController.setQuestion(question);

      scene.getStylesheets().add(url.toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void toEdit(ActionEvent event){
    // TODO: Pass question in
    final Question question = new Question(questionList.getSelectionModel().getSelectedItem(), "", new ArrayList<>(), topicBox.getValue());
    questionEdit(event, question);
  }

  @FXML
  public void toNew(ActionEvent event){
    questionEdit(event, Question.blankQuestion());
  }

  @FXML
  public void delete(ActionEvent event){
    // TODO: API Request to remove question
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
