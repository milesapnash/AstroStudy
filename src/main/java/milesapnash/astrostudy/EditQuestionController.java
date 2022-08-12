package milesapnash.astrostudy;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditQuestionController extends DataController {

  @FXML
  TextField questionField;
  @FXML
  TextField answerField;
  @FXML
  TextField topicField;

  public void setQuestion(Question question){
    questionField.setText(question.text());
    answerField.setText(question.answer());
    topicField.setText(question.topic());
  }
}
