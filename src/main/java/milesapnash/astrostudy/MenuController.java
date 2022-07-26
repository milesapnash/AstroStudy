package milesapnash.astrostudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static milesapnash.astrostudy.AstroStudyApplication.switchScene;

public class MenuController {

  @FXML
  void toMain(ActionEvent event) {
    switchScene(event, "main-view.fxml");

  }
}
