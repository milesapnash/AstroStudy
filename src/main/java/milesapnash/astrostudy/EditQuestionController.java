package milesapnash.astrostudy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;
import static milesapnash.astrostudy.MockAPI.getTopics;

public class EditQuestionController extends DataController {

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
  private final ObservableList<String> topics =
      FXCollections.observableArrayList(
          getTopics()
      );
  private User currentUser;

  @Override
  public <A> void parseData(A data) {
    TestData testData = (TestData) data;
    currentUser = testData.user();
    Question question = testData.questions().get(0);

    topicBox.setItems(topics);
    topicBox.getSelectionModel().select(question.topic());

    questionField.setText(question.text());
    answerField.setText(question.answer());
    option1Field.setText(question.options().get(0));
    option2Field.setText(question.options().get(1));
    option3Field.setText(question.options().get(2));
  }

  @FXML
  public void toQuestions(ActionEvent event){
    switchScene(event, "current-questions", currentUser);
  }
}
