package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Pair;

import java.util.List;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class TestViewController extends DataController {
  private List<Question> questions;
  private List<Boolean> results;
  private Question current;
  private User currentUser;

  @Override
  <T> void parseData(T data) {
    Pair<User, List<Question>> testData = (Pair<User, List<Question>>) data;
    currentUser = testData.getKey();
    questions = testData.getValue();
  }

  @FXML
  public void toMenu(ActionEvent event){
    switchScene(event, "menu", currentUser);
  }
}
